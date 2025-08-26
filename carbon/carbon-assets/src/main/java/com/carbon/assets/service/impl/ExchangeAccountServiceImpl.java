package com.carbon.assets.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.nacos.common.utils.MD5Utils;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.carbon.assets.entity.CarbonExchange;
import com.carbon.assets.entity.ExchangeAccount;
import com.carbon.assets.mapper.ExchangeAccountMapper;
import com.carbon.assets.mapper.CarbonExchangeMapper;
import com.carbon.assets.param.ExchangeAccountBindingParam;
import com.carbon.assets.service.ExchangeAccountService;
import com.carbon.assets.param.ExchangeAccountQueryParam;
import com.carbon.assets.vo.ExchangeAccountInfo;
import com.carbon.assets.vo.ExchangeAccountQueryVo;
import com.carbon.common.enums.ExpCodeEnum;
import com.carbon.common.exception.CommonBizException;
import com.carbon.common.service.BaseServiceImpl;
import com.carbon.common.api.Paging;
import com.carbon.domain.assets.vo.CarbonExchangeQueryVo;
import com.carbon.domain.auth.vo.LoginInfoVo;
import com.carbon.domain.auth.vo.SecurityData;
import com.carbon.domain.chainmaker.api.ChainMakerServiceApi;
import com.carbon.domain.common.constant.RocketDelayLevelConstant;
import com.carbon.domain.common.constant.RocketMqName;
import com.carbon.domain.mq.entity.AddTradingAccountApproval;
import com.carbon.domain.system.api.SystemServiceApi;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 交易账户  服务实现类
 * </p>
 *
 * @author Li Jun
 * @since 2021-08-16
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class ExchangeAccountServiceImpl extends BaseServiceImpl<ExchangeAccountMapper, ExchangeAccount> implements ExchangeAccountService {

    @Resource
    private ExchangeAccountMapper exchangeAccountMapper;

    @Resource
    private CarbonExchangeMapper carbonExchangeMapper;

    @Autowired
    private RocketMQTemplate mqTemplate;
    @Autowired
    private SystemServiceApi systemServiceApi;

    @Autowired
    private ChainMakerServiceApi chainMakerServiceApi;


    @Override
    public ExchangeAccountQueryVo getExchangeAccountById(Serializable id) {
        return exchangeAccountMapper.getExchangeAccountById(id);
    }

    @Override
    public Paging<ExchangeAccountQueryVo> getExchangeAccountPageList(ExchangeAccountQueryParam param) {
        Page<?> page = getPage(param);
        page.addOrder(OrderItem.desc("ea.updated_time"));
        IPage<ExchangeAccountQueryVo> iPage = exchangeAccountMapper.getExchangeAccountPageList(page,param);
        return new Paging<>(iPage);
    }

    @Override
    public void addExchangeAccount(ExchangeAccount exchangeAccount) {
        if(exchangeAccountMapper.getExchangeAccountByName(exchangeAccount) != 0){
            throw new CommonBizException("账户已存在");
        }

        //注册长安链账户
//        HttpUtil.createPost("/api/manage/registerAdmin")
        //创建平台账户
        //密码不空
        exchangeAccount.setAccountStatus("0430000001");//待审核
        if(StrUtil.isNotBlank(exchangeAccount.getPassword())){
            exchangeAccount.setPassword(DigestUtils.md5Hex(exchangeAccount.getPassword()));
        }
        save(exchangeAccount);
        //根据交易所id查询名字，获取对象的电话和名字
        CarbonExchange exchange = carbonExchangeMapper.selectById(exchangeAccount.getCarbonExchangeId());
        if (exchange == null){
            throw new CommonBizException("交易所不存在");
        }

        SecurityData data = getLoginInfoVo().getSecurityData();
        //飞书审批
        AddTradingAccountApproval approval = AddTradingAccountApproval.builder()
                .id(exchangeAccount.getId())
                .userName(handleNull(data.getAccountName()))
                .agenciesName(handleNull(data.getTenantName()))
                .contactNumber(handleNull(data.getPhone()))
                .exchangeName(handleNull(exchange.getName()))
                .tradeAccount(handleNull(exchangeAccount.getAccountName()))
                .accountProof(handleNull(exchangeAccount.getAccountCredentials()))
                .remark(handleNull(exchangeAccount.getRemarks()))
                .build();
        org.springframework.messaging.Message<AddTradingAccountApproval> msg= MessageBuilder.withPayload(approval).build();
        mqTemplate.syncSend(RocketMqName.AddTradingAccountApproval_MSG,msg,3000, RocketDelayLevelConstant.SECOND5);

        try {
            Map<String, Object> map = BeanUtil.beanToMap(exchangeAccount, false, true);
            Map<String,String>  paramMap = new HashMap<>(map.size());
            map.forEach((k,v)->{
                if(v instanceof String){
                    paramMap.put(k,v.toString());
                }else if(v instanceof Date){
                    paramMap.put(k,DateUtil.format((Date) v,"yyyy-MM-dd HH:mm:ss"));
                }else {
                    paramMap.put(k,v.toString());
                }
            });
            // 调用区块链交易员上链
            chainMakerServiceApi.registerExchangeAccount(paramMap);
        }catch (Exception e){
            log.error(e.getMessage());
        }

    }

    private String handleNull(String str){
        return str == null ? "-" : str;
    }

    @Override
    public List<ExchangeAccountInfo> getListByExchangeId(List<Long> exchangeIds) {
        if (CollUtil.isEmpty(exchangeIds)){
            return CollUtil.empty(ExchangeAccountInfo.class);
        }
        return exchangeAccountMapper.getListByExchangeId(exchangeIds);
    }

    @Override
    public void binding(ExchangeAccountBindingParam param) {
        ExchangeAccount account = this.getById(param.getAccountId());
        if (account == null){
            throw new CommonBizException("账户不存在");
        }
        account.setCarbonExchangeId(param.getCarbonExchangeId());
        account.setBindingTime(DateUtil.date());
        account.setCookie(param.getCookie());

        if (!this.updateById(account)){
            throw new CommonBizException(ExpCodeEnum.OPERATE_FAIL_ERROR);
        }
    }

    @Override
    public void unbind(Long id) {
        ExchangeAccount account = this.getById(id);
        if (account == null){
            throw new CommonBizException("账户不存在");
        }
        if (!exchangeAccountMapper.unbind(id)){
            throw new CommonBizException(ExpCodeEnum.OPERATE_FAIL_ERROR);
        }
    }

}
