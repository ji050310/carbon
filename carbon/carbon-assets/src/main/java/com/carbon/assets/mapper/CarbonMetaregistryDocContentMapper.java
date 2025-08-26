package com.carbon.assets.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.carbon.assets.entity.CarbonMetaregistryDocContent;
import com.carbon.assets.param.CarbonMetaregistryDocContentQueryParam;
import com.carbon.assets.vo.CarbonMetaregistryDocContentEsVo;
import com.carbon.assets.vo.CarbonMetaregistryDocContentQueryVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 项目元注册表内容 Mapper 接口
 * </p>
 *
 * @author Cbd
 * @since 2022-08-15
 */
@Repository
public interface CarbonMetaregistryDocContentMapper extends BaseMapper<CarbonMetaregistryDocContent> {

    /**
     * 根据ID获取查询对象
     * @param id 主键id
     * @return CarbonMetaregistryDocContentQueryVo
     */
    CarbonMetaregistryDocContentQueryVo getCarbonMetaregistryDocContentById(Serializable id);

    /**
     * 获取分页对象
     * @param page 分页参数
     * @param param 查询参数
     * @return IPage<CarbonMetaregistryDocContentQueryVo>
     */
    IPage<CarbonMetaregistryDocContentQueryVo> getCarbonMetaregistryDocContentPageList(@Param("page") Page<?> page, @Param("param") CarbonMetaregistryDocContentQueryParam param);

    CarbonMetaregistryDocContentEsVo getMetaregistryContentByRefIdEs(@Param("refId") String refId,@Param("typeCode")String typeCode);
}
