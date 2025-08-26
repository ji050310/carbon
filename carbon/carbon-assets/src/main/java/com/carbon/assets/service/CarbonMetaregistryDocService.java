package com.carbon.assets.service;

import com.carbon.assets.entity.CarbonMetaregistryDoc;
import com.carbon.common.service.BaseService;
import com.carbon.assets.param.CarbonMetaregistryDocQueryParam;
import com.carbon.assets.vo.CarbonMetaregistryDocQueryVo;
import com.carbon.common.api.Paging;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ChengJX
 * @since 2022-08-08
 */
public interface CarbonMetaregistryDocService extends BaseService<CarbonMetaregistryDoc> {

    /**
     * 根据ID获取查询对象
     * @param id 主键id
     * @return CarbonMetaregistryDocQueryVo
     */
    CarbonMetaregistryDocQueryVo getCarbonMetaregistryDocById(Serializable id);

    /**
     * 上传文档
     */
    void uploadCarbonMetaRegistryDoc();


    /**
     * 查询是否已经登记备案
     */
    void isDocRegistration();

    /**
     * 获取分页对象
     * @param param CarbonMetaregistryDocQueryParam
     * @return Paging<CarbonMetaregistryDocQueryVo>
     */
    Paging<CarbonMetaregistryDocQueryVo> getCarbonMetaregistryDocPageList(CarbonMetaregistryDocQueryParam param);

    int updateByref(String url,String ref,String typeCode);

}
