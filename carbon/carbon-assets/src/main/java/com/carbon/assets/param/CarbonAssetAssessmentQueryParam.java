package com.carbon.assets.param;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import com.carbon.domain.common.QueryParam;

/**
 * <p>
 *  查询参数对象
 * </p>
 *
 * @author Code534
 * @since 2022-07-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="CarbonAssetAssessmentQueryParam对象", description="查询参数")
public class CarbonAssetAssessmentQueryParam extends QueryParam {
    private static final long serialVersionUID = 1L;
}
