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
 * 碳交易供需行情 查询结果对象
 * </p>
 *
 * @author lin rizhao
 * @since 2022-05-21
 */
@Data
@ApiModel(value="CarbonTradeQuoteQueryVo对象", description="碳交易供需行情查询参数")
//@Document(indexName = "carbon_trade_quote",shards = 1,replicas = 0)
public class CarbonTradeQuoteQueryVo implements Serializable{
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @Dict(dictCode = "027")
    @ApiModelProperty(value = "交易角色（字典：027）")
    private String tradeRole;

    @ApiModelProperty(value = "项目领域字典码编码")
    @Dict(dictCode = "003")
    private String projectScopeCode;

    @ApiModelProperty(value = "项目领域")
    private String projectScope;

    @ApiModelProperty(value = "机构名称")
    private String institutionName;

    @ApiModelProperty(value = "联系人姓名")
    private String contactsName;

    @ApiModelProperty(value = "联系人手机")
    private String contactsPhone;

    @ApiModelProperty(value = "联系人邮箱")
    private String contactsEmail;

    @Dict(dictCode = "004")
    @ApiModelProperty(value = "项目类型（字典：004）")
    private String projectType;

    @ApiModelProperty(value = "项目类型（字典：004）")
    private String projectTypeName;

    @Dict(dictCode = "014")
    @ApiModelProperty(value = "资产类型（字典：014）")
    private String assetType;

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

    @ApiModelProperty(value = "交易截止日期")
    private Date expirationDate;

    @Dict(dictCode = "019")
    @ApiModelProperty(value = "期望交割方式（字典：019）")
    private String deliveryMethod;

    @Dict(dictCode = "017")
    @ApiModelProperty(value = "期望交割场所，交易所（字典：017）")
    private String deliveryExchange;

    @ApiModelProperty(value = "期望交割时间")
    private Date deliveryTime;

    @ApiModelProperty(value = "交易信息发布租户ID")
    private Long publisherId;

    @ApiModelProperty(value = "交易信息发布租户名称")
    private String publisherName;

    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "项目ID")
    private Long projectId;
}
