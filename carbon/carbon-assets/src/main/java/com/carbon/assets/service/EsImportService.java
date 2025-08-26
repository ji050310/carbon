package com.carbon.assets.service;

import com.carbon.assets.entity.CarbonAssetAssessment;
import com.carbon.assets.param.BusinessDataQueryParam;
import com.carbon.assets.param.CarbonAssetAssessmentQueryParam;
import com.carbon.assets.vo.CarbonAssetAssessmentQueryVo;
import com.carbon.assets.vo.CarbonDetectionDataVo;
import com.carbon.common.api.Paging;
import com.carbon.common.service.BaseService;

import java.io.Serializable;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Code534
 * @since 2022-07-14
 */
public interface EsImportService {

    void methodImport() throws Exception;

    void metaregistryImport() throws Exception;

}
