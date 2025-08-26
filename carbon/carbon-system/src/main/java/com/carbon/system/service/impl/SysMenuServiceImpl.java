package com.carbon.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.carbon.common.service.BaseServiceImpl;
import com.carbon.common.utils.HttpContextUtils;
import com.carbon.system.entity.SysAccountRole;
import com.carbon.system.entity.SysMenu;
import com.carbon.system.entity.SysRoleMenu;
import com.carbon.system.param.SysMenuQueryParam;
import com.carbon.system.param.SysMenuStatusParam;
import com.carbon.system.vo.SysMenuVo;
import com.carbon.system.mapper.SysMenuMapper;
import com.carbon.system.mapper.SysRoleMenuMapper;
import com.carbon.system.service.SysAccountRoleService;
import com.carbon.system.service.SysMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * <p>
 * 菜单  服务实现类
 * </p>
 *
 * @author Li Jun
 * @since 2021-06-18
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class SysMenuServiceImpl extends BaseServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    @Resource
    private SysMenuMapper sysMenuMapper;

    @Autowired
    private SysAccountRoleService sysAccountRoleService;
    @Resource
    private SysRoleMenuMapper sysRoleMenuMapper;


    @Override
    public List<SysMenuVo> getSysMenuList(SysMenuQueryParam param) {
        //默认从第一层查，否则返回树形重复
        if(param.getMenuLevel()==null){
            param.setMenuLevel(1);
        }
        List<SysMenuVo> menuList = sysMenuMapper.getSysMenuList(param);
        getChildMenu(menuList);
        return menuList;
    }

    @Override
    public List<Tree<Long>> getCurrentUserMenu() {

        List<Long> roleIds = sysAccountRoleService.lambdaQuery()
                .eq(SysAccountRole::getAccountId, HttpContextUtils.getAccountId()).list()
                .stream()
                .map(SysAccountRole::getRoleId)
                .collect(Collectors.toList());
//        //目前一个账号对应一名角色
        List<Long> menuIds = sysRoleMenuMapper.selectList(Wrappers.lambdaQuery(SysRoleMenu.class)
                        .in(SysRoleMenu::getRoleId,roleIds))
                .stream()
                .map(SysRoleMenu::getMenuId)
                .collect(Collectors.toList());
        //判列表是否空
        if(menuIds.size()==0){
            return new ArrayList<>();
        }
        List<SysMenu> sysMenus = sysMenuMapper.selectBatchIds(menuIds);
        sysMenus = sysMenus.stream().filter(e->e.getStatus() == 0).collect(Collectors.toList());
        // log.info("断点调试 sysMenus: [{}]", JSONUtil.toJsonStr(sysMenus));
//        树结构构建
        TreeNodeConfig config =new TreeNodeConfig();
        config.setWeightKey("orderNum");
        List<Tree<Long>> menuList = TreeUtil.build(sysMenus, 0L, config, (obj, tree) -> {
            tree.setId(obj.getId());
            tree.setParentId(obj.getParentId());
            tree.putExtra("menuName", obj.getMenuName());
            tree.putExtra("menuLevel", obj.getMenuLevel());
            tree.putExtra("menuIcon", obj.getMenuIcon());
            tree.putExtra("menuUrl", obj.getMenuUrl());
            tree.putExtra("orderNum", obj.getOrderNum());
            tree.putExtra("status", obj.getStatus());
            tree.putExtra("hidden", obj.getHidden());
        });
        return menuList;
    }


    @Override
    public void updateStatus(SysMenuStatusParam param) {
        SysMenu sysMenu = sysMenuMapper.selectById(param.getId());
        sysMenu.setStatus(param.getStatus());
        log.info("updateStatus : [{}]",JSONUtil.toJsonStr(sysMenu));
        sysMenuMapper.updateById(sysMenu);
    }

    @Override
    public SysMenuVo getMenuByName(String menuName) {
        return sysMenuMapper.getMenuByName(menuName);
    }

    /**
     * 查询字典子节点
     * @param menuList 字典列表
     */
    private void getChildMenu(List<SysMenuVo> menuList){
        if (CollUtil.isEmpty(menuList)){
            return;
        }
        List<Long> parentIds = menuList.stream().map(SysMenuVo::getId).collect(Collectors.toList());
        //查询子节点
        List<SysMenu> sysMenus = sysMenuMapper.selectList(Wrappers.lambdaQuery(SysMenu.class).in(SysMenu::getParentId, parentIds));
        List<SysMenuVo> childList = BeanUtil.copyToList(sysMenus, SysMenuVo.class);

        Map<Long, List<SysMenuVo>> childMap = childList.stream().collect(Collectors.groupingBy(SysMenuVo::getParentId));
        //将子节点填充到父节点
        for (SysMenuVo dict : menuList) {
            //子节点排序
            List<SysMenuVo> list = childMap.get(dict.getId());
            if (CollUtil.isNotEmpty(list)){
                dict.setChildren(list.stream().sorted(Comparator.comparing(SysMenuVo::getOrderNum)).collect(Collectors.toList()));
            }
        }
        //递归调用，查询下一级
        getChildMenu(childList);
    }
}