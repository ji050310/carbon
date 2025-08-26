package com.carbon.assets.controller;

import com.carbon.assets.param.BusinessDataQueryParam;
import com.carbon.assets.service.CarbonAssetAssessmentService;
import com.carbon.assets.param.CarbonAssetAssessmentQueryParam;
import com.carbon.assets.vo.CarbonAssetAssessmentQueryVo;
import com.carbon.assets.entity.CarbonAssetAssessment;
import com.carbon.assets.common.BaseController;
import com.carbon.assets.vo.CarbonDetectionDataVo;
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
 *  前端控制器
 * </p>
 *
 * @author Code534
 * @since 2022-07-14
 */
@Slf4j
@RestController
@RequestMapping("/carbonAssetAssessment")
@Api(value = "项目评估模块", tags = {"项目评估模块"})
public class CarbonAssetAssessmentController extends BaseController {

    @Autowired
    private CarbonAssetAssessmentService carbonAssetAssessmentService;

    /**
    * 添加
    */
    @PostMapping("/add")
    @ApiOperation(value = "添加CarbonAssetAssessment对象",notes = "添加")
    public ApiResult<Boolean> addCarbonAssetAssessment(@Valid @RequestBody CarbonAssetAssessment carbonAssetAssessment) {
        boolean flag = carbonAssetAssessmentService.save(carbonAssetAssessment);
        return ApiResult.result(flag);
    }

    /**
    * 修改
    */
    @PutMapping("/update")
    @ApiOperation(value = "修改CarbonAssetAssessment对象",notes = "修改")
    public ApiResult<Boolean> updateCarbonAssetAssessment(@Valid @RequestBody CarbonAssetAssessment carbonAssetAssessment) {
        boolean flag = carbonAssetAssessmentService.updateById(carbonAssetAssessment);
        return ApiResult.result(flag);
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "删除CarbonAssetAssessment对象",notes = "删除")
    public ApiResult<Boolean> deleteCarbonAssetAssessment(@PathVariable String id) {
        boolean flag = carbonAssetAssessmentService.removeById(id);
        return ApiResult.result(flag);
    }

    /**
    * 获取
    */
    @GetMapping("/info/{id}")
    @ApiOperation(value = "获取CarbonAssetAssessment对象详情",notes = "查看")
    public ApiResult<CarbonAssetAssessmentQueryVo> getCarbonAssetAssessment(@PathVariable String id) {
        CarbonAssetAssessmentQueryVo carbonAssetAssessmentQueryVo = carbonAssetAssessmentService.getCarbonAssetAssessmentById(id);
        return ApiResult.ok(carbonAssetAssessmentQueryVo);
    }

    /**
     * 分页列表
     */
    @PostMapping("/getPageList")
    @ApiOperation(value = "获取CarbonAssetAssessment分页列表",notes = "分页列表")
    public ApiResult<Paging<CarbonAssetAssessmentQueryVo>> getCarbonAssetAssessmentPageList(@Valid @RequestBody(required = false) CarbonAssetAssessmentQueryParam carbonAssetAssessmentQueryParam) {
        Paging<CarbonAssetAssessmentQueryVo> paging = carbonAssetAssessmentService.getCarbonAssetAssessmentPageList(carbonAssetAssessmentQueryParam);
        return ApiResult.ok(paging);
    }

    @PostMapping("/getBusinessData")
    @ApiOperation(value = "获取业务数据", notes = "业务数据")
    public ApiResult<CarbonDetectionDataVo>getBusinessData(BusinessDataQueryParam businessDataQueryParam) {

        CarbonDetectionDataVo businessData = carbonAssetAssessmentService.getBusinessData(businessDataQueryParam);

        return ApiResult.ok(businessData);
    }

}

