package com.carbon.assets.controller;

import com.carbon.assets.service.CarbonMethodologyContentService;
import com.carbon.assets.param.CarbonMethodologyContentQueryParam;
import com.carbon.assets.vo.CarbonMethodologyContentQueryVo;
import com.carbon.assets.entity.CarbonMethodologyContent;
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
 * 碳减排方法学文档 前端控制器
 * </p>
 *
 * @author Cbd
 * @since 2022-08-09
 */
@Slf4j
@RestController
@RequestMapping("/carbonMethodologyContent")
@Api(value = "碳减排方法学文档模块", tags = {"碳减排方法学文档模块"})
public class CarbonMethodologyContentController extends BaseController {

    @Autowired
    private CarbonMethodologyContentService carbonMethodologyContentService;

    /**
    * 添加碳减排方法学文档
    */
    @PostMapping("/add")
    @ApiOperation(value = "添加CarbonMethodologyContent对象",notes = "添加碳减排方法学文档")
    public ApiResult<Boolean> addCarbonMethodologyContent(@Valid @RequestBody CarbonMethodologyContent carbonMethodologyContent) {
        boolean flag = carbonMethodologyContentService.save(carbonMethodologyContent);
        return ApiResult.result(flag);
    }

    /**
    * 修改碳减排方法学文档
    */
    @PutMapping("/update")
    @ApiOperation(value = "修改CarbonMethodologyContent对象",notes = "修改碳减排方法学文档")
    public ApiResult<Boolean> updateCarbonMethodologyContent(@Valid @RequestBody CarbonMethodologyContent carbonMethodologyContent) {
        boolean flag = carbonMethodologyContentService.updateById(carbonMethodologyContent);
        return ApiResult.result(flag);
    }

    /**
    * 删除碳减排方法学文档
    */
    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "删除CarbonMethodologyContent对象",notes = "删除碳减排方法学文档")
    public ApiResult<Boolean> deleteCarbonMethodologyContent(@PathVariable String id) {
        boolean flag = carbonMethodologyContentService.removeById(id);
        return ApiResult.result(flag);
    }

    /**
    * 获取碳减排方法学文档
    */
    @GetMapping("/info/{id}")
    @ApiOperation(value = "获取CarbonMethodologyContent对象详情",notes = "查看碳减排方法学文档")
    public ApiResult<CarbonMethodologyContentQueryVo> getCarbonMethodologyContent(@PathVariable String id) {
        CarbonMethodologyContentQueryVo carbonMethodologyContentQueryVo = carbonMethodologyContentService.getCarbonMethodologyContentById(id);
        return ApiResult.ok(carbonMethodologyContentQueryVo);
    }

    /**
     * 碳减排方法学文档分页列表
     */
    @PostMapping("/getPageList")
    @ApiOperation(value = "获取CarbonMethodologyContent分页列表",notes = "碳减排方法学文档分页列表")
    public ApiResult<Paging<CarbonMethodologyContentQueryVo>> getCarbonMethodologyContentPageList(@Valid @RequestBody(required = false) CarbonMethodologyContentQueryParam carbonMethodologyContentQueryParam) {
        Paging<CarbonMethodologyContentQueryVo> paging = carbonMethodologyContentService.getCarbonMethodologyContentPageList(carbonMethodologyContentQueryParam);
        return ApiResult.ok(paging);
    }

}

