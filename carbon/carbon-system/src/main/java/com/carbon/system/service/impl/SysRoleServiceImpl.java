package com.carbon.system.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.carbon.common.api.Paging;
import com.carbon.common.service.BaseServiceImpl;
import com.carbon.system.entity.SysAccountRole;
import com.carbon.system.entity.SysRole;
import com.carbon.system.entity.SysRoleMenu;
import com.carbon.system.param.SysRoleMenuAddParam;
import com.carbon.system.param.SysRoleQueryParam;
import com.carbon.system.vo.SysAccountRoleVo;
import com.carbon.system.vo.SysRoleQueryVo;
import com.carbon.system.mapper.SysRoleMapper;
import com.carbon.system.mapper.SysRoleMenuMapper;
import com.carbon.system.service.SysAccountRoleService;
import com.carbon.system.service.SysRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


/**
 * <p>
 * 角色  服务实现类
 * </p>
 *
 * @author Li Jun
 * @since 2021-06-18
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class SysRoleServiceImpl extends BaseServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Resource
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Resource
    private SysAccountRoleService sysAccountRoleService;

    @Resource
    private SysRoleMapper sysRoleMapper;

    @Override
    public Paging<SysRoleQueryVo> getSysRolePageList(SysRoleQueryParam param) {
        Page<?> page = getPage(param);
        page.addOrder(OrderItem.desc("sr.updated_time"));
        IPage<SysRoleQueryVo> iPage = sysRoleMapper.getSysRolePageList(page,param);
        return new Paging<>(iPage);
    }

    @Override
    public void addSysRoleMenu(SysRoleMenuAddParam param) {
        //查询角色之前的菜单，删除菜单
        Long roleId = param.getRoleId();
        List<SysRoleMenu> list = sysRoleMenuMapper.selectList(Wrappers.lambdaQuery(SysRoleMenu.class).eq(SysRoleMenu::getRoleId,roleId));
//      removeByIds(list.stream().map(SysRoleMenu::getId).collect(Collectors.toList()));
        //判空
        if(list.size()!=0){
            sysRoleMenuMapper.deleteBatchIds(list.stream().map(SysRoleMenu::getId).collect(Collectors.toList()));
        }
        //添加新的菜单
        param.getMenuIds().forEach(menuId -> {
            SysRoleMenu roleMenu = new SysRoleMenu();
            roleMenu.setMenuId(menuId);
            roleMenu.setRoleId(roleId);
            sysRoleMenuMapper.insert(roleMenu);
        });

    }

    @Override
    public SysAccountRoleVo getAccountRole(Long accountId) {
        SysAccountRoleVo accountRoleVo = new SysAccountRoleVo();
        List<Long> roleIds = sysAccountRoleService.lambdaQuery().eq(SysAccountRole::getAccountId,accountId)
                .list().stream().map(SysAccountRole::getRoleId).collect(Collectors.toList());
        List<SysRole> roles = this.lambdaQuery().in(SysRole::getId,roleIds).list();
        ArrayList<String> roleNames = new ArrayList<>();
        ArrayList<String> roleCodes = new ArrayList<>();
        for (SysRole role : roles) {
            roleNames.add(role.getRoleName());
            roleCodes.add(role.getRoleCode());
        }
        accountRoleVo.setRoleIds(roleIds);
        accountRoleVo.setRoleNames(roleNames);
        accountRoleVo.setRoleCodes(roleCodes);
        accountRoleVo.setAccountId(accountId);

        return accountRoleVo;
    }

    @Override
    public void saveAccountRoles(Long accountId, List<Long> roleIds) {
        if(CollUtil.isEmpty(roleIds)){
            return;
        }
        //删除之前的角色
        sysAccountRoleService.remove(Wrappers.lambdaQuery(SysAccountRole.class).eq(SysAccountRole::getAccountId,accountId));
        //添加新的角色
        ArrayList<SysAccountRole> list = new ArrayList<>();
        roleIds.forEach(roleId -> {
            SysAccountRole sysAccountRole = new SysAccountRole();
            sysAccountRole.setAccountId(accountId);
            sysAccountRole.setRoleId(roleId);
            list.add(sysAccountRole);
        });
        sysAccountRoleService.saveBatch(list);
    }

    @Override
    public List<SysRoleMenu> selectRoleMenuById(Long id) {
        return sysRoleMenuMapper.selectList(Wrappers.lambdaQuery(SysRoleMenu.class).eq(SysRoleMenu::getRoleId,id));
    }

    @Override
    public SysRoleQueryVo getSysRoleById(String id) {

        return sysRoleMapper.getSysRoleById(id);
    }

    @Override
    public void addSysRole(SysRole sysRole) {
        save(sysRole);
    }

}
