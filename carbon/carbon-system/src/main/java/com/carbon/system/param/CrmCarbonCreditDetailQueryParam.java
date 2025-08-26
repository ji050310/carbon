package com.carbon.system.param;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import com.carbon.domain.common.QueryParam;

/**
 * <p>
 * 碳信分详情 查询参数对象
 * </p>
 *
 * @author Zhang Jinrui
 * @since 2022-05-02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="CrmCarbonCreditDetailQueryParam对象", description="碳信分详情查询参数")
public class CrmCarbonCreditDetailQueryParam extends QueryParam {
    private static final long serialVersionUID = 1L;
}
