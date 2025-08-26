package com.carbon.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.carbon.common.enums.ExpCodeEnum;
import com.carbon.common.exception.CommonBizException;
import com.carbon.domain.system.vo.SysTenantModelVo;
import com.carbon.system.service.SysTenantService;
import com.carbon.system.param.SysTenantQueryParam;
import com.carbon.system.vo.SysTenantQueryVo;
import com.carbon.system.entity.SysTenant;
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
 * 租户  前端控制器
 * </p>
 *
 * @author ChengJX
 * @since 2022-08-24
 */
@Slf4j
@RestController
@RequestMapping("/sysTenant")
@Api(value = "租户 模块", tags = {"租户 模块"})
public class SysTenantController extends BaseController {

    @Autowired
    private SysTenantService sysTenantService;

    /**
    * 添加租户 
    */
    @PostMapping("/add")
    @ApiOperation(value = "添加SysTenant对象",notes = "添加租户 ")
    public ApiResult<Boolean> addSysTenant(@Valid @RequestBody SysTenant sysTenant) {
        LambdaQueryWrapper<SysTenant>  queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysTenant::getOrgName,sysTenant.getOrgName());
        SysTenant t =  sysTenantService.getOne(queryWrapper);
        if(t !=null){
            // code: 50001
            throw new CommonBizException(ExpCodeEnum.SYS_Tenant_OrgName_UNIQUE);
        }
        boolean flag = sysTenantService.save(sysTenant);
        return ApiResult.result(flag);
    }

    /**
    * 修改租户 
    */
    @PutMapping("/update")
    @ApiOperation(value = "修改SysTenant对象",notes = "修改租户 ")
    public ApiResult<Boolean> updateSysTenant(@Valid @RequestBody SysTenant sysTenant) {
        boolean flag = sysTenantService.updateById(sysTenant);
        return ApiResult.result(flag);
    }

    /**
    * 删除租户 
    */
    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "删除SysTenant对象",notes = "删除租户 ")
    public ApiResult<Boolean> deleteSysTenant(@PathVariable String id) {
        boolean flag = sysTenantService.removeById(id);
        return ApiResult.result(flag);
    }

    /**
    * 获取租户 
    */
    @GetMapping("/info/{id}")
    @ApiOperation(value = "获取SysTenant对象详情",notes = "查看租户 ")
    public ApiResult<SysTenantQueryVo> getSysTenant(@PathVariable String id) {
        SysTenantQueryVo sysTenantQueryVo = sysTenantService.getSysTenantById(id);
        return ApiResult.ok(sysTenantQueryVo);
    }

    /**
     * 租户 分页列表
     */
    @PostMapping("/getPageList")
    @ApiOperation(value = "获取SysTenant分页列表",notes = "租户 分页列表")
    public ApiResult<Paging<SysTenantQueryVo>> getSysTenantPageList(@Valid @RequestBody(required = false) SysTenantQueryParam sysTenantQueryParam) {
        Paging<SysTenantQueryVo> paging = sysTenantService.getSysTenantPageList(sysTenantQueryParam);
        return ApiResult.ok(paging);
    }

    /**
     * 获取租户列表
     */
    @GetMapping("/list")
    public ApiResult<List<com.carbon.domain.system.vo.SysTenantModelVo>> getTenantList(){
        return ApiResult.ok(sysTenantService.getSysTenantList());
    };


}

