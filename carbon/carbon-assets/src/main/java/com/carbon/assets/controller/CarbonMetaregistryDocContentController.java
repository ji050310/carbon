package com.carbon.assets.controller;

import com.carbon.assets.service.CarbonMetaregistryDocContentService;
import com.carbon.assets.param.CarbonMetaregistryDocContentQueryParam;
import com.carbon.assets.vo.CarbonMetaregistryDocContentQueryVo;
import com.carbon.assets.entity.CarbonMetaregistryDocContent;
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
 * 项目元注册表内容 前端控制器
 * </p>
 *
 * @author Cbd
 * @since 2022-08-15
 */
@Slf4j
@RestController
@RequestMapping("/carbonMetaregistryDocContent")
@Api(value = "项目元注册表内容模块", tags = {"项目元注册表内容模块"})
public class CarbonMetaregistryDocContentController extends BaseController {

    @Autowired
    private CarbonMetaregistryDocContentService carbonMetaregistryDocContentService;

    /**
    * 添加项目元注册表内容
    */
    @PostMapping("/add")
    @ApiOperation(value = "添加CarbonMetaregistryDocContent对象",notes = "添加项目元注册表内容")
    public ApiResult<Boolean> addCarbonMetaregistryDocContent(@Valid @RequestBody CarbonMetaregistryDocContent carbonMetaregistryDocContent) {
        boolean flag = carbonMetaregistryDocContentService.save(carbonMetaregistryDocContent);
        return ApiResult.result(flag);
    }

    /**
    * 修改项目元注册表内容
    */
    @PutMapping("/update")
    @ApiOperation(value = "修改CarbonMetaregistryDocContent对象",notes = "修改项目元注册表内容")
    public ApiResult<Boolean> updateCarbonMetaregistryDocContent(@Valid @RequestBody CarbonMetaregistryDocContent carbonMetaregistryDocContent) {
        boolean flag = carbonMetaregistryDocContentService.updateById(carbonMetaregistryDocContent);
        return ApiResult.result(flag);
    }

    /**
    * 删除项目元注册表内容
    */
    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "删除CarbonMetaregistryDocContent对象",notes = "删除项目元注册表内容")
    public ApiResult<Boolean> deleteCarbonMetaregistryDocContent(@PathVariable String id) {
        boolean flag = carbonMetaregistryDocContentService.removeById(id);
        return ApiResult.result(flag);
    }

    /**
    * 获取项目元注册表内容
    */
    @GetMapping("/info/{id}")
    @ApiOperation(value = "获取CarbonMetaregistryDocContent对象详情",notes = "查看项目元注册表内容")
    public ApiResult<CarbonMetaregistryDocContentQueryVo> getCarbonMetaregistryDocContent(@PathVariable String id) {
        CarbonMetaregistryDocContentQueryVo carbonMetaregistryDocContentQueryVo = carbonMetaregistryDocContentService.getCarbonMetaregistryDocContentById(id);
        return ApiResult.ok(carbonMetaregistryDocContentQueryVo);
    }

    /**
     * 项目元注册表内容分页列表
     */
    @PostMapping("/getPageList")
    @ApiOperation(value = "获取CarbonMetaregistryDocContent分页列表",notes = "项目元注册表内容分页列表")
    public ApiResult<Paging<CarbonMetaregistryDocContentQueryVo>> getCarbonMetaregistryDocContentPageList(@Valid @RequestBody(required = false) CarbonMetaregistryDocContentQueryParam carbonMetaregistryDocContentQueryParam) {
        //
        Paging<CarbonMetaregistryDocContentQueryVo> paging = carbonMetaregistryDocContentService.getCarbonMetaregistryDocContentPageList(carbonMetaregistryDocContentQueryParam);
        return ApiResult.ok(paging);
    }

}

