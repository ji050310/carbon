package com.carbon.system.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 数据面板 碳开发量 月统计
 * </p>
 *
 * @author Li Jun
 * @since 2021-07-20
 */
@Data
@ApiModel(value="StatCarbonMonthVo", description="碳开发量 月统计")
public class StatCarbonMonthVo implements Serializable{
    private static final long serialVersionUID = 1L;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "数据统计日期")
    private Date statDate;

    @ApiModelProperty(value = "月供应量")
    private BigDecimal carbonSupply;

    @ApiModelProperty(value = "碳信用")
    private BigDecimal carbonCredit;

    @ApiModelProperty(value = "碳配额")
    private BigDecimal carbonQuota;

    @ApiModelProperty(value = "绿证")
    private BigDecimal greenScore;

    @ApiModelProperty(value = "总供应量")
    private BigDecimal carbonSupplyTotal;

    @ApiModelProperty(value = "碳资产估值")
    private BigDecimal carbonValuation;

}
