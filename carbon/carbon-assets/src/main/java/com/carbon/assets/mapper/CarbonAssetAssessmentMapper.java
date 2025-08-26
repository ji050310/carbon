package com.carbon.assets.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.carbon.assets.entity.CarbonAssetAssessment;
import com.carbon.assets.param.BusinessDataQueryParam;
import com.carbon.assets.param.CarbonAssetAssessmentQueryParam;
import com.carbon.assets.vo.CarbonAssetAssessmentQueryVo;
import com.carbon.assets.vo.CarbonDetectionDataVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Code534
 * @since 2022-07-14
 */
@Repository
public interface CarbonAssetAssessmentMapper extends BaseMapper<CarbonAssetAssessment> {


    CarbonDetectionDataVo getBusinessData(@Param("param") BusinessDataQueryParam param);


    /**
     * 根据ID获取查询对象
     * @param id 主键id
     * @return CarbonAssetAssessmentQueryVo
     */
    CarbonAssetAssessmentQueryVo getCarbonAssetAssessmentById(Serializable id);

    /**
     * 获取分页对象
     * @param page 分页参数
     * @param param 查询参数
     * @return IPage<CarbonAssetAssessmentQueryVo>
     */
    IPage<CarbonAssetAssessmentQueryVo> getCarbonAssetAssessmentPageList(@Param("page") Page<?> page, @Param("param") CarbonAssetAssessmentQueryParam param);

}
