package com.carbon.domain.mq.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 采购客户跟进表单MQ实体类
 *
 * @author Bae
 * @version 1.0
 * @date 2022/6/5 22:11
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value="采购客户跟进表单MQ实体类", description="采购客户跟进表单MQ实体类")
public class PurchasingCustomer {

    @ApiModelProperty(value = "客户姓名")
    private String customerName;

    @ApiModelProperty(value = "采购机构")
    private String procurementAgencies;

    @ApiModelProperty(value = "联系电话")
    private String contactNumber;

    @ApiModelProperty(value = "采购资产")
    private String purchasingAssets;

    @ApiModelProperty(value = "采购项目")
    private String procurementItem;

    @ApiModelProperty(value = "采购量")
    private String amountPurchased;

    @ApiModelProperty(value = "采购单价")
    private String purchasePrice;

    @ApiModelProperty(value = "采购截止时间")
    private String purchaseDeadline;

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
