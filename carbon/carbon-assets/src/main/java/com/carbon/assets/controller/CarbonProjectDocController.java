package com.carbon.assets.controller;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSONObject;
import com.carbon.assets.entity.CarbonProject;
import com.carbon.assets.service.CarbonProjectDocService;
import com.carbon.assets.param.CarbonProjectDocQueryParam;
import com.carbon.assets.service.CarbonProjectService;
import com.carbon.assets.vo.CarbonProjectDocQueryVo;
import com.carbon.assets.entity.CarbonProjectDoc;
import com.carbon.assets.common.BaseController;
import com.carbon.assets.vo.CarbonProjectQueryVo;
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
 * 碳减排项目文档 前端控制器
 * </p>
 *
 * @author Li Jun
 * @since 2022-04-24
 */
@Slf4j
@RestController
@RequestMapping("/carbonProjectDoc")
@Api(value = "碳减排项目文档模块", tags = {"碳减排项目文档模块"})
public class CarbonProjectDocController extends BaseController {

    @Autowired
    private CarbonProjectDocService carbonProjectDocService;
    @Autowired
    //目前缺失该service层的函数
    //目前方法为空方法，日后再修改
    private CarbonProjectService carbonProjectService;

    /**
     * 添加碳减排项目文档
     */
    @PostMapping("/add")
    @ApiOperation(value = "添加CarbonProjectDoc对象",notes = "添加碳减排项目文档")
    public ApiResult<Boolean> addCarbonProjectDoc(@Valid @RequestBody CarbonProjectDoc carbonProjectDoc) {
        String type = carbonProjectDoc.getType();
        String status ;
        switch (type){
            case "0210000001":status="0100000019";break;
            case "0210000002":status="0100000005";break;
            case "0210000003":status="0100000009";break;
            case "0210000004":status="0100000011";break;
            case "0210000005":status="0100000013";break;
            default:return ApiResult.fail("文档类型错误");
        }
        boolean flag = carbonProjectDocService.save(carbonProjectDoc);
        //根据上传文档类型，更改项目状态
        if(flag){
            CarbonProjectQueryVo carbonProjectById = carbonProjectService.getCarbonProjectById(carbonProjectDoc.getCarbonProjectId());
            carbonProjectById.setProjectStatus(status);
            CarbonProject carbonProject = new CarbonProject();
            BeanUtil.copyProperties(carbonProjectById,carbonProject);
            carbonProjectService.updateById(carbonProject);
        }
        return ApiResult.result(flag);
    }

    /**
     * 修改碳减排项目文档
     */
    @PutMapping("/update")
    @ApiOperation(value = "修改CarbonProjectDoc对象",notes = "修改碳减排项目文档")
    public ApiResult<Boolean> updateCarbonProjectDoc(@Valid @RequestBody CarbonProjectDoc carbonProjectDoc) {
        boolean flag = carbonProjectDocService.updateById(carbonProjectDoc);
        return ApiResult.result(flag);
    }

    /**
     * 删除碳减排项目文档
     */
    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "删除CarbonProjectDoc对象",notes = "删除碳减排项目文档")
    public ApiResult<Boolean> deleteCarbonProjectDoc(@PathVariable String id) {
        boolean flag = carbonProjectDocService.removeById(id);
        return ApiResult.result(flag);
    }

    /**
     * 获取碳减排项目文档
     */
    @GetMapping("/info/{id}")
    @ApiOperation(value = "获取CarbonProjectDoc对象详情",notes = "查看碳减排项目文档")
    public ApiResult<CarbonProjectDocQueryVo> getCarbonProjectDoc(@PathVariable String id) {
        CarbonProjectDocQueryVo carbonProjectDocQueryVo = carbonProjectDocService.getCarbonProjectDocById(id);
        return ApiResult.ok(carbonProjectDocQueryVo);
    }

    /**
     * 碳减排项目文档分页列表
     */
    @PostMapping("/getPageList")
    @ApiOperation(value = "获取CarbonProjectDoc分页列表",notes = "碳减排项目文档分页列表")
    public ApiResult<Paging<CarbonProjectDocQueryVo>> getCarbonProjectDocPageList(@Valid @RequestBody(required = false) CarbonProjectDocQueryParam carbonProjectDocQueryParam) {
        log.info("param: {}", JSONObject.toJSONString(carbonProjectDocQueryParam));
        Paging<CarbonProjectDocQueryVo> paging = carbonProjectDocService.getCarbonProjectDocPageList(carbonProjectDocQueryParam);
        return ApiResult.ok(paging);
    }

}
