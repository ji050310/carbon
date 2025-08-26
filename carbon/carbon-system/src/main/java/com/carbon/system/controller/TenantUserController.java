package com.carbon.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.carbon.common.entity.AddResponse;
import com.carbon.system.mapper.CrmCarbonCreditDetailMapper;
import com.carbon.system.mapper.CrmExchangeRecordMapper;
import com.carbon.system.mapper.CrmWithdrawalRecordMapper;
import com.carbon.system.service.TenantUserService;
import com.carbon.system.param.TenantUserQueryPageParam;
import com.carbon.system.vo.DateQueryDTO;
import com.carbon.system.vo.TenantUserDTO;
import com.carbon.system.vo.TenantUserDetailVo;
import com.carbon.system.vo.TenantUserPageVo;
import com.carbon.system.common.BaseController;
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
 * 租户-用户  前端控制器
 * </p>
 *
 * @author Zhang Jinrui
 * @since 2022-05-03
 */
@Slf4j
@RestController
@RequestMapping("/tenantUser")
@Api(value = "租户-用户 模块", tags = {"租户-用户 模块"})
public class TenantUserController extends BaseController {

    @Autowired
    private TenantUserService tenantUserService;

//    /**
//    * 添加租户-用户
//    */
//    @PostMapping("/add")
//    @ApiOperation(value = "添加TenantUser对象",notes = "添加租户-用户 ")
//    public ApiResult<Boolean> addTenantUser(@Valid @RequestBody TenantUser tenantUser) {
//        boolean flag = tenantUserService.save(tenantUser);
//        return ApiResult.result(flag);
//    }
//
    /**
    * 修改租户-用户
    */
    @PutMapping("/update")
    @ApiOperation(value = "修改TenantUser对象",notes = "修改租户-用户 ")
    public ApiResult updateTenantUser(@RequestBody TenantUserDTO tenantUser) {
        return tenantUserService.updateUser(tenantUser);
    }

    /**
    * 删除租户-用户
    */
    @DeleteMapping("/delete/{userId}")
    @ApiOperation(value = "删除用户",notes = "删除租户-用户 ")
    public ApiResult deleteTenantUser(@PathVariable("userId") String userId) {
        return tenantUserService.removeByUserId(userId);
    }


    /**
     * 查询用户详情
     */
    @GetMapping("/info/{userId}")
    @ApiOperation(value = "查询用户详情（最顶端信息）",notes = "查看租户-用户 ")
    public ApiResult getTenantUser(@PathVariable("userId") String userId) {
        return tenantUserService.getTenantUserById(userId);
    }



    /**
    * 根据条件查询TenantUser
    */
    @GetMapping("/query/{param}")
    @ApiOperation(value = "根据ID或昵称查询用户",notes = "查看租户-用户 ")
    public ApiResult<List<TenantUserPageVo>> queryTenantUser(@PathVariable("param") String param) {
        return tenantUserService.getTenantUserByCondition(param);
    }

    /**
     * 租户-用户 分页列表
     */
    @GetMapping("/getPageList")
    @ApiOperation(value = "获取用户分页列表",notes = "租户-用户 分页列表")
    public ApiResult<Paging<TenantUserPageVo>> getTenantUserPageList(Page page, @Valid TenantUserQueryPageParam tenantUserQueryPageParam) {
        Paging<TenantUserPageVo> paging = tenantUserService.getTenantUserPageList(page, tenantUserQueryPageParam);
        return ApiResult.ok(paging);
    }

    /**
     * 通过日期范围获取指定用户碳信分/兑换记录/提现记录详情
     */
    @GetMapping("/date")
    @ApiOperation(value = "通过日期范围获取指定用户详情",notes = "日期格式（YYYY-MM-DD）")
    public ApiResult getCrmCarbonCreditDetailByDate(DateQueryDTO date) {
        return tenantUserService.getCrmDetailByDate(date);
    }


}

