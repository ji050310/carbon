package com.carbon.assets.service;

import com.carbon.assets.entity.CarbonMetaregistryProject;
import com.carbon.common.service.BaseService;
import com.carbon.assets.param.CarbonMetaregistryProjectQueryParam;
import com.carbon.assets.vo.CarbonMetaregistryProjectQueryVo;
import com.carbon.common.api.Paging;

import java.io.Serializable;

/**
 * <p>
 * 租户碳减排项目 服务类
 * </p>
 *
 * @author ChengJX
 * @since 2022-08-22
 */
public interface CarbonMetaregistryProjectService extends BaseService<CarbonMetaregistryProject> {

    /**
     * 根据ID获取查询对象
     * @param id 主键id
     * @return CarbonMetaregistryProjectQueryVo
     */
    CarbonMetaregistryProjectQueryVo getCarbonMetaregistryProjectById(Serializable id);

    /**
     * 获取分页对象
     * @param param CarbonMetaregistryProjectQueryParam
     * @return Paging<CarbonMetaregistryProjectQueryVo>
     */
    Paging<CarbonMetaregistryProjectQueryVo> getCarbonMetaregistryProjectPageList(CarbonMetaregistryProjectQueryParam param);

}
