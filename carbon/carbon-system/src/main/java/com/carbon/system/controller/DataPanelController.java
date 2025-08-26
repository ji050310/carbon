package com.carbon.system.controller;

import  com.carbon.system.service.FeishuFiletokenService;
import com.carbon.system.vo.EnterpriseGreennessVo;
import com.carbon.system.service.DataPanelService;
import com.carbon.system.vo.StatHomeDataVo;
import com.carbon.system.common.BaseController;
import com.carbon.domain.common.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * <p>
 * 数据面板 前端控制器
 * </p>
 *
 * @author Li Jun
 * @since 2021-07-20
 */
@Slf4j
@RestController
@RequestMapping("/dataPanel")
@Api(value = "数据面板模块", description = "数据面板模块", tags = {"数据面板模块"})
public class DataPanelController extends BaseController {

    @Autowired
    private DataPanelService dataPanelService;

    @GetMapping("/home")
    @ApiOperation(value = "获取首页数据", notes = "获取首页数据")
    public ApiResult<StatHomeDataVo> getHomeData(){
        return ApiResult.ok(dataPanelService.getHomeData());
    }

    @GetMapping("/getGreenness")
    @ApiOperation(value = "获取企业绿化数据", notes = "获取企业绿化数据")
    public ApiResult<EnterpriseGreennessVo> getGreenness(){
        return ApiResult.ok(dataPanelService.getGreenness());
    }

}

