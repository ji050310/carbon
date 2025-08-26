package com.carbon.assets.controller;

import com.carbon.assets.common.BaseController;
import com.carbon.assets.entity.CarbonAssets;
import com.carbon.assets.param.CarbonAssetsQueryParam;
import com.carbon.assets.service.CarbonAssetsService;
import com.carbon.assets.service.SynMethodologyContentService;
import com.carbon.assets.vo.CarbonAssetsQueryVo;
import com.carbon.common.api.Paging;
import com.carbon.domain.assets.vo.MethodologyUploadParam;
import com.carbon.domain.common.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * <p>
 * 碳中和资产 前端控制器
 * </p>
 *
 * @author Li Jun
 * @since 2021-07-31
 */
@Slf4j
@RestController
@RequestMapping("/methodoloySyn")
public class SynMethodologyContentController extends BaseController {

    @Autowired
    private SynMethodologyContentService synMethodologyContentService;

    /**
     * 修改碳中和资产
     */
    @PostMapping("/synContent")
    @ApiOperation(value = "同步方法学文档内容",notes = "同步方法学文档内容")
    public ApiResult updateCarbonAssets(@Valid @RequestBody MethodologyUploadParam param) {
        synMethodologyContentService.synMethodologyContent(param);
        return ApiResult.ok();
    }
}

