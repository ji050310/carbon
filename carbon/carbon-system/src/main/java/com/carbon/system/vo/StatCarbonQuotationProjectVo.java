package com.carbon.system.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 数据面板 碳交易行情
 * </p>
 *
 * @author Li Jun
 * 2021-07-20
 */
@Data
@ApiModel(value="StatCarbonQuotationProjectVo", description="数据面板 碳交易行情-项目")
public class StatCarbonQuotationProjectVo implements Serializable{
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "项目类型")
    private String type;

    @ApiModelProperty(value = "签发量")
    private BigDecimal singCount;

    @ApiModelProperty(value = "存量")
    private BigDecimal stockCount;

    @ApiModelProperty(value = "项目数量")
    private BigDecimal count;

}
