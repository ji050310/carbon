package com.carbon.assets.service;

import com.carbon.assets.entity.CarbonMethodology;
import com.carbon.domain.assets.vo.MethodologyUploadParam;
import com.carbon.assets.vo.CarbonMethodologySelectVo;
import com.carbon.common.service.BaseService;
import com.carbon.assets.param.CarbonMethodologyQueryParam;
import com.carbon.assets.vo.CarbonMethodologyQueryVo;
import com.carbon.common.api.Paging;

import java.io.Serializable;
import java.util.List;

public interface CarbonMethodologyService extends BaseService<CarbonMethodology> {

    /**
     * 根据ID获取查询对象
     * @param id 主键id
     * @return CarbonMethodologyQueryVo
     */
    CarbonMethodologyQueryVo getCarbonMethodologyById(Serializable id);

    /**
     * 获取分页对象
     * @param param CarbonMethodologyQueryParam
     * @return Paging<CarbonMethodologyQueryVo>
     */
    Paging<CarbonMethodologyQueryVo> getCarbonMethodologyPageList(CarbonMethodologyQueryParam param);


    void addCarbonMethodology(MethodologyUploadParam param);

    /**
     * 修改方法学
     * @param carbonMethodology 参数
     */
    boolean updateCarbonMethodology(CarbonMethodology carbonMethodology);

    /**
     * 方法学下拉选择列表
     * @return List<CarbonMethodologySelectVo>
     */
    List<CarbonMethodologySelectVo> getCarbonMethodologyList();

    CarbonMethodology getCarbonMethodologyByName(String name) throws Exception;
}