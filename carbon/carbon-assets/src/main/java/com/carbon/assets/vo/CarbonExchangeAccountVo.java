package com.carbon.assets.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 交易账户  查询结果对象
 * </p>
 *
 * @author Li Jun
 * @since 2021-08-16
 */
@Data
@ApiModel(value = "CarbonExchangeAccountVo", description = "交易相关账号")
public class CarbonExchangeAccountVo implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "交易所ID")
    private Long id;

    @ApiModelProperty(value = "交易所名称")
    private String exchangeName;

    @ApiModelProperty(value = "开户官网")
    private String website;

    @ApiModelProperty(value = "交易详情url")
    private String detailUrl;

    @ApiModelProperty(value = "关联账户")
    private List<ExchangeAccountInfo>  accountInfoList;
}
