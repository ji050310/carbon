package com.carbon.system.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.carbon.system.entity.CarbonArticleComment;
import com.carbon.system.mapper.CarbonArticleCommentMapper;
import com.carbon.system.param.CarbonArticleParam;
import com.carbon.system.common.BaseController;
import com.carbon.system.param.CarbonArticleStatuParam;
import com.carbon.system.service.CarbonArticleService;
import com.carbon.system.param.CarbonArticleQueryParam;
import com.carbon.system.service.FeishuFiletokenService;
import com.carbon.system.vo.CarbonArticleAddVo;
import com.carbon.system.vo.CarbonArticleQueryVo;
import com.carbon.system.entity.CarbonArticle;
import com.carbon.domain.common.ApiResult;
import com.carbon.common.api.Paging;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;


/**
 * <p>
 * 碳文章 前端控制器
 * </p>
 *
 * @author Li Jun
 * @since 2021-08-01
 */
@Slf4j
@RestController
@RequestMapping("/carbonArticle")
@Api(value = "碳文章模块", tags = {"碳文章模块"})
public class CarbonArticleController extends BaseController {

    @Autowired
    private CarbonArticleService carbonArticleService;

    @Autowired
    FeishuFiletokenService feishuFiletokenService;

    /**
     * 添加碳文章
     */
    @PostMapping("/add")
    @ApiOperation(value = "添加碳文章", notes = "添加碳文章")
    public ApiResult<CarbonArticleAddVo> addCarbonArticle(){
        //写死的接口
        CarbonArticleAddVo carbonArticleAddVo = carbonArticleService.testFeishu2();
        return ApiResult.ok(carbonArticleAddVo);
    }
    /**
     * 添加碳文章
     */
    @PostMapping("/push")
    @ApiOperation(value = "添加碳文章",notes = "添加碳文章")
    public ApiResult<CarbonArticleAddVo> pushArticle(@RequestBody JSONObject jsonObject) {
        CarbonArticle carbonArticle = JSONUtil.toBean(jsonObject, CarbonArticle.class);
        //暂时调写死的接口
        CarbonArticleAddVo vo = carbonArticleService.pushFeishu(carbonArticle);
        return ApiResult.ok(vo);
    }


    /**
     * 修改碳文章
     */
    @PutMapping("/update")
    @ApiOperation(value = "修改碳文章",notes = "修改碳文章")
    public ApiResult<Boolean> updateCarbonArticle(@Valid @RequestBody CarbonArticleParam param) {
        CarbonArticle carbonArticle = new CarbonArticle();
        BeanUtil.copyProperties(param,carbonArticle);
        boolean flag = carbonArticleService.updateById(carbonArticle);
        return ApiResult.result(flag);
    }

    /**
     * 删除碳文章
     */
    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "删除碳文章",notes = "删除碳文章")
    public ApiResult<Boolean> deleteCarbonArticle(@PathVariable String id) {
        boolean flag = carbonArticleService.removeById(id);
        return ApiResult.result(flag);
    }

    /**
     * 获取碳文章
     */
    @GetMapping("/info/{id}")
    @ApiOperation(value = "获取文章详情",notes = "查看碳文章")
    public ApiResult<CarbonArticleQueryVo> getCarbonArticle(@PathVariable String id) {
        CarbonArticleQueryVo carbonArticleQueryVo = carbonArticleService.getCarbonArticleById(id);
        return ApiResult.ok(carbonArticleQueryVo);
    }

    /**
     * 碳文章分页列表(可带条件查询)
     */
    @PostMapping("/getPageList")
    @ApiOperation(value = "获取文章分页列表(可带条件查询)",notes = "碳文章分页列表")
    public ApiResult<Paging<CarbonArticleQueryVo>> getCarbonArticlePageList(@Valid @RequestBody(required = false) CarbonArticleQueryParam carbonArticleQueryParam) {
        Paging<CarbonArticleQueryVo> paging = carbonArticleService.getCarbonArticlePageList(carbonArticleQueryParam);
        return ApiResult.ok(paging);
    }

    /**
     * 碳文章的发布和下线（文章的状态修改）
     */
    @PutMapping("/updateStatu")
    @ApiOperation(value = "根据文章id修改文章的状态", notes = "修改文章的状态")
    public ApiResult<Boolean> updateArticleStatu(@Valid @RequestBody CarbonArticleStatuParam param){
        boolean flag = carbonArticleService.updateArticleStatu(param);
        return ApiResult.result(flag);
    }

    /**
     * 同步碳文章(刷新碳文章列表)
     */
    @PostMapping("/getRefreshList")
    @ApiOperation(value = "同步碳文章(刷新碳文章列表)",notes = "同步碳文章")
    public ApiResult getCarbonArticleRefreshPageList() {
        try
        {
            carbonArticleService.SyncArticle(feishuFiletokenService.getDocToken());
            return ApiResult.ok("文章同步成功");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ApiResult.fail("文章同步失败");
        }
    }



}

