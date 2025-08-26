package com.carbon.trade.controller;

import com.carbon.trade.service.CarbonTradeContractService;
import com.carbon.trade.param.CarbonTradeContractQueryParam;
import com.carbon.trade.vo.CarbonTradeContractQueryVo;
import com.carbon.trade.entity.CarbonTradeContract;
import com.carbon.trade.common.BaseController;
import com.carbon.domain.common.ApiResult;
import com.carbon.common.api.Paging;
import com.carbon.trade.vo.TradeContractPerformanceVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * <p>
 * 碳交易履约 前端控制器
 * </p>
 *
 * @author lin rizhao
 * @since 2022-05-21
 */
@Slf4j
@RestController
@RequestMapping("/carbonTradeContract")
@Api(value = "碳交易履约模块", tags = {"碳交易履约模块"})
public class CarbonTradeContractController extends BaseController {

    @Autowired
    private CarbonTradeContractService carbonTradeContractService;

    /**
    * 修改碳交易履约
    */
    @PutMapping("/update")
    @ApiOperation(value = "修改碳交易履约",notes = "修改碳交易履约")
    public ApiResult<Boolean> updateCarbonTradeContract(@Valid @RequestBody CarbonTradeContract carbonTradeContract) {
        boolean flag = carbonTradeContractService.updateById(carbonTradeContract);
        return ApiResult.result(flag);
    }

    /**
    * 获取碳交易履约
    */
    @GetMapping("/info/{id}")
    @ApiOperation(value = "查看碳交易履约",notes = "查看碳交易履约")
    public ApiResult<CarbonTradeContractQueryVo> getCarbonTradeContract(@PathVariable String id) {
        CarbonTradeContractQueryVo carbonTradeContractQueryVo = carbonTradeContractService.getCarbonTradeContractById(id);
        return ApiResult.ok(carbonTradeContractQueryVo);
    }

    /**
     * 碳交易履约分页列表
     */
    @PostMapping("/getPageList")
    @ApiOperation(value = "履约分页列表",notes = "碳交易履约分页列表")
    public ApiResult<Paging<CarbonTradeContractQueryVo>> getCarbonTradeContractPageList(@Valid @RequestBody(required = false) CarbonTradeContractQueryParam carbonTradeContractQueryParam) {
        Paging<CarbonTradeContractQueryVo> paging = carbonTradeContractService.getCarbonTradeContractPageList(carbonTradeContractQueryParam);
        return ApiResult.ok(paging);
    }


    /**
     * 下单
     */
    @PutMapping("/performance/{tradeContractId}")
    @ApiOperation(value = "下单",notes = "下单")
    public ApiResult<TradeContractPerformanceVo> performance(@ApiParam("履约ID") @PathVariable Long tradeContractId) {
        return ApiResult.ok(carbonTradeContractService.performance(tradeContractId));
    }
}

