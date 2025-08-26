package com.carbon.assets.controller;

import com.carbon.assets.service.CarbonAssetsService;
import com.carbon.assets.param.CarbonAssetsQueryParam;
import com.carbon.assets.vo.CarbonAssetsQueryVo;
import com.carbon.assets.entity.CarbonAssets;
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
 * 碳中和资产 前端控制器
 * </p>
 *
 * @author Li Jun
 * @since 2021-07-31
 */
@Slf4j
@RestController
@RequestMapping("/carbonAssets")
@Api(value = "碳中和资产模块", description = "碳中和资产模块", tags = {"碳中和资产模块"})
public class CarbonController extends BaseController {

    @Autowired
    private CarbonAssetsService carbonAssetsService;

    /**
    * 添加碳中和资产
    */
    @PostMapping("/add")
    @ApiOperation(value = "添加碳中和资产",notes = "添加碳中和资产")
    public ApiResult<Boolean> addCarbonAssets(@Valid @RequestBody CarbonAssets carbonAssets) {
        carbonAssetsService.addCarbonAssets(carbonAssets);
        return ApiResult.ok();
    }

    /**
    * 修改碳中和资产
    */
    @PutMapping("/update")
    @ApiOperation(value = "修改碳中和资产",notes = "修改碳中和资产")
    public ApiResult<Boolean> updateCarbonAssets(@Valid @RequestBody CarbonAssets carbonAssets) {
        boolean flag = carbonAssetsService.updateCarbonAssets(carbonAssets);
        return ApiResult.result(flag);
    }

    /**
    * 获取碳中和资产
    */
    @GetMapping("/info/{id}")
    @ApiOperation(value = "查看碳中和资产",notes = "查看碳中和资产")
    public ApiResult<CarbonAssetsQueryVo> getCarbonAssets(@PathVariable Long id) {
        CarbonAssetsQueryVo carbonAssetsQueryVo = carbonAssetsService.getCarbonAssetsById(id);
        return ApiResult.ok(carbonAssetsQueryVo);
    }

    /**
     * 碳中和资产分页列表
     */
    @PostMapping("/getPageList")
    @ApiOperation(value = "碳中和资产分页列表",notes = "碳中和资产分页列表")
    public ApiResult<Paging<CarbonAssetsQueryVo>> getCarbonAssetsPageList(@Valid @RequestBody(required = false) CarbonAssetsQueryParam carbonAssetsQueryParam) {
        Paging<CarbonAssetsQueryVo> paging = carbonAssetsService.getCarbonAssetsPageList(carbonAssetsQueryParam);
        return ApiResult.ok(paging);
    }

    /**
     * 中和资产核证
     */
    @GetMapping("/certified/{assetsId}")
    @ApiOperation(value = "中和资产核证",notes = "中和资产核证")
    public ApiResult certified(@PathVariable String assetsId) {
        carbonAssetsService.certified(assetsId);
        return ApiResult.ok();
    }
}

