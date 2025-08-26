package com.carbon.domain.trade.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.carbon.domain.common.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
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
@ApiModel(value="MqCarbonTradeQuote", description="碳交易供需行情Mq消息")
public class MqCarbonTradeQuote implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "交易角色（字典：027）")
    private String tradeRole;

    @ApiModelProperty(value = "机构名称")
    private String institutionName;

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

}
