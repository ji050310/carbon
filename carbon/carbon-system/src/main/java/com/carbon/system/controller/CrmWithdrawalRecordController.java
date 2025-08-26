package com.carbon.system.controller;

import com.carbon.system.service.CrmWithdrawalRecordService;
import com.carbon.system.param.CrmWithdrawalRecordQueryParam;
import com.carbon.system.vo.CrmWithdrawalRecordVo;
import com.carbon.system.entity.CrmWithdrawalRecord;
import com.carbon.system.common.BaseController;
import com.carbon.domain.common.ApiResult;
import com.carbon.common.api.Paging;
import com.carbon.system.vo.DateQueryDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


/**
 * <p>
 * 提现记录 前端控制器
 * </p>
 *
 * @author Zhang Jinrui
 * @since 2022-05-02
 */
@Slf4j
@RestController
@RequestMapping("/crmWithdrawalRecord")
@Api(value = "提现记录模块", tags = {"提现记录模块"})
public class CrmWithdrawalRecordController extends BaseController {

    @Autowired
    private CrmWithdrawalRecordService crmWithdrawalRecordService;

//    /**
//    * 添加提现记录
//    */
//    @PostMapping("/add")
//    @ApiOperation(value = "添加CrmWithdrawalRecord对象",notes = "添加提现记录")
//    public ApiResult<Boolean> addCrmWithdrawalRecord(@Valid @RequestBody CrmWithdrawalRecord crmWithdrawalRecord) {
//        boolean flag = crmWithdrawalRecordService.save(crmWithdrawalRecord);
//        return ApiResult.result(flag);
//    }
//
//    /**
//    * 修改提现记录
//    */
//    @PutMapping("/update")
//    @ApiOperation(value = "修改CrmWithdrawalRecord对象",notes = "修改提现记录")
//    public ApiResult<Boolean> updateCrmWithdrawalRecord(@Valid @RequestBody CrmWithdrawalRecord crmWithdrawalRecord) {
//        boolean flag = crmWithdrawalRecordService.updateById(crmWithdrawalRecord);
//        return ApiResult.result(flag);
//    }
//
//    /**
//    * 删除提现记录
//    */
//    @DeleteMapping("/delete/{id}")
//    @ApiOperation(value = "删除CrmWithdrawalRecord对象",notes = "删除提现记录")
//    public ApiResult<Boolean> deleteCrmWithdrawalRecord(@PathVariable String id) {
//        boolean flag = crmWithdrawalRecordService.removeById(id);
//        return ApiResult.result(flag);
//    }

    /**
    * 根据userId获取提现记录
    */
    @GetMapping("/info/{userId}")
    @ApiOperation(value = "获取指定用户提现记录",notes = "根据userId获取提现记录")
    public ApiResult getCrmWithdrawalRecord(@PathVariable("userId") String userId) {
        return crmWithdrawalRecordService.getCrmWithdrawalRecordByUserId(userId);
    }

    /**
     * 通过日期范围获取指定用户提现记录
     */
    @GetMapping("/date")
    @ApiOperation(value = "通过日期范围获取指定用户提现记录",notes = "日期格式（YYYY-MM-DD）")
    public ApiResult getCrmWithdrawalRecordByDate(DateQueryDTO date) {
        return crmWithdrawalRecordService.getCrmWithdrawalRecordByDate(date);
    }

//    /**
//     * 提现记录分页列表
//     */
//    @PostMapping("/getPageList")
//    @ApiOperation(value = "获取CrmWithdrawalRecord分页列表",notes = "提现记录分页列表")
//    public ApiResult<Paging<CrmWithdrawalRecordVo>> getCrmWithdrawalRecordPageList(@Valid @RequestBody(required = false) CrmWithdrawalRecordQueryParam crmWithdrawalRecordQueryParam) {
//        Paging<CrmWithdrawalRecordVo> paging = crmWithdrawalRecordService.getCrmWithdrawalRecordPageList(crmWithdrawalRecordQueryParam);
//        return ApiResult.ok(paging);
//    }

}

