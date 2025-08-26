package com.carbon.trade.controller;

import com.carbon.common.api.Paging;
import com.carbon.domain.common.ApiResult;
import com.carbon.trade.common.BaseController;
import com.carbon.trade.entity.CarbonTradeQuote;
import com.carbon.trade.param.CarbonTradeQuoteQueryParam;
import com.carbon.trade.param.StartTradingParam;
import com.carbon.trade.service.CarbonTradeQuoteService;
import com.carbon.trade.vo.CarbonTradeQuoteQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * <p>
 * 碳交易供需行情 前端控制器
 * </p>
 *
 * @author lin rizhao
 * @since 2022-05-21
 */
@Slf4j
@RestController
@RequestMapping("/carbonTradeQuote")
@Api(value = "碳交易供需行情模块", tags = {"碳交易供需行情模块"})
public class CarbonTradeQuoteController extends BaseController {

    @Autowired
    private CarbonTradeQuoteService carbonTradeQuoteService;

    /**
     * 添加碳交易供需行情
     */
    @PostMapping("/add")
    @ApiOperation(value = "添加供需行情",notes = "添加碳交易供需行情")
    public ApiResult<Boolean> addCarbonTradeQuote(@Valid @RequestBody CarbonTradeQuote carbonTradeQuote) {
        carbonTradeQuoteService.addTradeQuote(carbonTradeQuote);
        return ApiResult.ok();
    }

    /**
     * 发起询报价
     */
    @PostMapping("/startTrading")
    @ApiOperation(value = "发起询报价",notes = "发起询报价")
    public ApiResult<Boolean> startTrading(@Valid @RequestBody StartTradingParam param) {
        carbonTradeQuoteService.startTrading(param);
        return ApiResult.ok();
    }

    /**
     * 获取碳交易供需行情
     */
    @GetMapping("/info/{id}")
    @ApiOperation(value = "查看供需行情",notes = "查看碳交易供需行情")
    public ApiResult<CarbonTradeQuoteQueryVo> getCarbonTradeQuote(@PathVariable String id) {
        CarbonTradeQuoteQueryVo carbonTradeQuoteQueryVo = carbonTradeQuoteService.getCarbonTradeQuoteById(id);
        return ApiResult.ok(carbonTradeQuoteQueryVo);
    }

    /**
     * 碳交易供需行情分页列表
     */
    @PostMapping("/getPageList")
    @ApiOperation(value = "供需行情分页列表",notes = "碳交易供需行情分页列表")
    public ApiResult<Paging<CarbonTradeQuoteQueryVo>> getCarbonTradeQuotePageList(@Valid @RequestBody(required = false) CarbonTradeQuoteQueryParam carbonTradeQuoteQueryParam) {
        Paging<CarbonTradeQuoteQueryVo> paging = carbonTradeQuoteService.getCarbonTradeQuotePageList(carbonTradeQuoteQueryParam);
        return ApiResult.ok(paging);
    }



    @GetMapping("/search")
    @ApiOperation(value = "简单搜索")
    public ApiResult<Paging<CarbonTradeQuoteQueryVo>> search(@RequestParam(required = false) String keyword,
                                                             @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                                             @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        return ApiResult.ok(carbonTradeQuoteService.searchByEs(keyword,pageNum,pageSize));
    }

    @GetMapping("/importAll")
    @ApiOperation(value = "同步所有行情到ES")
    public ApiResult<Integer> importAll() {
        return ApiResult.ok(carbonTradeQuoteService.importAll());
    }
}

