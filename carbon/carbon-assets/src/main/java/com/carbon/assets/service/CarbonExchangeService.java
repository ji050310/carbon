package com.carbon.assets.service;

import com.carbon.assets.entity.CarbonExchange;
import com.carbon.common.service.BaseService;
import com.carbon.assets.param.CarbonExchangeQueryParam;
import com.carbon.assets.vo.CarbonExchangeAccountVo;
import com.carbon.domain.assets.vo.CarbonExchangeQueryVo;
import com.carbon.common.api.Paging;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 碳交易所  服务类
 * </p>
 *
 * @author Li Jun
 * @since 2021-08-16
 */
public interface CarbonExchangeService extends BaseService<CarbonExchange> {

    CarbonExchangeQueryVo getCarbonExchangeById(String id);

    Paging<CarbonExchangeQueryVo> getCarbonExchangePageList(CarbonExchangeQueryParam carbonExchangeQueryParam);

    List<CarbonExchangeAccountVo> getListByTenant();
}
