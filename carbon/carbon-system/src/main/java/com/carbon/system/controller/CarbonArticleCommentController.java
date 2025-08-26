package com.carbon.system.controller;

import com.carbon.system.service.CarbonArticleCommentService;
import com.carbon.system.param.CarbonArticleCommentQueryParam;
import com.carbon.system.vo.CarbonArticleCommentQueryVo;
import com.carbon.system.entity.CarbonArticleComment;
import com.carbon.system.common.BaseController;
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
 * @author Jiang zhenhua
 * @since 2022-09-15
 */
@Slf4j
@RestController
@RequestMapping("/carbonArticleComment")
@Api(value = "模块", tags = {"模块"})
public class CarbonArticleCommentController extends BaseController {

    @Autowired
    private CarbonArticleCommentService carbonArticleCommentService;

    /**
    * 添加
    */
    @PostMapping("/add")
    @ApiOperation(value = "添加CarbonArticleComment对象",notes = "添加")
    public ApiResult<Boolean> addCarbonArticleComment(@Valid @RequestBody CarbonArticleComment carbonArticleComment) {
        boolean flag = carbonArticleCommentService.save(carbonArticleComment);
        return ApiResult.result(flag);
    }

    /**
    * 修改
    */
    @PutMapping("/update")
    @ApiOperation(value = "修改CarbonArticleComment对象",notes = "修改")
    public ApiResult<Boolean> updateCarbonArticleComment(@Valid @RequestBody CarbonArticleComment carbonArticleComment) {
        boolean flag = carbonArticleCommentService.updateById(carbonArticleComment);
        return ApiResult.result(flag);
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "删除CarbonArticleComment对象",notes = "删除")
    public ApiResult<Boolean> deleteCarbonArticleComment(@PathVariable String id) {
        boolean flag = carbonArticleCommentService.removeById(id);
        return ApiResult.result(flag);
    }

    /**
    * 获取
    */
    @GetMapping("/info/{id}")
    @ApiOperation(value = "获取CarbonArticleComment对象详情",notes = "查看")
    public ApiResult<CarbonArticleCommentQueryVo> getCarbonArticleComment(@PathVariable String id) {
        CarbonArticleCommentQueryVo carbonArticleCommentQueryVo = carbonArticleCommentService.getCarbonArticleCommentById(id);
        return ApiResult.ok(carbonArticleCommentQueryVo);
    }

    /**
     * 分页列表
     */
    @PostMapping("/getPageList")
    @ApiOperation(value = "获取CarbonArticleComment分页列表",notes = "分页列表")
    public ApiResult<Paging<CarbonArticleCommentQueryVo>> getCarbonArticleCommentPageList(@Valid @RequestBody(required = false) CarbonArticleCommentQueryParam carbonArticleCommentQueryParam) {
        Paging<CarbonArticleCommentQueryVo> paging = carbonArticleCommentService.getCarbonArticleCommentPageList(carbonArticleCommentQueryParam);
        return ApiResult.ok(paging);
    }

}

