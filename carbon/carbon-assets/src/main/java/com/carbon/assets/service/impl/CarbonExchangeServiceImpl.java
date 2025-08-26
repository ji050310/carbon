package com.carbon.assets.service.impl;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.carbon.assets.service.ExchangeAccountService;
import com.carbon.assets.entity.CarbonExchange;
import com.carbon.assets.mapper.CarbonExchangeMapper;
import com.carbon.assets.service.CarbonExchangeService;
import com.carbon.assets.param.CarbonExchangeQueryParam;
import com.carbon.assets.vo.CarbonExchangeAccountVo;
import com.carbon.domain.assets.vo.CarbonExchangeQueryVo;
import com.carbon.common.service.BaseServiceImpl;
import com.carbon.common.api.Paging;
import com.carbon.assets.vo.ExchangeAccountInfo;
import com.carbon.domain.system.api.SystemServiceApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * <p>
 * 碳交易所  服务实现类
 * </p>
 *
 * @author Li Jun
 * @since 2021-08-16
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class CarbonExchangeServiceImpl extends BaseServiceImpl<CarbonExchangeMapper, CarbonExchange> implements CarbonExchangeService {

    @Resource
    private CarbonExchangeMapper carbonExchangeMapper;

    @Autowired
    private SystemServiceApi systemServiceApi;

    @Autowired
    private ExchangeAccountService exchangeAccountService;


    @Override
    public CarbonExchangeQueryVo getCarbonExchangeById(String id) {
        return carbonExchangeMapper.getCarbonExchangeById(id);
    }

    @Override
    public Paging<CarbonExchangeQueryVo> getCarbonExchangePageList(CarbonExchangeQueryParam param) {
        Page<?> page = getPage(param);
        page.addOrder(OrderItem.desc("ce.updated_time"));
        IPage<CarbonExchangeQueryVo> iPage = carbonExchangeMapper.getCarbonExchangePageList(page,param);
        return iPage == null ? null : new Paging<>(iPage);
    }

    @Override
    public List<CarbonExchangeAccountVo> getListByTenant() {
        List<CarbonExchange> exchanges = this.lambdaQuery().list();
        List<Long> exchangeIds = exchanges.stream().map(CarbonExchange::getId).collect(Collectors.toList());
        Map<Long, List<ExchangeAccountInfo>> accountMap =exchangeAccountService.getListByExchangeId(exchangeIds).
                stream().collect(Collectors.groupingBy(ExchangeAccountInfo::getCarbonExchangeId));

        ArrayList<CarbonExchangeAccountVo> vos = new ArrayList<>();
        for(CarbonExchange exchange : exchanges){
            CarbonExchangeAccountVo vo = new CarbonExchangeAccountVo();
            vo.setId(exchange.getId());
            vo.setExchangeName(exchange.getName());
            vo.setWebsite(exchange.getWebsite());
            vo.setDetailUrl(exchange.getDetailUrl());
            vo.setAccountInfoList(accountMap.get(exchange.getId()));
            vos.add(vo);
        }
        return Collections.emptyList();
    }
}
