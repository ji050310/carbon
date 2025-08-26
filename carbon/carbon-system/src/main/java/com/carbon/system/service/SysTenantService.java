package com.carbon.system.service;

import com.carbon.system.entity.SysTenant;
import com.carbon.common.service.BaseService;
import com.carbon.system.param.SysTenantQueryParam;
import com.carbon.system.vo.SysTenantQueryVo;
import com.carbon.common.api.Paging;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 租户  服务类
 * </p>
 *
 * @author ChengJX
 * @since 2022-08-24
 */
public interface SysTenantService extends BaseService<SysTenant> {

    /**
     * 根据ID获取查询对象
     * @param id 主键id
     * @return SysTenantQueryVo
     */
    SysTenantQueryVo getSysTenantById(Serializable id);

    /**
     * 获取分页对象
     * @param param SysTenantQueryParam
     * @return Paging<SysTenantQueryVo>
     */
    Paging<SysTenantQueryVo> getSysTenantPageList(SysTenantQueryParam param);


    List<com.carbon.domain.system.vo.SysTenantModelVo> getSysTenantList();
}
