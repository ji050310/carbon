package com.carbon.assets.service;

import com.carbon.assets.entity.CarbonMetaregistry;
import com.carbon.common.service.BaseService;
import com.carbon.assets.param.CarbonMetaregistryQueryParam;
import com.carbon.assets.vo.CarbonMetaregistryQueryVo;
import com.carbon.common.api.Paging;

import java.io.Serializable;

/**
 * <p>
 * 项目仓库 服务类
 * </p>
 *
 * @author ChengJX
 * @since 2022-08-09
 */
public interface CarbonMetaregistryService extends BaseService<CarbonMetaregistry> {

    /**
     * 根据ID获取查询对象
     * @param id 主键id
     * @return CarbonMetaregistryQueryVo
     */
    CarbonMetaregistryQueryVo getCarbonMetaregistryById(Serializable id);

    /**
     * 获取分页对象
     * @param param CarbonMetaregistryQueryParam
     * @return Paging<CarbonMetaregistryQueryVo>
     */
    Paging<CarbonMetaregistryQueryVo> getCarbonMetaregistryPageList(CarbonMetaregistryQueryParam param);

}
