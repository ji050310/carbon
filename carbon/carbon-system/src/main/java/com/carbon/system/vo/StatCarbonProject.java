package com.carbon.system.vo;

import com.carbon.domain.common.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 数据面板 碳项目统计
 * </p>
 *
 * @author Li Jun
 * @since 2021-07-20
 */
@Data
@ApiModel(value="StatCarbonProject", description="碳项目统计")
public class StatCarbonProject implements Serializable{
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "预计减排量")
    private String reduction;

    @ApiModelProperty(value = "碳资产估值")
    private BigDecimal carbonValuation;

    @Dict(dictCode = "010")
    @ApiModelProperty(value = "开发状态")
    private String developmentState;

    @Dict(dictCode = "016")
    @ApiModelProperty(value = "交易状态")
    private String tradeState;

}
