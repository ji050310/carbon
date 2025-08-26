package com.carbon.assets.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.carbon.assets.entity.CarbonMetaregistryDoc;
import com.carbon.assets.param.CarbonMetaregistryDocQueryParam;
import com.carbon.assets.vo.CarbonMetaregistryDocQueryVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ChengJX
 * @since 2022-08-08
 */
@Repository
public interface CarbonMetaregistryDocMapper extends BaseMapper<CarbonMetaregistryDoc> {

    /**
     * 根据ID获取查询对象
     * @param id 主键id
     * @return CarbonMetaregistryDocQueryVo
     */
    CarbonMetaregistryDocQueryVo getCarbonMetaregistryDocById(Serializable id);

    CarbonMetaregistryDocQueryVo getCarbonMetaregistryDocByRegistryId(Serializable id);
    /**
     * 获取分页对象
     * @param page 分页参数
     * @param param 查询参数
     * @return IPage<CarbonMetaregistryDocQueryVo>
     */
    IPage<CarbonMetaregistryDocQueryVo> getCarbonMetaregistryDocPageList(@Param("page") Page<?> page, @Param("param") CarbonMetaregistryDocQueryParam param);

    int updateByref(@Param("url") String url,@Param("ref") String ref,@Param("typeCode") String typeCode);
}
