package com.carbon.system.controller;

import com.carbon.system.service.CrmCarbonCreditDetailService;
import com.carbon.system.vo.CrmCarbonCreditDetailVo;
import com.carbon.system.common.BaseController;
import com.carbon.domain.common.ApiResult;
import com.carbon.system.vo.DateQueryDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * <p>
 * 碳信分详情 前端控制器
 * </p>
 *
 * @author Zhang Jinrui
 * @since 2022-05-02
 */
@Slf4j
@RestController
@RequestMapping("/crmCarbonCreditDetail")
@Api(value = "碳信分详情模块", tags = {"碳信分详情模块"})
public class CrmCarbonCreditDetailController extends BaseController {

    @Autowired
    private CrmCarbonCreditDetailService crmCarbonCreditDetailService;

//    /**
//    * 添加碳信分详情
//    */
//    @PostMapping("/add")
//    @ApiOperation(value = "添加CrmCarbonCreditDetail对象",notes = "添加碳信分详情")
//    public ApiResult<Boolean> addCrmCarbonCreditDetail(@Valid @RequestBody CrmCarbonCreditDetail crmCarbonCreditDetail) {
//        boolean flag = crmCarbonCreditDetailService.save(crmCarbonCreditDetail);
//        return ApiResult.result(flag);
//    }
//
//    /**
//    * 修改碳信分详情
//    */
//    @PutMapping("/update")
//    @ApiOperation(value = "修改CrmCarbonCreditDetail对象",notes = "修改碳信分详情")
//    public ApiResult<Boolean> updateCrmCarbonCreditDetail(@Valid @RequestBody CrmCarbonCreditDetail crmCarbonCreditDetail) {
//        boolean flag = crmCarbonCreditDetailService.updateById(crmCarbonCreditDetail);
//        return ApiResult.result(flag);
//    }
//
//    /**
//    * 删除碳信分详情
//    */
//    @DeleteMapping("/delete/{id}")
//    @ApiOperation(value = "删除CrmCarbonCreditDetail对象",notes = "删除碳信分详情")
//    public ApiResult<Boolean> deleteCrmCarbonCreditDetail(@PathVariable String id) {
//        boolean flag = crmCarbonCreditDetailService.removeById(id);
//        return ApiResult.result(flag);
//    }

    /**
    * 获取指定用户碳信分详情
    */
    @GetMapping("/info/{userId}")
    @ApiOperation(value = "获取指定用户碳信分明细",notes = "获取指定用户碳信分详情")
    public ApiResult getCrmCarbonCreditDetail(@PathVariable("userId") String userId) {
        return crmCarbonCreditDetailService.getCrmCarbonCreditDetailByUserId(userId);
    }


    /**
     * 通过日期范围获取指定用户碳信分详情
     */
    @GetMapping("/date")
    @ApiOperation(value = "通过日期范围获取指定用户碳信分详情",notes = "日期格式（YYYY-MM-DD）")
    public ApiResult getCrmCarbonCreditDetailByDate(DateQueryDTO date) {
        return crmCarbonCreditDetailService.getCrmCarbonCreditDetailByDate(date);
    }



//    /**
//     * 碳信分详情分页列表
//     */
//    @PostMapping("/getPageList")
//    @ApiOperation(value = "获取CrmCarbonCreditDetail分页列表",notes = "碳信分详情分页列表")
//    public ApiResult<Paging<CrmCarbonCreditDetailQueryVo>> getCrmCarbonCreditDetailPageList(@Valid @RequestBody(required = false) CrmCarbonCreditDetailQueryParam crmCarbonCreditDetailQueryParam) {
//        Paging<CrmCarbonCreditDetailQueryVo> paging = crmCarbonCreditDetailService.getCrmCarbonCreditDetailPageList(crmCarbonCreditDetailQueryParam);
//        return ApiResult.ok(paging);
//    }

}

