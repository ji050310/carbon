package com.carbon.system.service;

import com.carbon.system.entity.SysRole;
import com.carbon.common.service.BaseService;
import com.carbon.system.entity.SysRoleMenu;
import com.carbon.system.param.SysRoleMenuAddParam;
import com.carbon.system.param.SysRoleQueryParam;
import com.carbon.system.vo.SysAccountRoleVo;
import com.carbon.system.vo.SysRoleQueryVo;
import com.carbon.common.api.Paging;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 角色  服务类
 * </p>
 *
 * @author Li Jun
 * @since 2021-06-18
 */
public interface SysRoleService extends BaseService<SysRole> {

    Paging<SysRoleQueryVo> getSysRolePageList(SysRoleQueryParam sysRoleQueryParam);

    void addSysRoleMenu(SysRoleMenuAddParam sysRoleMenuAddParam);

    SysAccountRoleVo getAccountRole(Long accountId);

    void saveAccountRoles(Long accountId, List< Long> roleIds);

    List<SysRoleMenu> selectRoleMenuById(Long id);

    SysRoleQueryVo getSysRoleById(String id);

    void addSysRole(SysRole sysRole);
}
