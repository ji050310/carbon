package com.carbon.system.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 首页-企业绿度
 * </p>
 *
 * @author Li Jun
 * @since 2021-07-20
 */
@Data
@ApiModel(value="EnterpriseGreennessVo", description="企业绿度")
public class EnterpriseGreennessVo implements Serializable{
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "碳排放指标权重")
    private Double carbonEmissionRate;
    @ApiModelProperty(value = "能效指标权重")
    private Double energyEfficiencyRate;
    @ApiModelProperty(value = "环保指标权重")
    private Double environmentRate;

}
