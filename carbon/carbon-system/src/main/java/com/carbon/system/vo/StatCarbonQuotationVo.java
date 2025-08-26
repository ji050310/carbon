package com.carbon.system.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 数据面板 碳交易行情
 * </p>
 *
 * @author Li Jun
 * 2021-07-20
 */
@Data
@ApiModel(value="StatCarbonQuotationVo", description="数据面板 碳交易行情")
public class StatCarbonQuotationVo implements Serializable{
    private static final long serialVersionUID = 1L;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "碳交易行情-统计日期")
    private Date statDate;

    @ApiModelProperty(value = "CCER签发量")
    private Long ccerCount;

    @ApiModelProperty(value = "CCER项目量")
    private Long ccerProjectCount;

    @ApiModelProperty(value = "已核销")
    private Long writtenOffCount;

    @ApiModelProperty(value = "存量")
    private Long stockCount;

    @ApiModelProperty(value = "已审定")
    private Long approvedCount;

    @ApiModelProperty(value = "已备案")
    private Long filingCount;

    @ApiModelProperty(value = "已签发")
    private Long singCount;

    @ApiModelProperty(value = "项目")
    private List<StatCarbonQuotationProjectVo> projects;

    @ApiModelProperty(value = "类型")
    private String type;


}
