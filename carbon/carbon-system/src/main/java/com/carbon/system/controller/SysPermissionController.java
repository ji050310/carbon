package com.carbon.system.controller;

import com.carbon.domain.common.ApiResult;
import com.carbon.system.vo.SysMenuVo;
import com.carbon.system.common.BaseController;
import com.carbon.system.service.SysMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * <p>
 * 兼容老接口
 * 菜单  前端控制器
 * </p>
 *
 * @author Li Jun
 * @since 2021-06-18
 */
@Deprecated
@Slf4j
@RestController
@RequestMapping("/sysPermission")
@Api(value = "菜单 模块", tags = {"菜单 模块"})
public class SysPermissionController extends BaseController {

    @Autowired
    private SysMenuService sysMenuService;


//    /**
//     * 获取当前登录人菜单列表
//     */
//    @PostMapping("/getPageList")
//    @ApiOperation(value = "当前登录人菜单列表",notes = "当前登录人菜单列表")
//    public ApiResult<List<SysMenuVo>> getCurrentUserMenu() {
//        List<SysMenuVo> menus = sysMenuService.getCurrentUserMenu();
//        for (SysMenuVo menu : menus) {
//            menu.setPermissionName(menu.getMenuName());
//        }
//        return ApiResult.ok(menus);
//    }


}

