package com.carbon.trade.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.carbon.domain.common.annotation.Dict;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 碳交易询报价 查询结果对象
 * </p>
 *
 * @author lin rizhao
 * @since 2022-05-22
 */
@Data
@ApiModel(value="CarbonTradePriceQueryVo对象", description="碳交易询报价查询参数")
public class CarbonTradePriceQueryVo implements Serializable{
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "项目领域字典码编码")
    private String projectScopeCode;

    @ApiModelProperty(value = "项目领域")
    private String projectScope;

    @ApiModelProperty(value = "交易行情ID")
    private Long tradeQuoteId;

    @JsonIgnore
    @ApiModelProperty(value = "交易行情发布租户ID",hidden = true)
    private Long publisherId;

    @JsonIgnore
    @ApiModelProperty(value = "交易行情发布人ID",hidden = true)
    private Long publisherUserId;

    @ApiModelProperty(value = "对话")
    private String conversation;

    @Dict(dictCode = "027")
    @ApiModelProperty(value = "交易角色（字典：027）")
    private String tradeRole;

    @Dict(dictCode = "004")
    @ApiModelProperty(value = "项目类型（字典：004）")
    private String projectType;

    @Dict(dictCode = "014")
    @ApiModelProperty(value = "资产类型（字典：014）")
    private String assetType;

    @ApiModelProperty(value = "买方-ID(租户ID)")
    private Long buyerId;

    @ApiModelProperty(value = "买方机构名称")
    private String buyerName;

    @ApiModelProperty(value = "买方-交易量")
    private BigDecimal buyerTradeQuantity;

    @ApiModelProperty(value = "买方-单价")
    private BigDecimal buyerUnitPrice;

    @Dict(dictCode = "019")
    @ApiModelProperty(value = "买方-期望交割方式（字典：019）")
    private String buyerDeliveryMethod;

    @Dict(dictCode = "017")
    @ApiModelProperty(value = "买方-期望交割场所（字典：017）")
    private String buyerDeliveryExchange;

    @ApiModelProperty(value = "买方-期望交割时间")
    private Date buyerDeliveryTime;


    @ApiModelProperty(value = "卖方-ID(租户ID)")
    private Long sellerId;

    @ApiModelProperty(value = "卖方机构名称")
    private String sellerName;

    @ApiModelProperty(value = "卖方-交易量")
    private BigDecimal sellerTradeQuantity;

    @ApiModelProperty(value = "卖方-单价")
    private BigDecimal sellerUnitPrice;

    @Dict(dictCode = "019")
    @ApiModelProperty(value = "卖方-期望交割方式（字典：019）")
    private String sellerDeliveryMethod;

    @Dict(dictCode = "017")
    @ApiModelProperty(value = "卖方-期望交割场所（字典：017）")
    private String sellerDeliveryExchange;

    @ApiModelProperty(value = "卖方-期望交割时间")
    private Date sellerDeliveryTime;

    @ApiModelProperty(value = "交易截止日期")
    private Date expirationDate;

    @ApiModelProperty(value = "创建人ID")
    private Long creatorId;
}
