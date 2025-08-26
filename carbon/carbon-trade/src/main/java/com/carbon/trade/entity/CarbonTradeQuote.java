package com.carbon.trade.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.carbon.domain.common.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * <p>
 * 碳交易供需行情
 * </p>
 *
 * @author lin rizhao
 * @since 2022-05-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="CarbonTradeQuote对象", description="碳交易供需行情")
public class CarbonTradeQuote extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "交易角色（字典：027）")
    private String tradeRole;

    @ApiModelProperty(value = "机构名称")
    private String institutionName;

    @ApiModelProperty(value = "联系人姓名")
    private String contactsName;

    @ApiModelProperty(value = "联系人手机")
    private String contactsPhone;

    @ApiModelProperty(value = "联系人邮箱")
    private String contactsEmail;

    @ApiModelProperty(value = "项目领域字典码编码")
    private String projectScopeCode;

    @ApiModelProperty(value = "项目领域")
    private String projectScope;


    @ApiModelProperty(value = "项目类型（字典：004）")
    private String projectType;

    @ApiModelProperty(value = "资产类型（字典：014）")
    private String assetType;

    @ApiModelProperty(value = "交易量")
    private BigDecimal tradeQuantity;

    @ApiModelProperty(value = "资产单位（字典：015）")
    private String assetUnit;

    @ApiModelProperty(value = "资产单价")
    private BigDecimal assetUnitPrice;

    @ApiModelProperty(value = "交易状态（字典：016）")
    private String status;

    @ApiModelProperty(value = "交易截止日期")
    private Date expirationDate;

    @ApiModelProperty(value = "期望交割方式（字典：019）")
    private String deliveryMethod;

    @ApiModelProperty(value = "期望交割场所，交易所（字典：017）")
    private String deliveryExchange;

    @ApiModelProperty(value = "期望交割时间")
    private Date deliveryTime;

    @ApiModelProperty(value = "交易信息发布人（租户）")
    private Long publisherId;

    @ApiModelProperty(value = "项目ID")
    private Long projectId;

    @ApiModelProperty(value = "项目名称")
    private String projectName;

}
