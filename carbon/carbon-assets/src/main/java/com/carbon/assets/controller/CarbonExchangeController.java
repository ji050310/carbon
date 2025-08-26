package com.carbon.assets.controller;

import cn.hutool.core.bean.BeanUtil;
import com.carbon.assets.service.CarbonExchangeService;
import com.carbon.assets.param.CarbonExchangeQueryParam;
import com.carbon.assets.vo.CarbonExchangeAccountVo;
import com.carbon.domain.assets.vo.CarbonExchangeQueryVo;
import com.carbon.assets.entity.CarbonExchange;
import com.carbon.assets.common.BaseController;
import com.carbon.common.api.Paging;
import com.carbon.domain.common.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


/**
 * <p>
 * 碳交易所  前端控制器
 * </p>
 *
 * @author Li Jun
 * @since 2021-08-16
 */
@Slf4j
@RestController
@RequestMapping("/carbonExchange")
@Api(value = "碳交易所 模块", tags = {"碳交易所 模块"})
public class CarbonExchangeController extends BaseController {
    @Autowired
    private CarbonExchangeService carbonExchangeService;

    /**
     * 添加碳交易
     */
    @PostMapping("/add")
    @ApiOperation(value = "添加碳交易", notes = "添加碳交易")
    public ApiResult<Boolean> addCarbonExchange(@Valid @RequestBody CarbonExchange carbonExchange) {
        boolean flag = carbonExchangeService.save(carbonExchange);
        return ApiResult.result(flag);
    }

    /**
     * 删除碳交易
     */
    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "删除碳交易对象",notes = "删除碳交易")
    public ApiResult<Boolean> delete(@PathVariable Long id) {
        boolean flag = carbonExchangeService.removeById(id);
        return ApiResult.result(flag);
    }

    /**
     * 修改碳交易
     */

    @PutMapping("/update")
    @ApiOperation(value = "修改碳交易所",notes = "修改碳交易所")
    public ApiResult<Boolean> updateCarbonExchange(@Valid @RequestBody CarbonExchange carbonExchange) {
        boolean flag = carbonExchangeService.updateById(carbonExchange);
        return ApiResult.result(flag);
    }

    /**
     * 获取碳交易所
     */
    @GetMapping("/info/{id}")
    @ApiOperation(value = "查看碳交易所",notes = "查看碳交易所")
    public ApiResult<CarbonExchangeQueryVo> getCarbonExchange(@PathVariable String id){
        CarbonExchangeQueryVo carbonExchangeQueryVo = carbonExchangeService.getCarbonExchangeById(id);
        return ApiResult.ok(carbonExchangeQueryVo);
    }

    /**
     * 获取碳交易所
     */
    @GetMapping("/info/dict/{code}")
    @ApiOperation(value = "查看碳交易所",notes = "查看碳交易所")
    public ApiResult<CarbonExchangeQueryVo> getCarbonExchangeByDict(@PathVariable String code){
        CarbonExchange exchange = carbonExchangeService.lambdaQuery().eq(CarbonExchange::getDict, code).one();
        return ApiResult.ok(BeanUtil.copyProperties(exchange, CarbonExchangeQueryVo.class));
    }

    /**
     * 碳交易所 分页列表
     */
    @PostMapping("getPageList")
    @ApiOperation(value = "碳交易所 分页列表",notes = "碳交易所 分页列表")
    public ApiResult<Paging<CarbonExchangeQueryVo>> getCarbonExchangePageList(@Valid @RequestBody(required = false) CarbonExchangeQueryParam carbonExchangeQueryParam) {
        Paging<CarbonExchangeQueryVo> paging = carbonExchangeService.getCarbonExchangePageList(carbonExchangeQueryParam);
        return ApiResult.ok(paging);
    }

    /**
     * 碳交易所，分页列表
     */
    @PostMapping("/getListByTenant")
    @ApiOperation(value = "碳交易所，分页列表",notes = "碳交易所，分页列表")
    public ApiResult<List<CarbonExchangeAccountVo>> getList(){
        List<CarbonExchangeAccountVo> paging = carbonExchangeService.getListByTenant();
        return ApiResult.ok(paging);
    }
}

