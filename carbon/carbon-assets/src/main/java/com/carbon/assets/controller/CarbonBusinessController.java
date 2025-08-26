package com.carbon.assets.controller;

import com.carbon.assets.service.CarbonAssetsBusinessService;
import com.carbon.assets.param.CarbonAssetsBusinessQueryParam;
import com.carbon.assets.vo.CarbonAssetsBusinessQueryVo;
import com.carbon.assets.entity.CarbonAssetsBusiness;
import com.carbon.assets.common.BaseController;
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
 * 中和资产交易  前端控制器
 * </p>
 *
 * @author Li Jun
 * @since 2021-09-24
 */
@Slf4j
@RestController
@RequestMapping("/carbonAssetsBusiness")
@Api(value = "中和资产交易 模块", tags = {"中和资产交易 模块"})
public class CarbonBusinessController extends BaseController {

    @Autowired
    private CarbonAssetsBusinessService carbonAssetsBusinessService;

    /**
    * 添加中和资产交易
    */
    @PostMapping("/add")
    @ApiOperation(value = "添加中和资产交易",notes = "添加中和资产交易 ")
    public ApiResult<Boolean> addCarbonAssetsBusiness(@Valid @RequestBody CarbonAssetsBusiness carbonAssetsBusiness) {
        carbonAssetsBusinessService.addCarbonAssetsBusiness(carbonAssetsBusiness);
        return ApiResult.ok();
    }

    /**
    * 修改中和资产交易
    */
    @PutMapping("/update")
    @ApiOperation(value = "修改中和资产交易",notes = "修改中和资产交易 ")
    public ApiResult<Boolean> updateCarbonAssetsBusiness(@Valid @RequestBody CarbonAssetsBusiness carbonAssetsBusiness) {
        boolean flag = carbonAssetsBusinessService.updateById(carbonAssetsBusiness);
        return ApiResult.result(flag);
    }

    /**
    * 删除中和资产交易
    */
    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "删除中和资产交易",notes = "删除中和资产交易 ")
    public ApiResult<Boolean> deleteCarbonAssetsBusiness(@PathVariable String id) {
        boolean flag = carbonAssetsBusinessService.removeById(id);
        return ApiResult.result(flag);
    }

    /**
    * 获取中和资产交易
    */
    @GetMapping("/info/{id}")
    @ApiOperation(value = "获取中和资产交易",notes = "查看中和资产交易 ")
    public ApiResult<CarbonAssetsBusinessQueryVo> getCarbonAssetsBusiness(@PathVariable Long id) {
        CarbonAssetsBusinessQueryVo carbonAssetsBusinessQueryVo = carbonAssetsBusinessService.getCarbonAssetsBusinessById(id);
        return ApiResult.ok(carbonAssetsBusinessQueryVo);
    }

    /**
     * 中和资产交易 分页列表
     */
    @PostMapping("/getPageList")
    @ApiOperation(value = "中和资产交易分页列表",notes = "中和资产交易 分页列表")
    public ApiResult<Paging<CarbonAssetsBusinessQueryVo>> getCarbonAssetsBusinessPageList(@Valid @RequestBody(required = false) CarbonAssetsBusinessQueryParam carbonAssetsBusinessQueryParam) {
        Paging<CarbonAssetsBusinessQueryVo> paging = carbonAssetsBusinessService.getCarbonAssetsBusinessPageList(carbonAssetsBusinessQueryParam);
        return ApiResult.ok(paging);
    }

}

