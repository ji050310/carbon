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
 * 数据面板 碳开收入-月统计
 * </p>
 *
 * @author Li Jun
 * @since 2021-07-20
 */
@Data
@ApiModel(value="StatCarbonIncomeVo", description="碳开收入 月统计")
public class StatCarbonIncomeVo implements Serializable{
    private static final long serialVersionUID = 1L;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "数据统计日期")
    private Date statDate;

    @ApiModelProperty(value = "月收入")
    private BigDecimal monthIncome;

    @ApiModelProperty(value = "总收入")
    private BigDecimal totalIncome;

    @ApiModelProperty(value = "环比")
    private BigDecimal monthOnMonthRatio;

    @ApiModelProperty(value = "同比")
    private BigDecimal yearOnYearRatio;

}
