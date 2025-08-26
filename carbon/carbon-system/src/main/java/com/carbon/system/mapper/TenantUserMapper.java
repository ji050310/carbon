package com.carbon.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.carbon.system.entity.TenantUser;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * <p>
 * 租户-用户  Mapper 接口
 * </p>
 *
 * @author Zhang Jinrui
 * @since 2022-05-03
 */
@Repository
public interface TenantUserMapper extends BaseMapper<TenantUser> {

//    /**
//     * 根据ID获取查询对象
//     * @param id 主键id
//     * @return TenantUserQueryVo
//     */
//    TenantUserQueryVo getTenantUserById(Serializable id);
//
//    /**
//     * 获取分页对象
//     * @param page 分页参数
//     * @param param 查询参数
//     * @return IPage<TenantUserQueryVo>
//     */
//    IPage<TenantUserQueryVo> getTenantUserPageList(@Param("page") Page<?> page, @Param("param") TenantUserQueryParam param);

}
