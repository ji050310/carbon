package com.carbon.trade.vo;

import com.carbon.domain.common.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 碳交易履约 查询结果对象
 * </p>
 *
 * @author lin rizhao
 * @since 2022-05-21
 */
@Data
@ApiModel(value="CarbonTradeContractQueryVo对象", description="碳交易履约查询参数")
public class CarbonTradeContractQueryVo implements Serializable{
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "交易对手")
    private String Counterparty;

    @Dict(dictCode = "027")
    @ApiModelProperty(value = "交易角色（字典：027）")
    private String tradeRole;

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

    @Dict(dictCode = "014")
    @ApiModelProperty(value = "资产类型（字典：014）")
    private String assetType;

    @Dict(dictCode = "004")
    @ApiModelProperty(value = "项目类型（字典：004）")
    private String projectType;

    @ApiModelProperty(value = "项目领域字典码编码")
    private String projectScopeCode;

    @ApiModelProperty(value = "项目领域")
    private String projectScope;

    @ApiModelProperty(value = "交易量")
    private BigDecimal tradeQuantity;

    @Dict(dictCode = "015")
    @ApiModelProperty(value = "资产单位（字典：015）")
    private String assetUnit;

    @ApiModelProperty(value = "资产单价")
    private BigDecimal assetUnitPrice;

    @Dict(dictCode = "016")
    @ApiModelProperty(value = "交易状态（字典：016）")
    private String status;

    @Dict(dictCode = "019")
    @ApiModelProperty(value = "交割方式（字典：019）")
    private String deliveryMethod;

    @Dict(dictCode = "017")
    @ApiModelProperty(value = "交割场所，交易所（字典：017）")
    private String deliveryExchange;

    @ApiModelProperty(value = "交割时间")
    private Date deliveryTime;

    @ApiModelProperty(value = "交易截止日期")
    private Date expirationDate;

    @ApiModelProperty(value = "备注")
    private String remark;

}
