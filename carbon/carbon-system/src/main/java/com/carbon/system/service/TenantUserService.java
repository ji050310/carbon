package com.carbon.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.carbon.domain.common.ApiResult;
import com.carbon.system.entity.TenantUser;
import com.carbon.common.service.BaseService;
import com.carbon.system.param.TenantUserQueryPageParam;
import com.carbon.system.vo.DateQueryDTO;
import com.carbon.system.vo.TenantUserDTO;
import com.carbon.system.vo.TenantUserDetailVo;
import com.carbon.system.vo.TenantUserPageVo;
import com.carbon.common.api.Paging;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 租户-用户  服务类
 * </p>
 *
 * @author Zhang Jinrui
 * @since 2022-05-03
 */
public interface TenantUserService extends BaseService<TenantUser> {

    /**
     * 根据条件获取查询对象
     * @param param 条件
     * @return TenantUserQueryVo
     */
    ApiResult getTenantUserByCondition(String param);

    /**
     * 根据条件获取查询对象
     * @param id
     * @return TenantUserDetailVo
     */
    ApiResult getTenantUserById(String id);
//
//    /**
//     * 获取分页对象
//     * @param param TenantUserQueryParam
//     * @return Paging<TenantUserQueryVo>
//     */
//    Paging<TenantUserQueryVo> getTenantUserPageList(TenantUserQueryParam param);

    /**
     * 获取分页对象
     * @param param TenantUserQueryParam
     * @return Paging<TenantUserQueryVo>
     */
    Paging<TenantUserPageVo> getTenantUserPageList(Page page, TenantUserQueryPageParam param);

    /**
     * 通过ID删除用户
     * @param userId
     * @return
     */
    ApiResult removeByUserId(String userId);


    /**
     * 更新用户信息
     * @param tenantUser
     * @return
     */
    ApiResult updateUser(TenantUserDTO tenantUser);


    ApiResult getCrmDetailByDate(DateQueryDTO date);
}
