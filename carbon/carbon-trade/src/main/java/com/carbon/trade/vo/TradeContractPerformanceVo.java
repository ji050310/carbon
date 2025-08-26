package com.carbon.trade.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


/**
 * <p>
 * 碳交易履约 执行结果
 * </p>
 *
 * @author lin rizhao
 * @since 2022-05-21
 */
@Data
@ApiModel(value="TradeContractPerformanceVo", description="碳交易履约-执行结果")
public class TradeContractPerformanceVo implements Serializable{
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "交易履约ID")
    private Long tradeContractId;

    @ApiModelProperty(value = "交易所官网")
    private String exchangeWebsite;

}
