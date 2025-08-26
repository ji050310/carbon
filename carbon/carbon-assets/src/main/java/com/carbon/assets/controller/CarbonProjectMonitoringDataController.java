package com.carbon.assets.controller;

import com.carbon.assets.service.CarbonProjectMonitoringDataService;
import com.carbon.assets.param.CarbonProjectMonitoringDataQueryParam;
import com.carbon.assets.vo.CarbonProjectMonitoringDataQueryVo;
import com.carbon.assets.entity.CarbonProjectMonitoringData;
import com.carbon.assets.common.BaseController;
import com.carbon.domain.common.ApiResult;
import com.carbon.common.api.Paging;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


/**
 * <p>
 * 碳减排项目监测数据 前端控制器
 * </p>
 *
 * @author Li Jun
 * @since 2022-05-11
 */
@Slf4j
@RestController
@RequestMapping("/carbonProjectMonitoringData")
@Api(value = "碳减排项目监测数据模块", tags = {"碳减排项目监测数据模块"})
public class CarbonProjectMonitoringDataController extends BaseController {

    @Autowired
    private CarbonProjectMonitoringDataService carbonProjectMonitoringDataService;

    /**
    * 添加碳减排项目监测数据
    */
    @PostMapping("/add")
    @ApiOperation(value = "添加项目监测数据",notes = "添加碳减排项目监测数据")
    public ApiResult<Boolean> addCarbonProjectMonitoringData(@Valid @RequestBody CarbonProjectMonitoringData carbonProjectMonitoringData) {
        boolean flag = carbonProjectMonitoringDataService.save(carbonProjectMonitoringData);
        return ApiResult.result(flag);
    }

    /**
    * 修改碳减排项目监测数据
    */
    @PutMapping("/update")
    @ApiOperation(value = "修改项目监测数据",notes = "修改碳减排项目监测数据")
    public ApiResult<Boolean> updateCarbonProjectMonitoringData(@Valid @RequestBody CarbonProjectMonitoringData carbonProjectMonitoringData) {
        boolean flag = carbonProjectMonitoringDataService.updateById(carbonProjectMonitoringData);
        return ApiResult.result(flag);
    }

    /**
    * 获取碳减排项目监测数据
    */
    @GetMapping("/info/{id}")
    @ApiOperation(value = "查看项目监测数据",notes = "查看碳减排项目监测数据")
    public ApiResult<CarbonProjectMonitoringDataQueryVo> getCarbonProjectMonitoringData(@PathVariable String id) {
        CarbonProjectMonitoringDataQueryVo carbonProjectMonitoringDataQueryVo = carbonProjectMonitoringDataService.getCarbonProjectMonitoringDataById(id);
        return ApiResult.ok(carbonProjectMonitoringDataQueryVo);
    }

    /**
     * 碳减排项目监测数据分页列表
     */
    @PostMapping("/getList/{projectId}")
    @ApiOperation(value = "监测数据列表",notes = "监测数据列表")
    public ApiResult<List<CarbonProjectMonitoringDataQueryVo>> getListByProjectId(@PathVariable Long projectId) {
        return ApiResult.ok(carbonProjectMonitoringDataService.getListByProjectId(projectId));
    }

    /**
     * 碳减排项目监测数据分页列表
     */
//    @PostMapping("/getPageList")
//    @ApiOperation(value = "获取CarbonProjectMonitoringData分页列表",notes = "碳减排项目监测数据分页列表")
//    public ApiResult<Paging<CarbonProjectMonitoringDataQueryVo>> getCarbonProjectMonitoringDataPageList(@Valid @RequestBody(required = false) CarbonProjectMonitoringDataQueryParam carbonProjectMonitoringDataQueryParam) {
//        Paging<CarbonProjectMonitoringDataQueryVo> paging = carbonProjectMonitoringDataService.getCarbonProjectMonitoringDataPageList(carbonProjectMonitoringDataQueryParam);
//        return ApiResult.ok(paging);
//    }

}

