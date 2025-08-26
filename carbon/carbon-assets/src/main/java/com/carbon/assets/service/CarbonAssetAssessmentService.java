package com.carbon.assets.service;

import com.carbon.assets.entity.CarbonAssetAssessment;
import com.carbon.assets.param.BusinessDataQueryParam;
import com.carbon.assets.vo.CarbonDetectionDataVo;
import com.carbon.common.service.BaseService;
import com.carbon.assets.param.CarbonAssetAssessmentQueryParam;
import com.carbon.assets.vo.CarbonAssetAssessmentQueryVo;
import com.carbon.common.api.Paging;

import java.io.Serializable;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Code534
 * @since 2022-07-14
 */
public interface CarbonAssetAssessmentService extends BaseService<CarbonAssetAssessment> {

    CarbonDetectionDataVo getBusinessData(BusinessDataQueryParam businessDataQueryParam);

    /**
     * 根据ID获取查询对象
     * @param id 主键id
     * @return CarbonAssetAssessmentQueryVo
     */
    CarbonAssetAssessmentQueryVo getCarbonAssetAssessmentById(Serializable id);

    /**
     * 获取分页对象
     * @param param CarbonAssetAssessmentQueryParam
     * @return Paging<CarbonAssetAssessmentQueryVo>
     */
    Paging<CarbonAssetAssessmentQueryVo> getCarbonAssetAssessmentPageList(CarbonAssetAssessmentQueryParam param);

}
