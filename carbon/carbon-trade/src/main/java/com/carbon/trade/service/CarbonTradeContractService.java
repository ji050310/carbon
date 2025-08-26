package com.carbon.trade.service;

import com.carbon.trade.entity.CarbonTradeContract;
import com.carbon.common.service.BaseService;
import com.carbon.trade.param.CarbonTradeContractQueryParam;
import com.carbon.trade.vo.CarbonTradeContractQueryVo;
import com.carbon.common.api.Paging;
import com.carbon.trade.vo.TradeContractPerformanceVo;

import java.io.Serializable;

/**
 * <p>
 * 碳交易履约 服务类
 * </p>
 *
 * @author lin rizhao
 * @since 2022-05-21
 */
public interface CarbonTradeContractService extends BaseService<CarbonTradeContract> {

    /**
     * 添加履约
     * @param tradeContract 履约信息
     */
    void addTradeContract(CarbonTradeContract tradeContract);

    /**
     * 根据ID获取查询对象
     * @param id 主键id
     * @return CarbonTradeContractQueryVo
     */
    CarbonTradeContractQueryVo getCarbonTradeContractById(Serializable id);

    /**
     * 获取分页对象
     * @param param CarbonTradeContractQueryParam
     * @return Paging<CarbonTradeContractQueryVo>
     */
    Paging<CarbonTradeContractQueryVo> getCarbonTradeContractPageList(CarbonTradeContractQueryParam param);

    /**
     * 执行交易履约
     * @param tradeContractId 交易履约ID
     * @return TradeContractPerformanceVo
     */
    TradeContractPerformanceVo performance(Long tradeContractId);
}
