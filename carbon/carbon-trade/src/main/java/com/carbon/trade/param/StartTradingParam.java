package com.carbon.trade.param;

import com.carbon.trade.entity.CarbonTradeContract;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 发起询报价参数
 * </p>
 *
 * @author Li Jun
 * @since 2022-05-21
 */
@Data
@ApiModel(value="StartTradingParam", description="发起询报价参数")
public class StartTradingParam implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "交易行情ID")
    private String tradeQuoteId;

    @ApiModelProperty(value = "交易量")
    private BigDecimal tradeQuantity;

    @ApiModelProperty(value = "资产单价")
    private BigDecimal assetUnitPrice;

    @ApiModelProperty(value = "项目领域字典码编码")
    private String projectScopeCode;

    @ApiModelProperty(value = "项目领域")
    private String projectScope;

    @ApiModelProperty(value = "期望交割方式（字典：019）")
    private String deliveryMethod;

    @ApiModelProperty(value = "期望交割场所，交易所（字典：017）")
    private String deliveryExchange;

    @ApiModelProperty(value = "期望交割时间")
    private Date deliveryTime;


}
