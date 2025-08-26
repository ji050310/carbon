package com.carbon.system.service;

import com.carbon.common.service.BaseService;
import com.carbon.system.entity.SysAccountRole;

/**
 * <p>
 * 账户角色  服务类
 * </p>
 *
 * @author Li Jun
 * @since 2021-06-18
 */
public interface SysAccountRoleService extends BaseService<SysAccountRole> {

    Long getRoleIdByAccountId(Long accountId);

}
