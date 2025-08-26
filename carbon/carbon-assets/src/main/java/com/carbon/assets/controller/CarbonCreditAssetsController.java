package com.carbon.assets.controller;

import com.carbon.assets.param.CarbonCreditAssetsAddParam;
import com.carbon.assets.service.CarbonCreditAssetsService;
import com.carbon.assets.param.CarbonCreditAssetsQueryParam;
import com.carbon.assets.vo.CarbonAssetsTotalVo;
import com.carbon.assets.vo.CarbonCreditAssetsQueryVo;
import com.carbon.assets.entity.CarbonCreditAssets;
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
 * 碳信用资产 前端控制器
 * </p>
 *
 * @author Li Jun
 * @since 2022-04-24
 */
@Slf4j
@RestController
@RequestMapping("/carbonCreditAssets")
@Api(value = "碳信用资产模块", tags = {"碳信用资产模块"})
public class CarbonCreditAssetsController extends BaseController {

    @Autowired
    private CarbonCreditAssetsService carbonCreditAssetsService;

    /**
     * 添加碳信用资产
     */
    @PostMapping("/add")
    @ApiOperation(value = "添加碳信用资产",notes = "添加碳信用资产")
    public ApiResult<Boolean> addCarbonCreditAssets(@Valid @RequestBody CarbonCreditAssetsAddParam param) {
        carbonCreditAssetsService.addCarbonCreditAssets(param);
        return ApiResult.ok();
    }

    /**
     * 修改碳信用资产
     */
    @PutMapping("/update")
    @ApiOperation(value = "修改碳信用资产",notes = "修改碳信用资产")
    public ApiResult<Boolean> updateCarbonCreditAssets(@Valid @RequestBody CarbonCreditAssets carbonCreditAssets) {
        boolean flag = carbonCreditAssetsService.updateById(carbonCreditAssets);
        return ApiResult.result(flag);
    }


    /**
     * 获取碳信用资产
     */
    @GetMapping("/info/{id}")
    @ApiOperation(value = "碳信用资产-详情",notes = "查看碳信用资产")
    public ApiResult<CarbonCreditAssetsQueryVo> getCarbonCreditAssets(@PathVariable String id) {
        CarbonCreditAssetsQueryVo carbonCreditAssetsQueryVo = carbonCreditAssetsService.getCarbonCreditAssetsById(id);
        return ApiResult.ok(carbonCreditAssetsQueryVo);
    }

    /**
     * 碳信用资产分页列表
     */
    @PostMapping("/getPageList")
    @ApiOperation(value = "碳信用资产-分页列表",notes = "碳信用资产分页列表")
    public ApiResult<Paging<CarbonCreditAssetsQueryVo>> getCarbonCreditAssetsPageList(@Valid @RequestBody(required = false) CarbonCreditAssetsQueryParam carbonCreditAssetsQueryParam) {
        Paging<CarbonCreditAssetsQueryVo> paging = carbonCreditAssetsService.getCarbonCreditAssetsPageList(carbonCreditAssetsQueryParam);
        return ApiResult.ok(paging);
    }

    @GetMapping("/assetsTotal")
    @ApiOperation(value = "碳配额资产-总计",notes = "碳配额资产-总计")
    public ApiResult<CarbonAssetsTotalVo> getCarbonAssetsTotal() {
        CarbonAssetsTotalVo vo = carbonCreditAssetsService.getCarbonAssetsTotal();
        return ApiResult.ok(vo);
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除碳信用对象",notes = "删除")
    public ApiResult<Boolean> deleteCarbonCreditAsset(String id) {
        boolean flag = carbonCreditAssetsService.removeById(id);
        return ApiResult.result(flag);
    }

}