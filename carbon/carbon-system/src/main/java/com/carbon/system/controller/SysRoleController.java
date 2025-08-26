package com.carbon.system.controller;

import com.carbon.common.api.Paging;
import com.carbon.domain.common.ApiResult;
import com.carbon.system.entity.SysRole;
import com.carbon.system.entity.SysRoleMenu;
import com.carbon.system.param.SysRoleMenuAddParam;
import com.carbon.system.param.SysRoleQueryParam;
import com.carbon.system.vo.SysRoleQueryVo;
import com.carbon.system.common.BaseController;
import com.carbon.system.service.SysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


/**
 * <p>
 * 角色  前端控制器
 * </p>
 *
 * @author Li Jun
 * @since 2021-06-18
 */
@Slf4j
@RestController
@RequestMapping("/sysRole")
@Api(value = "角色 模块", tags = {"角色 模块"})
public class SysRoleController extends BaseController {
    @Autowired
    private SysRoleService sysRoleService;

    @PostMapping("/add")
    @ApiOperation(value = "添加角色", notes = "添加角色")
    public ApiResult<Boolean> addSysRole(@Valid @RequestBody SysRole sysRole){
        sysRole.setId(null);
        sysRoleService.addSysRole(sysRole);
        return ApiResult.ok();
    }

    @PutMapping("/update")
    @ApiOperation(value = "修改角色",notes = "修改角色")
    public ApiResult<Boolean> updateSysRole(@Valid @RequestBody SysRole sysRole){
        boolean flag = sysRoleService.updateById(sysRole);
        return ApiResult.result(flag);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "删除角色对象",notes = "删除角色")
    public ApiResult<Boolean> delete(@PathVariable String id) {
        boolean flag = sysRoleService.removeById(id);
        return ApiResult.result(flag);
    }

    @GetMapping("/info/{id}")
    @ApiOperation(value = "查看角色",notes = "查看角色")
    public ApiResult<SysRoleQueryVo> getSysRole(@PathVariable String id) {
        SysRoleQueryVo sysRoleQueryVo = sysRoleService.getSysRoleById(id);
        return ApiResult.ok(sysRoleQueryVo);
    }

    @PostMapping("getPageList")
    @ApiOperation(value = "角色 分页列表",notes = "角色 分页列表")
    public ApiResult<Paging<SysRoleQueryVo>> getSysRolePageList(@Valid @RequestBody(required = false) SysRoleQueryParam sysRoleQueryParam) {
        Paging<SysRoleQueryVo> paging = sysRoleService.getSysRolePageList(sysRoleQueryParam);
        return ApiResult.ok(paging);
    }

    @PostMapping("/menu/add")
    @ApiOperation(value = "修改角色权限菜单", notes = "修改角色权限菜单")
    public ApiResult<Boolean> addSysRoleMenu(@Valid @RequestBody SysRoleMenuAddParam sysRoleMenuAddParam) {
        sysRoleService.addSysRoleMenu(sysRoleMenuAddParam);
        return ApiResult.ok();
    }

    /**
     * 根据角色id查询角色权限菜单
     */

    @GetMapping("/menu/{id}")
    @ApiOperation(value = "根据角色id查询角色权限菜单", notes = "根据角色id查询角色权限菜单")
    public ApiResult<List<SysRoleMenu>> selectRoleMenuById(@RequestParam Long id){
        return ApiResult.ok(sysRoleService.selectRoleMenuById(id));
    }

}

