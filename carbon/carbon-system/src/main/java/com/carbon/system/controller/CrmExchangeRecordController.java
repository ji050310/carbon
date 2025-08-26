package com.carbon.system.controller;

import com.carbon.system.service.CrmExchangeRecordService;
import com.carbon.system.param.CrmExchangeRecordQueryParam;
import com.carbon.system.vo.CrmExchangeRecordVo;
import com.carbon.system.entity.CrmExchangeRecord;
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
 * 兑换记录 前端控制器
 * </p>
 *
 * @author Zhang Jinrui
 * @since 2022-05-02
 */
@Slf4j
@RestController
@RequestMapping("/crmExchangeRecord")
@Api(value = "兑换记录模块", tags = {"兑换记录模块"})
public class CrmExchangeRecordController extends BaseController {

    @Autowired
    private CrmExchangeRecordService crmExchangeRecordService;

//    /**
//    * 添加兑换记录
//    */
//    @PostMapping("/add")
//    @ApiOperation(value = "添加CrmExchangeRecord对象",notes = "添加兑换记录")
//    public ApiResult<Boolean> addCrmExchangeRecord(@Valid @RequestBody CrmExchangeRecord crmExchangeRecord) {
//        boolean flag = crmExchangeRecordService.save(crmExchangeRecord);
//        return ApiResult.result(flag);
//    }
//
//    /**
//    * 修改兑换记录
//    */
//    @PutMapping("/update")
//    @ApiOperation(value = "修改CrmExchangeRecord对象",notes = "修改兑换记录")
//    public ApiResult<Boolean> updateCrmExchangeRecord(@Valid @RequestBody CrmExchangeRecord crmExchangeRecord) {
//        boolean flag = crmExchangeRecordService.updateById(crmExchangeRecord);
//        return ApiResult.result(flag);
//    }
//
//    /**
//    * 删除兑换记录
//    */
//    @DeleteMapping("/delete/{id}")
//    @ApiOperation(value = "删除CrmExchangeRecord对象",notes = "删除兑换记录")
//    public ApiResult<Boolean> deleteCrmExchangeRecord(@PathVariable String id) {
//        boolean flag = crmExchangeRecordService.removeById(id);
//        return ApiResult.result(flag);
//    }

    /**
    * 获取指定用户兑换记录
    */
    @GetMapping("/info/{userId}")
    @ApiOperation(value = "获取指定用户兑换记录",notes = "获取指定用户兑换记录")
    public ApiResult getCrmExchangeRecord(@PathVariable("userId") String userId) {
        return crmExchangeRecordService.getCrmExchangeRecordByUserId(userId);
    }

    /**
     * 通过日期范围获取指定用户兑换记录
     */
    @GetMapping("/date")
    @ApiOperation(value = "通过日期范围获取指定用户兑换记录",notes = "日期格式（YYYY-MM-DD）")
    public ApiResult getCrmExchangeRecordByDate(DateQueryDTO date) {
        return crmExchangeRecordService.getCrmExchangeRecordByDate(date);
    }

//    /**
//     * 兑换记录分页列表
//     */
//    @PostMapping("/getPageList")
//    @ApiOperation(value = "获取CrmExchangeRecord分页列表",notes = "兑换记录分页列表")
//    public ApiResult<Paging<CrmExchangeRecordVo>> getCrmExchangeRecordPageList(@Valid @RequestBody(required = false) CrmExchangeRecordQueryParam crmExchangeRecordQueryParam) {
//        Paging<CrmExchangeRecordVo> paging = crmExchangeRecordService.getCrmExchangeRecordPageList(crmExchangeRecordQueryParam);
//        return ApiResult.ok(paging);
//    }

}

