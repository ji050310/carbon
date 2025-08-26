package com.carbon.assets.controller;

import com.carbon.assets.service.CarbonMetaregistryService;
import com.carbon.assets.param.CarbonMetaregistryQueryParam;
import com.carbon.assets.vo.CarbonMetaregistryQueryVo;
import com.carbon.assets.entity.CarbonMetaregistry;
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
 * 项目仓库 前端控制器
 * </p>
 *
 * @author ChengJX
 * @since 2022-08-09
 */
@Slf4j
@RestController
@RequestMapping("/carbonMetaregistry")
@Api(value = "项目仓库模块", tags = {"项目仓库模块"})
public class CarbonMetaregistryController extends BaseController {

    @Autowired
    private CarbonMetaregistryService carbonMetaregistryService;

    /**
    * 添加项目仓库
    */
    @PostMapping("/add")
    @ApiOperation(value = "添加CarbonMetaregistry对象",notes = "添加项目仓库")
    public ApiResult<Boolean> addCarbonMetaregistry(@Valid @RequestBody CarbonMetaregistry carbonMetaregistry) {
        boolean flag = carbonMetaregistryService.save(carbonMetaregistry);
        return ApiResult.result(flag);
    }

    /**
    * 修改项目仓库
    */
    @PutMapping("/update")
    @ApiOperation(value = "修改CarbonMetaregistry对象",notes = "修改项目仓库")
    public ApiResult<Boolean> updateCarbonMetaregistry(@Valid @RequestBody CarbonMetaregistry carbonMetaregistry) {
        boolean flag = carbonMetaregistryService.updateById(carbonMetaregistry);
        return ApiResult.result(flag);
    }

    /**
    * 删除项目仓库
    */
    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "删除CarbonMetaregistry对象",notes = "删除项目仓库")
    public ApiResult<Boolean> deleteCarbonMetaregistry(@PathVariable String id) {
        boolean flag = carbonMetaregistryService.removeById(id);
        return ApiResult.result(flag);
    }

    /**
    * 获取项目仓库
    */
    @GetMapping("/info/{id}")
    @ApiOperation(value = "获取CarbonMetaregistry对象详情",notes = "查看项目仓库")
    public ApiResult<CarbonMetaregistryQueryVo> getCarbonMetaregistry(@PathVariable String id) {
        CarbonMetaregistryQueryVo carbonMetaregistryQueryVo = carbonMetaregistryService.getCarbonMetaregistryById(id);
        return ApiResult.ok(carbonMetaregistryQueryVo);
    }

    /**
     * 项目仓库分页列表
     */
    @PostMapping("/getPageList")
    @ApiOperation(value = "获取CarbonMetaregistry分页列表",notes = "项目仓库分页列表")
    public ApiResult<Paging<CarbonMetaregistryQueryVo>> getCarbonMetaregistryPageList(@Valid @RequestBody(required = false) CarbonMetaregistryQueryParam carbonMetaregistryQueryParam) {
        Paging<CarbonMetaregistryQueryVo> paging = carbonMetaregistryService.getCarbonMetaregistryPageList(carbonMetaregistryQueryParam);
        return ApiResult.ok(paging);
    }

}

