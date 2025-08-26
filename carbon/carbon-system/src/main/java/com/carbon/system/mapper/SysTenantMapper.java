package com.carbon.system.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.carbon.domain.system.vo.SysTenantModelVo;
import com.carbon.system.entity.SysTenant;
import com.carbon.system.param.SysTenantQueryParam;
import com.carbon.system.vo.SysTenantQueryVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 租户  Mapper 接口
 * </p>
 *
 * @author ChengJX
 * @since 2022-08-24
 */
@Repository
public interface SysTenantMapper extends BaseMapper<SysTenant> {

    /**
     * 根据ID获取查询对象
     * @param id 主键id
     * @return SysTenantQueryVo
     */
    SysTenantQueryVo getSysTenantById(Serializable id);

    /**
     * 获取分页对象
     * @param page 分页参数
     * @param param 查询参数
     * @return IPage<SysTenantQueryVo>
     */
    IPage<SysTenantQueryVo> getSysTenantPageList(@Param("page") Page<?> page, @Param("param") SysTenantQueryParam param);

    List<SysTenantModelVo> getSysTenantList();
}
