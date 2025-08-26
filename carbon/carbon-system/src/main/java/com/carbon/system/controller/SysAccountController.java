package com.carbon.system.controller;

import cn.hutool.core.bean.BeanUtil;
import com.carbon.common.api.Paging;
import com.carbon.domain.common.ApiResult;
import com.carbon.domain.system.param.ChangePasswordParam;
import com.carbon.domain.system.param.SysAccountParam;
import com.carbon.domain.system.vo.SysAccountModelVo;
import com.carbon.system.entity.SysAccount;
import com.carbon.system.param.*;
import com.carbon.system.vo.SysAccountQueryVo;
import com.carbon.system.common.BaseController;
import com.carbon.system.service.SysAccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


/**
 * <p>
 * 帐号  前端控制器
 * </p>
 *
 * @author Li Jun
 * @since 2021-06-11
 */
@Slf4j
@RestController
@RequestMapping("/sysAccount")
@Api(value = "账户 模块", tags = {"帐户 模块"})
public class SysAccountController extends BaseController {

    @Autowired
    private SysAccountService sysAccountService;

    @PostMapping("/add")
    @ApiOperation(value = "添加系统账户", notes = "添加系统账户")
    public ApiResult<Boolean> addSysAccount(@Valid @RequestBody SysAccountParam param) {
        sysAccountService.addAccount(param);
        return ApiResult.ok();
    }

    @GetMapping("/info/{id}")
    @ApiOperation(value = "获取账户信息",notes = "查看账户信息")
    public ApiResult<SysAccountQueryVo> getAccount(@PathVariable Long id) {
        SysAccountQueryVo sysAccountQueryVo = sysAccountService.getSysAccountById(id);
        return ApiResult.ok(sysAccountQueryVo);
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改账户信息",notes = "修改账户信息")
    public ApiResult<Boolean> updateAccount(@Valid @RequestBody SysAccountParam param) {
        sysAccountService.updateAccount(param);
        return ApiResult.ok();
    }

    @GetMapping("/list")
    @ApiOperation(value = "账户列表",notes = "账户列表")
    public ApiResult<List<SysAccountModelVo>> getList() {
        return ApiResult.ok(BeanUtil.copyToList(sysAccountService.list(), SysAccountModelVo.class));
    }

    @PostMapping("/getPageList")
    @ApiOperation(value = "账户-分页列表",notes = "获取账户列表")
    public ApiResult<Paging<SysAccountQueryVo>> getSysAccountPageList(@Valid @RequestBody(required = false) SysAccountQueryParam sysAccountQueryParam) {
        Paging<SysAccountQueryVo> paging = sysAccountService.getSysAccountPageList(sysAccountQueryParam);
        return ApiResult.ok(paging);
    }

    @DeleteMapping("delete/{id}")
    @ApiOperation(value = "删除账户",notes = "删除账户")
    public ApiResult<Boolean> deleteAccountById(@PathVariable Long id) {
        sysAccountService.deleteAccountById(id);
        return ApiResult.ok("删除成功");
    }

//    @PutMapping("/update/baseInfo")
//    @ApiOperation(value = "账户-基本信息更新",notes = "修改基本信息")
//    public ApiResult<Boolean> updateAccountBaseInfo(@Valid @RequestBody AccountBaseInfoUpdateParam param) {
//        sysAccountService.updateAccountBaseInfo(param);
//        return ApiResult.ok();
//    }

    @PostMapping("/update/password")
    @ApiOperation(value = "账户-更新密码",notes = "修改密码")
    public ApiResult<Boolean>  updatePassword(@Valid @RequestBody ChangePasswordParam param) {
        sysAccountService.updatePassword(param);
        return ApiResult.ok();
    }

    @PutMapping("/update/avatar")
    @ApiOperation(value = "账户-头像信息更新",notes = "修改头像信息")
    public ApiResult<Boolean> updateAvatar(@Valid @RequestBody ChangeAvatarParam param) {
        sysAccountService.updateAccountAvatar(param);
        return ApiResult.ok();
    }


    @PutMapping("/update/phone")
    @ApiOperation(value = "账户-修改手机号",notes = "修改手机号")
    public ApiResult<Boolean> updatePhone(@Valid @RequestBody ChangePhoneParam param) {
        sysAccountService.updatePhone(param);
        return ApiResult.ok();
    }

    @GetMapping("/update/code/{phone}")
    @ApiOperation(value = "验证码-获取修改手机号验证码", notes = "获取修改验证码")
    public ApiResult<Boolean> sendRegisterCode(@PathVariable String phone) {
        sysAccountService.sendUpdateCode(phone);
        return ApiResult.ok("发送成功");
    }

    @PostMapping("/send/email")
    @ApiOperation(value = "发送绑定链接邮件到客户邮箱",notes = "发送邮件到客户邮箱")
    public ApiResult<Boolean>  sendEmail(@Valid @RequestBody SendEmailParam param) {
        sysAccountService.sendEmail(param);
        return ApiResult.ok("发送成功，等待用户邮箱验证即可绑定成功");
    }

    @GetMapping("/renew/email/{id}/{email}/{value}")
    @ApiOperation(value = "通过点击链接 绑定/修改 邮箱",notes = "绑定/修改邮箱")
    public ApiResult<Boolean>  bindEmail2(@PathVariable Long id,@PathVariable String email,@PathVariable String value) {
        sysAccountService.bindEmail2(id,email,value);
        return ApiResult.ok("绑定成功");
    }


}

