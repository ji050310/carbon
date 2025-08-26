package com.carbon.trade.service;

import com.carbon.trade.entity.CarbonTradeQuote;
import com.carbon.common.service.BaseService;
import com.carbon.trade.param.CarbonTradeQuoteQueryParam;
import com.carbon.trade.param.StartTradingParam;
import com.carbon.trade.vo.CarbonTradeQuoteQueryVo;
import com.carbon.common.api.Paging;

import java.io.Serializable;

/**
 * <p>
 * 碳交易供需行情 服务类
 * </p>
 *
 * @author lin rizhao
 * @since 2022-05-21
 */
public interface CarbonTradeQuoteService extends BaseService<CarbonTradeQuote> {

    /**
     * 添加询报价
     * @param tradeQuote
     */
    void addTradeQuote(CarbonTradeQuote tradeQuote);

    /**
     * 根据ID获取查询对象
     * @param id 主键id
     * @return CarbonTradeQuoteQueryVo
     */
    CarbonTradeQuoteQueryVo getCarbonTradeQuoteById(Serializable id);

    /**
     * 获取分页对象
     * @param param CarbonTradeQuoteQueryParam
     * @return Paging<CarbonTradeQuoteQueryVo>
     */
    Paging<CarbonTradeQuoteQueryVo> getCarbonTradeQuotePageList(CarbonTradeQuoteQueryParam param);

    /**
     * 发起询报价
     * @param param 参数
     */
    void startTrading(StartTradingParam param);


    /**
     * 获取分页对象
     * @param param CarbonTradeQuoteQueryParam
     * @return Paging<CarbonTradeQuoteQueryVo>
     */
    Paging<CarbonTradeQuoteQueryVo> searchByEs(String keyword,Integer pageNum,Integer pageSize);

    /**
     * 从数据库中导入所有商品到ES
     */
    Integer importAll();

}