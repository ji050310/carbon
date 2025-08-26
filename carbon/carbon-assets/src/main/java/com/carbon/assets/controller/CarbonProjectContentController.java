package com.carbon.assets.controller;

import com.carbon.assets.service.CarbonProjectContentService;
import com.carbon.assets.param.CarbonProjectContentQueryParam;
import com.carbon.assets.vo.CarbonProjectContentQueryVo;
import com.carbon.assets.entity.CarbonProjectContent;
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
 * 碳减排项目文档内容 前端控制器
 * </p>
 *
 * @author Cbd
 * @since 2022-08-10
 */
@Slf4j
@RestController
@RequestMapping("/carbonProjectContent")
@Api(value = "碳减排项目文档内容模块", tags = {"碳减排项目文档内容模块"})
public class CarbonProjectContentController extends BaseController {

    @Autowired
    private CarbonProjectContentService carbonProjectContentService;

    /**
    * 添加碳减排项目文档内容
    */
    @PostMapping("/add")
    @ApiOperation(value = "添加CarbonProjectContent对象",notes = "添加碳减排项目文档内容")
    public ApiResult<Boolean> addCarbonProjectContent(@Valid @RequestBody CarbonProjectContent carbonProjectContent) {
        boolean flag = carbonProjectContentService.save(carbonProjectContent);
        return ApiResult.result(flag);
    }

    /**
    * 修改碳减排项目文档内容
    */
    @PutMapping("/update")
    @ApiOperation(value = "修改CarbonProjectContent对象",notes = "修改碳减排项目文档内容")
    public ApiResult<Boolean> updateCarbonProjectContent(@Valid @RequestBody CarbonProjectContent carbonProjectContent) {
        boolean flag = carbonProjectContentService.updateById(carbonProjectContent);
        return ApiResult.result(flag);
    }

    /**
    * 删除碳减排项目文档内容
    */
    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "删除CarbonProjectContent对象",notes = "删除碳减排项目文档内容")
    public ApiResult<Boolean> deleteCarbonProjectContent(@PathVariable String id) {
        boolean flag = carbonProjectContentService.removeById(id);
        return ApiResult.result(flag);
    }

    /**
    * 获取碳减排项目文档内容
    */
    @GetMapping("/info/{id}")
    @ApiOperation(value = "获取CarbonProjectContent对象详情",notes = "查看碳减排项目文档内容")
    public ApiResult<CarbonProjectContentQueryVo> getCarbonProjectContent(@PathVariable String id) {
        CarbonProjectContentQueryVo carbonProjectContentQueryVo = carbonProjectContentService.getCarbonProjectContentById(id);
        return ApiResult.ok(carbonProjectContentQueryVo);
    }

    /**
     * 碳减排项目文档内容分页列表
     */
    @PostMapping("/getPageList")
    @ApiOperation(value = "获取CarbonProjectContent分页列表",notes = "碳减排项目文档内容分页列表")
    public ApiResult<Paging<CarbonProjectContentQueryVo>> getCarbonProjectContentPageList(@Valid @RequestBody(required = false) CarbonProjectContentQueryParam carbonProjectContentQueryParam) {
        Paging<CarbonProjectContentQueryVo> paging = carbonProjectContentService.getCarbonProjectContentPageList(carbonProjectContentQueryParam);
        return ApiResult.ok(paging);
    }

}

