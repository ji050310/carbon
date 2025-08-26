package com.carbon.system.service;

import cn.hutool.core.lang.tree.Tree;
import com.carbon.common.service.BaseService;
import com.carbon.system.entity.SysMenu;
import com.carbon.system.param.SysMenuQueryParam;
import com.carbon.system.param.SysMenuStatusParam;
import com.carbon.system.vo.SysMenuVo;

import java.util.List;

/**
 * <p>
 * 菜单  服务类
 * </p>
 *
 * @author Li Jun
 * @since 2021-06-18
 */
public interface SysMenuService extends BaseService<SysMenu> {

    /**
     * 获取分页对象
     * @param param 查询参数
     * @return List<SysPermissionQueryVo>
     */
    List<SysMenuVo> getSysMenuList(SysMenuQueryParam param);

    /**
     * 获取当前用户菜单
     * @return List<SysPermissionQueryVo>
     */
    List<Tree<Long>> getCurrentUserMenu();

    /**
     * 修改菜单状态
     */
    void updateStatus(SysMenuStatusParam param);

    SysMenuVo getMenuByName(String menuName);
}