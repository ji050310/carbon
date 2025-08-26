package com.carbon.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.carbon.system.entity.SysAccountRole;
import com.carbon.system.mapper.SysAccountRoleMapper;
import com.carbon.system.service.SysAccountRoleService;
import com.carbon.common.service.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;




/**
 * <p>
 * 账户角色  服务实现类
 * </p>
 *
 * @author Li Jun
 * @since 2021-06-18
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class SysAccountRoleServiceImpl extends BaseServiceImpl<SysAccountRoleMapper, SysAccountRole> implements SysAccountRoleService {

    @Autowired
    private SysAccountRoleMapper sysAccountRoleMapper;

    @Override
    public Long getRoleIdByAccountId(Long accountId) {
        SysAccountRole role = sysAccountRoleMapper.selectOne(new QueryWrapper<SysAccountRole>().eq("account_id", accountId));
        return role.getRoleId();
    }
}
