package com.carbon.trade.param;

import com.carbon.domain.common.QueryParam;
import com.carbon.trade.entity.CarbonTradeContract;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 意向成交参数
 * </p>
 *
 * @author lin rizhao
 * @since 2022-05-21
 */
@Data
@ApiModel(value="IntendedTransactionParam", description="意向成交参数")
public class IntendedTransactionParam implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "询报价ID")
    private String tradePriceId;

    @ApiModelProperty(value = "履约信息")
    private CarbonTradeContract tradeContract;

}
