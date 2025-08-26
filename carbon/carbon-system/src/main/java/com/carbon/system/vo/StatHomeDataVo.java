package com.carbon.system.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 数据面板 查询结果对象
 * </p>
 *
 * @author Li Jun
 * @since 2021-07-20
 */
@Data
@ApiModel(value="DataPanelQueryVo对象", description="数据面板查询参数")
public class StatHomeDataVo implements Serializable{
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "资产收入")
    private StatCarbonIncomeVo assetsIncome;

    @ApiModelProperty(value = "资金收入")
    private StatCarbonIncomeVo fundIncome;

    @ApiModelProperty(value = "账户信息")
    private StatAccountVo accountVo;


    @ApiModelProperty(value = "碳信用")
    private BigDecimal carbonCredit;

    @ApiModelProperty(value = "碳配额")
    private BigDecimal carbonQuota;

    @ApiModelProperty(value = "绿证")
    private BigDecimal greenScore;


    @ApiModelProperty(value = "供应量")
    private StatCarbonMonthVo monthSupply;

    @ApiModelProperty(value = "开发量")
    private StatCarbonMonthVo monthDevelopment;

    @ApiModelProperty(value = "销售量")
    private StatCarbonMonthVo monthSales;

    @ApiModelProperty(value = "碳项目统计")
    private StatCarbonProjectVo projectStat;

    @ApiModelProperty(value = "碳交易行情")
    private List<StatCarbonQuotationVo> quotation;

}
