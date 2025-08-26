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
 * 碳交易询报价
 * </p>
 *
 * @author lin rizhao
 * @since 2022-05-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="CarbonTradePrice对象", description="碳交易询报价")
public class CarbonTradePrice extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "项目领域字典码编码")
    private String projectScopeCode;

    @ApiModelProperty(value = "项目领域")
    private String projectScope;

    @ApiModelProperty(value = "交易行情ID")
    private Long tradeQuoteId;

    @ApiModelProperty(value = "买方-ID(租户ID)")
    private Long buyerId;

    @ApiModelProperty(value = "买方-交易量")
    private BigDecimal buyerTradeQuantity;

    @ApiModelProperty(value = "买方-单价")
    private BigDecimal buyerUnitPrice;

    @ApiModelProperty(value = "买方-期望交割方式（字典：019）")
    private String buyerDeliveryMethod;

    @ApiModelProperty(value = "买方-期望交割场所（字典：017）")
    private String buyerDeliveryExchange;

    @ApiModelProperty(value = "买方-期望交割时间")
    private Date buyerDeliveryTime;

    @ApiModelProperty(value = "卖方-ID(租户ID)")
    private Long sellerId;

    @ApiModelProperty(value = "卖方-交易量")
    private BigDecimal sellerTradeQuantity;

    @ApiModelProperty(value = "卖方-单价")
    private BigDecimal sellerUnitPrice;

    @ApiModelProperty(value = "卖方-期望交割方式（字典：019）")
    private String sellerDeliveryMethod;

    @ApiModelProperty(value = "卖方-期望交割场所（字典：017）")
    private String sellerDeliveryExchange;

    @ApiModelProperty(value = "卖方-期望交割时间")
    private Date sellerDeliveryTime;

}
