package com.carbon.assets.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.carbon.assets.entity.CarbonMetaregistryProject;
import com.carbon.assets.param.CarbonMetaregistryProjectQueryParam;
import com.carbon.assets.vo.CarbonMetaregistryProjectQueryVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * <p>
 * 租户碳减排项目 Mapper 接口
 * </p>
 *
 * @author ChengJX
 * @since 2022-08-22
 */
@Repository
public interface CarbonMetaregistryProjectMapper extends BaseMapper<CarbonMetaregistryProject> {

    /**
     * 根据ID获取查询对象
     * @param id 主键id
     * @return CarbonMetaregistryProjectQueryVo
     */
    CarbonMetaregistryProjectQueryVo getCarbonMetaregistryProjectById(Serializable id);

    /**
     * 获取分页对象
     * @param page 分页参数
     * @param param 查询参数
     * @return IPage<CarbonMetaregistryProjectQueryVo>
     */
    IPage<CarbonMetaregistryProjectQueryVo> getCarbonMetaregistryProjectPageList(@Param("page") Page<?> page, @Param("param") CarbonMetaregistryProjectQueryParam param);

}
