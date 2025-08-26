package com.carbon.assets.service;

import com.carbon.assets.entity.CarbonMetaregistryDocContent;
import com.carbon.assets.vo.CarbonMetaregistryDocContentEsVo;
import com.carbon.common.service.BaseService;
import com.carbon.assets.param.CarbonMetaregistryDocContentQueryParam;
import com.carbon.assets.vo.CarbonMetaregistryDocContentQueryVo;
import com.carbon.common.api.Paging;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 项目元注册表内容 服务类
 * </p>
 *
 * @author Cbd
 * @since 2022-08-15
 */
public interface CarbonMetaregistryDocContentService extends BaseService<CarbonMetaregistryDocContent> {

    /**
     * 根据ID获取查询对象
     * @param id 主键id
     * @return CarbonMetaregistryDocContentQueryVo
     */
    CarbonMetaregistryDocContentQueryVo getCarbonMetaregistryDocContentById(Serializable id);

    /**
     * 获取分页对象
     * @param param CarbonMetaregistryDocContentQueryParam
     * @return Paging<CarbonMetaregistryDocContentQueryVo>
     */
    Paging<CarbonMetaregistryDocContentQueryVo> getCarbonMetaregistryDocContentPageList(CarbonMetaregistryDocContentQueryParam param);


    CarbonMetaregistryDocContentEsVo getMetaregistryContentByRefId(String refId,String typeCode);

    CarbonMetaregistryDocContentEsVo getMetaregistryContentByRefIdEs(String refId,String typeCode);
}
