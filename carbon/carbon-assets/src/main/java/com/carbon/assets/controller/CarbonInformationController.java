package com.carbon.assets.controller;

import com.carbon.assets.service.CarbonInformationService;
import com.carbon.assets.param.CarbonInformationQueryParam;
import com.carbon.assets.vo.CarbonInformationQueryVo;
import com.carbon.assets.entity.CarbonInformation;
import com.carbon.assets.common.BaseController;
import com.carbon.domain.common.ApiResult;
import com.carbon.common.api.Paging;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


/**
 * <p>
 * 碳资讯 前端控制器
 * </p>
 *
 * @author Li Jun
 * @since 2022-01-11
 */
@Slf4j
@RestController
@RequestMapping("/carbonInformation")
@Api(value = "碳资讯模块", tags = {"碳资讯模块"})
public class CarbonInformationController extends BaseController {

    @Autowired
    private CarbonInformationService carbonInformationService;

    /**
    * 添加碳资讯
    */
    @PostMapping("/add")
    @ApiOperation(value = "添加CarbonInformation对象",notes = "添加碳资讯")
    public ApiResult<Boolean> addCarbonInformation(@Valid @RequestBody CarbonInformation carbonInformation) {
        boolean flag = carbonInformationService.save(carbonInformation);
        return ApiResult.result(flag);
    }

    /**
    * 修改碳资讯
    */
    @PutMapping("/update")
    @ApiOperation(value = "修改CarbonInformation对象",notes = "修改碳资讯")
    public ApiResult<Boolean> updateCarbonInformation(@Valid @RequestBody CarbonInformation carbonInformation) {
        boolean flag = carbonInformationService.updateById(carbonInformation);
        return ApiResult.result(flag);
    }

    /**
    * 删除碳资讯
    */
    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "删除CarbonInformation对象",notes = "删除碳资讯")
    public ApiResult<Boolean> deleteCarbonInformation(@PathVariable String id) {
        boolean flag = carbonInformationService.removeById(id);
        return ApiResult.result(flag);
    }

    /**
    * 获取碳资讯
    */
    @GetMapping("/getRandomList")
    @ApiOperation(value = "随机获取列表",notes = "随机获取固定条数")
    public ApiResult<List<CarbonInformationQueryVo>> getCarbonInformationList(@RequestParam @ApiParam("条数") Integer num) {
        return ApiResult.ok(carbonInformationService.getRandomList(num));
    }

    /**
     * 碳资讯分页列表
     */
    @PostMapping("/getPageList")
    @ApiOperation(value = "获取CarbonInformation分页列表",notes = "碳资讯分页列表")
    public ApiResult<Paging<CarbonInformationQueryVo>> getCarbonInformationPageList(@Valid @RequestBody(required = false) CarbonInformationQueryParam carbonInformationQueryParam) {
        Paging<CarbonInformationQueryVo> paging = carbonInformationService.getCarbonInformationPageList(carbonInformationQueryParam);
        return ApiResult.ok(paging);
    }

}

