package com.carbon.trade.service;

import com.carbon.trade.entity.CarbonTradePrice;
import com.carbon.common.service.BaseService;
import com.carbon.trade.param.CarbonTradePriceQueryParam;
import com.carbon.trade.param.IntendedTransactionParam;
import com.carbon.trade.vo.CarbonTradePriceQueryVo;
import com.carbon.common.api.Paging;

import java.io.Serializable;

/**
 * <p>
 * 碳交易询报价 服务类
 * </p>
 *
 * @author lin rizhao
 * @since 2022-05-21
 */
public interface CarbonTradePriceService extends BaseService<CarbonTradePrice> {

    /**
     * 添加询报价记录
     * @param tradePrice
     */
    void addTradePrice(CarbonTradePrice tradePrice);

    /**
     * 根据ID获取查询对象
     * @param id 主键id
     * @return CarbonTradePriceQueryVo
     */
    CarbonTradePriceQueryVo getCarbonTradePriceById(Serializable id);

    /**
     * 获取分页对象
     * @param param CarbonTradePriceQueryParam
     * @return Paging<CarbonTradePriceQueryVo>
     */
    Paging<CarbonTradePriceQueryVo> getCarbonTradePricePageList(CarbonTradePriceQueryParam param);

    /**
     * 意向成交
     * @param id 询报价ID
     */
    void intendedTransaction(IntendedTransactionParam param);
}