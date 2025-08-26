package com.carbon.assets.param;

import com.carbon.domain.common.QueryParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 交易账户  绑定账户参数
 * </p>
 *
 * @author Li Jun
 * @since 2021-08-16
 */
@Data
@ApiModel(value="ExchangeAccountBindingParam", description="绑定账户参数")
public class ExchangeAccountBindingParam implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "账户ID")
    private Long accountId;

    @ApiModelProperty(value = "交易所ID")
    private Long carbonExchangeId;

    @ApiModelProperty(value = "账户cookie")
    private String cookie;
}
