package com.carbon.system.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.carbon.domain.common.ApiResult;
import com.carbon.system.entity.SysMenu;
import com.carbon.system.entity.SysRoleMenu;
import com.carbon.system.mapper.SysRoleMapper;
import com.carbon.system.mapper.SysRoleMenuMapper;
import com.carbon.system.param.SysMenuQueryParam;
import com.carbon.system.param.SysMenuStatusParam;
import com.carbon.system.vo.SysMenuVo;
import com.carbon.system.common.BaseController;
import com.carbon.system.service.SysMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;


/**
 * <p>
 * 菜单  前端控制器
 * </p>
 *
 * @author Li Jun
 * @since 2021-06-18
 */
@Slf4j
@RestController
@RequestMapping("/sysMenu")
@Api(value = "菜单 模块", tags = {"菜单 模块"})
public class SysMenuController extends BaseController {

    @Autowired
    private SysMenuService sysMenuService;

    @Resource
    private SysRoleMenuMapper sysRoleMenuMapper;

    //超级管理员id
    private Long superAdminId=1L;
    /**
     * 添加菜单
     */
    @PostMapping("/add")
    @ApiOperation(value = "添加菜单",notes = "添加菜单")
    public ApiResult<Boolean> addSysPermission(@Valid @RequestBody SysMenu sysMenu) {
        sysMenu.setId(null);
        boolean flag=sysMenuService.save(sysMenu);
        try {
            //默认超级管理员拥有菜单权限
            Long menuId=sysMenuService.getMenuByName(sysMenu.getMenuName()).getId();
            SysRoleMenu roleMenu = new SysRoleMenu();
            roleMenu.setMenuId(menuId);
            roleMenu.setRoleId(superAdminId);
            sysRoleMenuMapper.insert(roleMenu);
        }catch (Exception e)
        {
            e.printStackTrace();
        }


        return ApiResult.result(flag);
    }

    /**
     * 修改菜单
     */
    @PutMapping("/update")
    @ApiOperation(value = "修改菜单",notes = "修改菜单 ")
    public ApiResult<Boolean> updateSysPermission(@Valid @RequestBody SysMenu sysMenu) {
        return sysMenuService.updateById(sysMenu) ? ApiResult.ok() : ApiResult.fail("菜单ID不正确");
    }

    /**
     * 删除菜单
     */
    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "删除菜单",notes = "删除菜单 ")
    public ApiResult<Boolean> deleteSysPermission(@PathVariable String id) {
        LambdaQueryWrapper<SysMenu> queryWrapper = Wrappers.lambdaQuery(SysMenu.class).eq(SysMenu::getParentId, id);
        List<SysMenu> children = sysMenuService.list(queryWrapper);
        if (CollUtil.isNotEmpty(children)){
            return ApiResult.fail("请先删除子菜单");
        }
        return sysMenuService.removeById(id) ? ApiResult.ok() : ApiResult.fail("菜单ID不正确");
    }

    /**
     * 获取当前登录人菜单列表
     */
    @PostMapping("/userMenus")
    @ApiOperation(value = "当前登录人菜单列表",notes = "当前登录人菜单列表")
    public ApiResult<List<Tree<Long>>> getCurrentUserMenu() {
        return ApiResult.ok(sysMenuService.getCurrentUserMenu());
    }

    /**
     * 查询菜单列表
     */
    @PostMapping("/list")
    @ApiOperation(value = "查询菜单列表",notes = "查询菜单列表")
    public ApiResult<List<SysMenuVo>> getMenuList(@RequestBody SysMenuQueryParam param) {
        return ApiResult.ok(sysMenuService.getSysMenuList(param));
    }

    /**
     * 根据ID和status修改状态接口
     */
    @PostMapping("/updateStatus")
    @ApiOperation(value = "根据ID和status修改状态接口",notes = "根据ID和status修改状态接口")
    public ApiResult<Boolean> updateStatus(@Valid @RequestBody SysMenuStatusParam param) {
        sysMenuService.updateStatus(param);
        return ApiResult.ok("修改成功");
    }

}
