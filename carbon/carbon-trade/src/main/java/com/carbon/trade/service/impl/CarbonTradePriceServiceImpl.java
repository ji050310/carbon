package com.carbon.trade.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.carbon.common.exception.CommonBizException;
import com.carbon.common.redis.RedisService;
import com.carbon.domain.common.ApiResult;
import com.carbon.domain.common.constant.RedisKeyName;
import com.carbon.domain.system.api.SystemServiceApi;
import com.carbon.domain.system.vo.SysAccountModelVo;
import com.carbon.domain.system.vo.SysTenantModelVo;
import com.carbon.trade.common.enums.TradeRoleEnum;
import com.carbon.trade.common.enums.TradeStatusEnum;
import com.carbon.trade.entity.CarbonTradePrice;
import com.carbon.trade.entity.CarbonTradeQuote;
import com.carbon.trade.mapper.CarbonTradePriceMapper;
import com.carbon.trade.param.IntendedTransactionParam;
import com.carbon.trade.service.CarbonTradeContractService;
import com.carbon.trade.service.CarbonTradePriceService;
import com.carbon.trade.param.CarbonTradePriceQueryParam;
import com.carbon.trade.service.CarbonTradeQuoteService;
import com.carbon.trade.vo.CarbonTradePriceQueryVo;
import com.carbon.common.service.BaseServiceImpl;
import com.carbon.common.api.Paging;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * <p>
 * 碳交易询报价 服务实现类
 * </p>
 *
 * @author lin rizhao
 * @since 2022-05-21
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class CarbonTradePriceServiceImpl extends BaseServiceImpl<CarbonTradePriceMapper, CarbonTradePrice> implements CarbonTradePriceService {

    @Resource
    private CarbonTradePriceMapper carbonTradePriceMapper;
    @Autowired
    private CarbonTradeContractService carbonTradeContractService;
    @Autowired
    private CarbonTradeQuoteService carbonTradeQuoteService;
    @Autowired
    private SystemServiceApi systemServiceApi;

    @Override
    public void addTradePrice(CarbonTradePrice tradePrice) {
        this.save(tradePrice);
    }

    @Override
    public CarbonTradePriceQueryVo getCarbonTradePriceById(Serializable id) {
        return carbonTradePriceMapper.getCarbonTradePriceById(id);
    }

    @Override
    public Paging<CarbonTradePriceQueryVo> getCarbonTradePricePageList(CarbonTradePriceQueryParam param) {
        Long tenantId = getCurrentTenantId();
        param.setTenantId(tenantId);
        //分页列表按更新时间降序
        Page<?> page = getPage(param);
        page.addOrder(OrderItem.desc("ctp.updated_time"));
        IPage<CarbonTradePriceQueryVo> iPage = carbonTradePriceMapper.getCarbonTradePricePageList(page,param);

        //获取租户信息
        Map<Long, String> tenantNameMap = null;
        List<SysTenantModelVo> tenantList = systemServiceApi.getTenantList().getData();
        if (CollUtil.isNotEmpty(tenantList)){
            tenantNameMap = tenantList.stream().collect(Collectors.toMap(SysTenantModelVo::getId, SysTenantModelVo::getTenantName));
        }

        //获取用户信息
        Map<Long, String> accountNameMap = null;
        List<SysAccountModelVo> accountList = systemServiceApi.getAccountList().getData();
        if (CollUtil.isNotEmpty(accountList)){
            accountNameMap = accountList.stream().collect(Collectors.toMap(SysAccountModelVo::getId, SysAccountModelVo::getAccountName));
        }

        for (CarbonTradePriceQueryVo record : iPage.getRecords()) {
            if (tenantId.equals(record.getBuyerId())){
                record.setTradeRole(TradeRoleEnum.BUYER.getStatus());
            }else {
                record.setTradeRole(TradeRoleEnum.SELLER.getStatus());
            }

            if (CollUtil.isNotEmpty(accountNameMap) && CollUtil.isNotEmpty(tenantNameMap)){
                record.setConversation(accountNameMap.get(record.getPublisherUserId()) + "_" + tenantNameMap.get(record.getPublisherId()));
            }
        }
        return new Paging<>(iPage);
    }

    @Override
    public void intendedTransaction(IntendedTransactionParam param) {
        CarbonTradePrice tradePrice = this.getById(param.getTradePriceId());
        if (tradePrice == null){
            throw new CommonBizException("找不到询报价记录");
        }

        //修改交易行情状态
        CarbonTradeQuote tradeQuote = new CarbonTradeQuote();
        tradeQuote.setId(tradePrice.getTradeQuoteId());
        tradeQuote.setStatus(TradeStatusEnum.IN_TRADE.getStatus());
        carbonTradeQuoteService.updateById(tradeQuote);

        //生成履约信息
        carbonTradeContractService.addTradeContract(param.getTradeContract());
    }

}
