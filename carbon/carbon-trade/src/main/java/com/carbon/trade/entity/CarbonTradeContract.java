package com.carbon.trade.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.carbon.domain.common.BaseEntity;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * <p>
 * 碳交易履约
 * </p>
 *
 * @author lin rizhao
 * @since 2022-05-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="CarbonTradeContract对象", description="碳交易履约")
public class CarbonTradeContract extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键",hidden = true)
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "交易行情ID")
    private Long tradeQuoteId;

    @ApiModelProperty(value = "卖方-租户ID")
    private Long sellerId;

    @ApiModelProperty(value = "卖方机构名称")
    private String sellerName;

    @ApiModelProperty(value = "卖方联系人")
    private String sellerContacts;

    @ApiModelProperty(value = "卖方联系人电话")
    private String sellerPhone;

    @ApiModelProperty(value = "卖方邮箱")
    private String sellerEmail;

    @ApiModelProperty(value = "买方-租户ID")
    private Long buyerId;

    @ApiModelProperty(value = "买方机构名称")
    private String buyerName;

    @ApiModelProperty(value = "买方联系人")
    private String buyerContacts;

    @ApiModelProperty(value = "买方联系人电话")
    private String buyerPhone;

    @ApiModelProperty(value = "买方邮箱")
    private String buyerEmail;

    @ApiModelProperty(value = "资产类型（字典：014）")
    private String assetType;

    @ApiModelProperty(value = "项目类型（字典：004）")
    private String projectType;

    @ApiModelProperty(value = "交易量")
    private BigDecimal tradeQuantity;

    @ApiModelProperty(value = "资产单位（字典：015）")
    private String assetUnit;

    @ApiModelProperty(value = "资产单价")
    private BigDecimal assetUnitPrice;

    @ApiModelProperty(value = "交易状态（字典：016）")
    private String status;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "交割方式（字典：019）")
    private String deliveryMethod;

    @ApiModelProperty(value = "交割场所，交易所（字典：017）")
    private String deliveryExchange;

    @ApiModelProperty(value = "交割时间")
    private Date deliveryTime;

    @ApiModelProperty(value = "交易截止日期")
    private Date expirationDate;

}
