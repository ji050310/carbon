package com.carbon.domain.mq.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 出售客户跟进表单MQ实体类
 *
 * @author Bae
 * @version 1.0
 * @date 2022/6/5 22:11
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value="出售客户跟进表单MQ实体类", description="出售客户跟进表单MQ实体类")
public class SalesCustomer {

    @ApiModelProperty(value = "客户姓名")
    private String customerName;

    @ApiModelProperty(value = "销售机构")
    private String salesAgencies;

    @ApiModelProperty(value = "联系电话")
    private String contactNumber;

    @ApiModelProperty(value = "销售资产")
    private String salesAssets;

    @ApiModelProperty(value = "销售项目")
    private String salesItem;

    @ApiModelProperty(value = "销售量")
    private String amountSales;

    @ApiModelProperty(value = "销售单价")
    private String salesPrice;

    @ApiModelProperty(value = "销售截止时间")
    private String salesDeadline;

    @ApiModelProperty(value = "期望交割方式")
    private String expectedDeliveryMode;

    @ApiModelProperty(value = "期望交割场所")
    private String expectedDeliverySite;

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

    @ApiModelProperty(value = "撮合进展状态")
    private String matchedProgressState;

    @ApiModelProperty(value = "撮合负责人")
    private String matchmaker;

    @ApiModelProperty(value = "撮合进展备注")
    private String matchedProgressRemark;
    
}
