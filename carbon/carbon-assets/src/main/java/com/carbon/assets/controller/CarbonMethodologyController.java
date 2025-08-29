package com.carbon.assets.controller;

import com.carbon.domain.assets.vo.MethodologyUploadParam;
import com.carbon.assets.repository.MethodologyRepository;
import com.carbon.assets.service.CarbonMethodologyService;
import com.carbon.assets.param.CarbonMethodologyQueryParam;
import com.carbon.assets.service.SynMethodologyContentService;
import com.carbon.assets.vo.CarbonMethodologyQueryVo;
import com.carbon.assets.entity.CarbonMethodology;
import com.carbon.assets.common.BaseController;
import com.carbon.assets.vo.CarbonMethodologySelectVo;
import com.carbon.domain.common.ApiResult;
import com.carbon.common.api.Paging;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.carbon.domain.sentinel.CommonSentinelHandler;
import com.carbon.assets.sentinel.AssetsSentinelHandler;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/carbonMethodology")
@Api(value = "碳减排方法学模块", tags = {"碳减排方法学模块"})
public class CarbonMethodologyController extends BaseController {

    @Autowired
    private CarbonMethodologyService carbonMethodologyService;

    @Autowired
    private MethodologyRepository methodologyRepository;

    @Autowired
    private SynMethodologyContentService synMethodologyContentService;

    /**
     * 添加碳减排方法学
     */
    @PostMapping("/upload")
    @ApiOperation(value = "添加碳减排方法学",notes = "添加碳减排方法学")
    public ApiResult addCarbonMethodology(@Valid @RequestBody MethodologyUploadParam param) {
        CarbonMethodology query = carbonMethodologyService.lambdaQuery()
                .select(CarbonMethodology.class,i->i.getProperty().equals("created_time")||i.getProperty().equals("updated_time"))
                .eq(CarbonMethodology::getDictCode,param.getDictCode())
                .one();
        if(query!=null)
        {
            return ApiResult.fail("字典编码已存在");
        }
        try {
            carbonMethodologyService.addCarbonMethodology(param);
        }catch (DataIntegrityViolationException e)
        {
            return ApiResult.fail("请先添加对应的字段编码");
        }

        return ApiResult.ok();
    }

    /**
     * 修改碳减排方法学
     */
    @PutMapping("/update")
    @ApiOperation(value = "修改碳减排方法学",notes = "修改碳减排方法学")
    public ApiResult<Boolean> updateCarbonMethodology(@Valid @RequestBody CarbonMethodology carbonMethodology) {
        System.out.println(carbonMethodology);
        boolean flag = carbonMethodologyService.updateCarbonMethodology(carbonMethodology);
        return ApiResult.result(flag);
    }

    /**
     * 获取碳减排方法学
     */
    @GetMapping("/info/{id}")
    @ApiOperation(value = "查看碳减排方法学",notes = "查看碳减排方法学")
    public ApiResult<CarbonMethodologyQueryVo> getCarbonMethodology(@PathVariable String id) {
        CarbonMethodologyQueryVo carbonMethodologyQueryVo = carbonMethodologyService.getCarbonMethodologyById(id);
        return ApiResult.ok(carbonMethodologyQueryVo);
    }

    /**
     * 碳减排方法学分页列表
     */
    @PostMapping("/getPageList")
    @ApiOperation(value = "碳减排方法学分页列表",notes = "碳减排方法学分页列表")
    @SentinelResource(value = "getCarbonMethodologyPageList",
            blockHandler = "handleGetCarbonMethodologyPageList",
            blockHandlerClass = AssetsSentinelHandler.class,
            fallback = "fallback",
            fallbackClass = CommonSentinelHandler.class)
    public ApiResult<Paging<CarbonMethodologyQueryVo>> getCarbonMethodologyPageList(@Valid @RequestBody(required = false) CarbonMethodologyQueryParam carbonMethodologyQueryParam) {
        Paging<CarbonMethodologyQueryVo> paging = carbonMethodologyService.getCarbonMethodologyPageList(carbonMethodologyQueryParam);
        return ApiResult.ok(paging);
    }

    /**
     * 获取碳减排方法学列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "方法学下拉列表",notes = "方法学下拉列表")
    @SentinelResource(value = "getCarbonMethodologyList",
            blockHandler = "handleGetCarbonMethodologyList",
            blockHandlerClass = AssetsSentinelHandler.class,
            fallback = "fallback",
            fallbackClass = CommonSentinelHandler.class)
    public ApiResult<List<CarbonMethodologySelectVo>> getCarbonMethodologyList() {
        return ApiResult.ok(carbonMethodologyService.getCarbonMethodologyList());
    }

    @GetMapping("/gbn/{name}")
    public CarbonMethodology getCarbonMethodologyByName(@PathVariable String name) {
        System.out.println(name);
        try {
            return carbonMethodologyService.getCarbonMethodologyByName(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}