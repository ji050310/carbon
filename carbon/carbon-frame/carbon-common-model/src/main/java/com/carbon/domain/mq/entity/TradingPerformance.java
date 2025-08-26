package com.carbon.domain.mq.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 交易履约跟进表单MQ实体类
 *
 * @author Bae
 * @version 1.0
 * @date 2022/6/5 22:11
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value="交易履约跟进表单MQ实体类", description="交易履约跟进表单MQ实体类")
public class TradingPerformance {

    @ApiModelProperty(value = "对话")
    private String conversation;

    @ApiModelProperty(value = "询价机构")
    private String enquiryAgencies;

    @ApiModelProperty(value = "询价机构联系电话")
    private String enquiryAgenciesNumber;

    @ApiModelProperty(value = "报价机构")
    private String quoteAgencies;

    @ApiModelProperty(value = "报价机构联系人")
    private String quoteAgenciesContact;

    @ApiModelProperty(value = "报价机构联系电话")
    private String quoteAgenciesNumber;

    @ApiModelProperty(value = "询价量")
    private String amountEnquiry;

    @ApiModelProperty(value = "询价单价")
    private String enquiryPrice;

    @ApiModelProperty(value = "报价量")
    private String amountQuote;

    @ApiModelProperty(value = "报价单价")
    private String quotePrice;

    @ApiModelProperty(value = "截止时间")
    private String deadline;

    @ApiModelProperty(value = "拟交割方式")
    private String planDeliveryMode;

    @ApiModelProperty(value = "拟交割场所")
    private String planDeliverySite;

    @ApiModelProperty(value = "进展状态")
    private String progressStatus;

    @ApiModelProperty(value = "赢单率")
    private String winRate;

    @ApiModelProperty(value = "跟进人")
    private String followPeople;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "签约付费情况")
    private String payState;

    @ApiModelProperty(value = "履约进展状态")
    private String implementationState;

    @ApiModelProperty(value = "交易履约负责人")
    private String implementationPrincipal;

    @ApiModelProperty(value = "履约状态备注")
    private String implementationStateRemark;
    
}
