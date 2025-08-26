package com.carbon.assets.service.impl;

import com.carbon.assets.entity.CarbonAssetAssessment;
import com.carbon.assets.mapper.CarbonAssetAssessmentMapper;
import com.carbon.assets.param.BusinessDataQueryParam;
import com.carbon.assets.service.CarbonAssetAssessmentService;
import com.carbon.assets.param.CarbonAssetAssessmentQueryParam;
import com.carbon.assets.vo.CarbonAssetAssessmentQueryVo;
import com.carbon.assets.vo.CarbonDetectionDataVo;
import com.carbon.common.service.BaseServiceImpl;
import com.carbon.common.api.Paging;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.io.Serializable;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Code534
 * @since 2022-07-14
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class CarbonAssetAssessmentServiceImpl extends BaseServiceImpl<CarbonAssetAssessmentMapper, CarbonAssetAssessment> implements CarbonAssetAssessmentService {

    @Resource
    private CarbonAssetAssessmentMapper carbonAssetAssessmentMapper;

    @Override
    public CarbonDetectionDataVo getBusinessData(BusinessDataQueryParam businessDataQueryParam) {
        return carbonAssetAssessmentMapper.getBusinessData(businessDataQueryParam);
    }

    @Override
    public CarbonAssetAssessmentQueryVo getCarbonAssetAssessmentById(Serializable id) {
        return carbonAssetAssessmentMapper.getCarbonAssetAssessmentById(id);
    }

    @Override
    public Paging<CarbonAssetAssessmentQueryVo> getCarbonAssetAssessmentPageList(CarbonAssetAssessmentQueryParam param) {
        IPage<CarbonAssetAssessmentQueryVo> iPage = carbonAssetAssessmentMapper.getCarbonAssetAssessmentPageList(getPage(param),param);
        return new Paging<>(iPage);
    }

}
