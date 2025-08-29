package com.carbon.trade.sentinel;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.carbon.common.api.Paging;
import com.carbon.trade.vo.CarbonTradeQuoteQueryVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TradeSentinelHandler {
    private static final Logger log = LoggerFactory.getLogger(TradeSentinelHandler.class);

    public static Paging<CarbonTradeQuoteQueryVo> handleGetCarbonTradeQuotePageList(Object param, BlockException ex) {
        log.warn("Trade block handler for quote page: {}", ex == null ? "null" : ex.toString());
        Page<CarbonTradeQuoteQueryVo> empty = new Page<>();
        return new Paging<>(empty);
    }
}
