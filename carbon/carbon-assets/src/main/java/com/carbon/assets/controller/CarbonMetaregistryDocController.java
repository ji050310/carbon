package com.carbon.assets.controller;

import com.carbon.assets.service.CarbonMetaregistryDocService;
import com.carbon.assets.param.CarbonMetaregistryDocQueryParam;
import com.carbon.assets.vo.CarbonMetaregistryDocQueryVo;
import com.carbon.assets.entity.CarbonMetaregistryDoc;
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
 *  前端控制器
 * </p>
 *
 * @author ChengJX
 * @since 2022-08-08
 */
@Slf4j
@RestController
@RequestMapping("/carbonMetaregistryDoc")
@Api(value = "项目文档管理模块", tags = {"项目文档管理模块"})
public class CarbonMetaregistryDocController extends BaseController {




    @Autowired
    private CarbonMetaregistryDocService carbonMetaregistryDocService;

    /**
    * 添加
    */
    @PostMapping("/add")
    @ApiOperation(value = "添加CarbonMetaregistryDoc对象",notes = "添加")
    public ApiResult<Boolean> addCarbonMetaregistryDoc(@Valid @RequestBody CarbonMetaregistryDoc carbonMetaregistryDoc) {
        boolean flag = carbonMetaregistryDocService.save(carbonMetaregistryDoc);
        return ApiResult.result(flag);
    }

    /**
    * 修改
    */
    @PutMapping("/update")
    @ApiOperation(value = "修改CarbonMetaregistryDoc对象",notes = "修改")
    public ApiResult<Boolean> updateCarbonMetaregistryDoc(@Valid @RequestBody CarbonMetaregistryDoc carbonMetaregistryDoc) {
        boolean flag = carbonMetaregistryDocService.updateById(carbonMetaregistryDoc);
        return ApiResult.result(flag);
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "删除CarbonMetaregistryDoc对象",notes = "删除")
    public ApiResult<Boolean> deleteCarbonMetaregistryDoc(@PathVariable String id) {
        boolean flag = carbonMetaregistryDocService.removeById(id);
        return ApiResult.result(flag);
    }

    /**
    * 获取
    */
    @GetMapping("/info/{id}")
    @ApiOperation(value = "获取CarbonMetaregistryDoc对象详情",notes = "查看")
    public ApiResult<CarbonMetaregistryDocQueryVo> getCarbonMetaregistryDoc(@PathVariable String id) {
        CarbonMetaregistryDocQueryVo carbonMetaregistryDocQueryVo = carbonMetaregistryDocService.getCarbonMetaregistryDocById(id);
        return ApiResult.ok(carbonMetaregistryDocQueryVo);
    }

    /**
     * 分页列表
     */
    @PostMapping("/getPageList")
    @ApiOperation(value = "获取CarbonMetaregistryDoc分页列表",notes = "分页列表")
    public ApiResult<Paging<CarbonMetaregistryDocQueryVo>> getCarbonMetaregistryDocPageList(@Valid @RequestBody(required = false) CarbonMetaregistryDocQueryParam carbonMetaregistryDocQueryParam) {
        Paging<CarbonMetaregistryDocQueryVo> paging = carbonMetaregistryDocService.getCarbonMetaregistryDocPageList(carbonMetaregistryDocQueryParam);
        return ApiResult.ok(paging);
    }

}

