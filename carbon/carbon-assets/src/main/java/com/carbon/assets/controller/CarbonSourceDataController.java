package com.carbon.assets.controller;

import com.carbon.assets.service.CarbonSourceDataService;
import com.carbon.assets.param.CarbonSourceDataQueryParam;
import com.carbon.assets.vo.CarbonSourceDataQueryVo;
import com.carbon.assets.entity.CarbonSourceData;
import com.carbon.assets.common.BaseController;
import com.carbon.common.api.Paging;
import com.carbon.domain.common.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * <p>
 * 碳源数据 前端控制器
 * </p>
 *
 * @author Li Jun
 * @since 2021-08-07
 */
@Slf4j
@RestController
@RequestMapping("/carbonSourceData")
@Api(value = "碳源数据模块", tags = {"碳源数据模块"})
public class CarbonSourceDataController extends BaseController {

    @Autowired
    private CarbonSourceDataService carbonSourceDataService;

    /**
    * 添加碳源数据
    */
    @PostMapping("/add")
    @ApiOperation(value = "添加碳源数据",notes = "添加碳源数据")
    public ApiResult<Boolean> addCarbonSourceData(@Valid @RequestBody CarbonSourceData carbonSourceData) {
        carbonSourceDataService.addCarbonSourceData(carbonSourceData);
        return ApiResult.ok();
    }

    /**
    * 修改碳源数据
    */
    @PutMapping("/update")
    @ApiOperation(value = "修改碳源数据",notes = "修改碳源数据")
    public ApiResult<Boolean> updateCarbonSourceData(@Valid @RequestBody CarbonSourceData carbonSourceData) {
        boolean flag = carbonSourceDataService.updateCarbonSourceData(carbonSourceData);
        return ApiResult.result(flag);
    }

    /**
    * 删除碳源数据
    */
    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "删除碳源数据",notes = "删除碳源数据")
    public ApiResult<Boolean> deleteCarbonSourceData(@PathVariable String id) {
        boolean flag = carbonSourceDataService.removeById(id);
        return ApiResult.result(flag);
    }

    /**
    * 获取碳源数据
    */
    @GetMapping("/info/{id}")
    @ApiOperation(value = "查看碳源数据",notes = "查看碳源数据")
    public ApiResult<CarbonSourceDataQueryVo> getCarbonSourceData(@PathVariable Long id) {
        CarbonSourceDataQueryVo carbonSourceDataQueryVo = carbonSourceDataService.getCarbonSourceDataById(id);
        return ApiResult.ok(carbonSourceDataQueryVo);
    }

    /**
     * 碳源数据分页列表
     */
    @PostMapping("/getPageList")
    @ApiOperation(value = "碳源数据分页列表",notes = "碳源数据分页列表")
    public ApiResult<Paging<CarbonSourceDataQueryVo>> getCarbonSourceDataPageList(@Valid @RequestBody(required = false) CarbonSourceDataQueryParam carbonSourceDataQueryParam) {
        Paging<CarbonSourceDataQueryVo> paging = carbonSourceDataService.getCarbonSourceDataPageList(carbonSourceDataQueryParam);
        return ApiResult.ok(paging);
    }


    /**
     * 碳源数据报送
     */
    @PostMapping("/submitted/{id}")
    @ApiOperation(value = "碳源数据报送",notes = "碳源数据报送")
    public ApiResult<Boolean> submitted(@PathVariable String id) {
        carbonSourceDataService.submitted(id);
        return ApiResult.ok();
    }
}

