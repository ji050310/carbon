package com.carbon.system.controller;

import com.carbon.common.api.Paging;
import com.carbon.domain.common.ApiResult;
import com.carbon.domain.common.QueryParam;
import com.carbon.system.param.CarbonArticleQueryParam;
import com.carbon.system.param.CarbonH5ArticleKeyWordSearchParam;
import com.carbon.system.service.CarbonH5ArticleService;
import com.carbon.system.vo.CarbonArticleQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;


@Slf4j
@RestController
@RequestMapping("/carbonH5Article")
@Api(value = "H5碳资讯模块", tags = {"H5碳资讯模块"})
public class CarbonH5ArticleController {

    @Resource
    CarbonH5ArticleService carbonH5ArticleService;

    /**
    * @Description: 分页查询所有资讯
    * @Param:
    * @return:
    * @Author: Code534
    * @Date:
    */
    @PostMapping("/getPageList")
    public ApiResult<Paging<CarbonArticleQueryVo>>getPageList(@Valid @RequestBody(required = false) CarbonArticleQueryParam carbonArticleQueryParam) {
        return ApiResult.ok(carbonH5ArticleService.getCarbonArticlePageList(carbonArticleQueryParam));
    }


    /**
     * 获取碳资讯文章 详情
     */
    @GetMapping("/info/{id}")
    @ApiOperation(value = " 获取碳资讯文章 详情",notes = " 获取碳资讯文章 详情 ")
    public ApiResult<CarbonArticleQueryVo> getSysTenant(@PathVariable Long id) {
        return ApiResult.ok(carbonH5ArticleService.getCarbonH5ArticleById(id));
    }


    @PostMapping("/getByKeyWord")
    @ApiOperation(value = "搜索碳资讯文章",notes = "小程序搜索")
    public ApiResult getArticleByKeyWord(@Valid @RequestBody(required = false) CarbonH5ArticleKeyWordSearchParam param) {
        return ApiResult.ok(carbonH5ArticleService.searchArticleByKeyword(param));
    }


    @PostMapping("/getByKeyWord/default")
    @ApiOperation(value = "默认全站搜索",notes = "小程序搜索")
    public ApiResult getMethodologyByKeyWord(@RequestBody H5SearchDefaultParam param) {
        return ApiResult.ok(carbonH5ArticleService.searchByKeyword(param));
    }

    @PostMapping("/getByKeyWord/methodology")
    @ApiOperation(value = "搜索方法学",notes = "小程序搜索")
    public ApiResult getMethodologyByKeyWord(@Valid @RequestBody(required = false) H5SearchMethodologyParam param) {
        return ApiResult.ok(carbonH5ArticleService.searchMethodologyByKeyword(param));
    }


    @Data
    public static class H5SearchMethodologyParam  extends QueryParam {
        @ApiModelProperty(value = "搜索关键字")
        private String keyword;
        @ApiModelProperty(value = "领域编码（字典：003）")
        private String fieldCode;
        @ApiModelProperty(value = "行业编码（字典：002）")
        private String industryCode;

    }

    @Data
    public static class H5SearchDefaultParam  extends QueryParam {
        @ApiModelProperty(value = "搜索关键字")
        private String keyword;
    }
}
