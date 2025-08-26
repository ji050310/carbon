package com.carbon.assets.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.carbon.assets.entity.CarbonMetaregistry;
import com.carbon.assets.param.CarbonMetaregistryQueryParam;
import com.carbon.assets.vo.CarbonMetaregistryDocContentEsVo;
import com.carbon.assets.vo.CarbonMetaregistryQueryVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 项目仓库 Mapper 接口
 * </p>
 *
 * @author ChengJX
 * @since 2022-08-09
 */
@Repository
public interface CarbonMetaregistryMapper extends BaseMapper<CarbonMetaregistry> {

    /**
     * 根据ID获取查询对象
     * @param id 主键id
     * @return CarbonMetaregistryQueryVo
     */
    CarbonMetaregistryQueryVo getCarbonMetaregistryById(Serializable id);

    /**
     * 获取分页对象
     * @param page 分页参数
     * @param param 查询参数
     * @return IPage<CarbonMetaregistryQueryVo>
     */
    IPage<CarbonMetaregistryQueryVo> getCarbonMetaregistryPageList(@Param("page") Page<?> page, @Param("param") CarbonMetaregistryQueryParam param);


    List<CarbonMetaregistryQueryVo>  getMetaregistryContentByRefId(@Param("refId")String refId );

    //es
    CarbonMetaregistryDocContentEsVo getMetaregistryContentByRefIdEs(@Param("refId")String refId , @Param("typeCode") String typeCode);

    List<CarbonMetaregistryDocContentEsVo> MetaregistryEsAdd();

    CarbonMetaregistryDocContentEsVo getContentMsgByref(@Param("refId") String refId,@Param("type") String type);
}
