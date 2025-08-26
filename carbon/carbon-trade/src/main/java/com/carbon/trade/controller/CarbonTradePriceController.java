package com.carbon.trade.controller;

import com.carbon.trade.entity.CarbonTradeContract;
import com.carbon.trade.param.IntendedTransactionParam;
import com.carbon.trade.service.CarbonTradePriceService;
import com.carbon.trade.param.CarbonTradePriceQueryParam;
import com.carbon.trade.vo.CarbonTradeContractQueryVo;
import com.carbon.trade.vo.CarbonTradePriceQueryVo;
import com.carbon.trade.entity.CarbonTradePrice;
import com.carbon.trade.common.BaseController;
import com.carbon.domain.common.ApiResult;
import com.carbon.common.api.Paging;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * <p>
 * 碳交易询报价 前端控制器
 * </p>
 *
 * @author lin rizhao
 * @since 2022-05-22
 */
@Slf4j
@RestController
@RequestMapping("/carbonTradePrice")
@Api(value = "碳交易询报价模块", tags = {"碳交易询报价模块"})
public class CarbonTradePriceController extends BaseController {
    @Autowired
    private CarbonTradePriceService carbonTradePriceService;

    @PostMapping("/intendedTransaction")
    @ApiOperation(value = "意向成交",notes = "意向成交")
    public ApiResult<CarbonTradePriceQueryVo> intendedTransaction(@RequestBody IntendedTransactionParam param) {
        CarbonTradeContract tradeContract = param.getTradeContract();
        if (tradeContract.getBuyerId() == null || tradeContract.getSellerId() == null){
            return ApiResult.fail("买方或卖方ID为空");
        }
        carbonTradePriceService.intendedTransaction(param);
        return ApiResult.ok();
    }

    /**
     * 获取碳交易询报价
     */
    @GetMapping("/info/{id}")
    @ApiOperation(value = "查看碳交易询报价",notes = "查看碳交易询报价")
    public ApiResult<CarbonTradePriceQueryVo> getCarbonTradePrice(@PathVariable String id) {
        CarbonTradePriceQueryVo carbonTradePriceQueryVo = carbonTradePriceService.getCarbonTradePriceById(id);
        return ApiResult.ok(carbonTradePriceQueryVo);
    }

    /**
     * 碳交易询报价分页列表
     */
    @PostMapping("/getPageList")
    @ApiOperation(value = "碳交易询报价分页列表",notes = "碳交易询报价分页列表")
    public ApiResult<Paging<CarbonTradePriceQueryVo>> getCarbonTradePricePageList(@Valid @RequestBody(required = false) CarbonTradePriceQueryParam carbonTradePriceQueryParam) {
        Paging<CarbonTradePriceQueryVo> paging = carbonTradePriceService.getCarbonTradePricePageList(carbonTradePriceQueryParam);
        return ApiResult.ok(paging);
    }

}

