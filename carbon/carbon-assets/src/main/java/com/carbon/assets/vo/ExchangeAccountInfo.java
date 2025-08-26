package com.carbon.assets.vo;

import com.carbon.domain.common.annotation.Dict;
import com.carbon.assets.common.enums.ExchangeAccountStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel(value = "ExchangeAccountInfo", description = "交易相关账号-账户信息")
public class ExchangeAccountInfo implements Serializable {

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "所属租户")
    private Long tenantId;

    @ApiModelProperty(value = "交易所ID")
    private Long carbonExchangeId;

    @ApiModelProperty(value = "账号")
    private String accountName;

    @Dict(dictCode = "043")
    @ApiModelProperty(value = "账户状态(字典编码043)")
    private String accountStatus;

    @ApiModelProperty(value = "最近登录时间")
    private Date loginTime;

    @ApiModelProperty(value = "绑定时间")
    private Date bindingTime;

    @ApiModelProperty(value = "创建时间")
    private Date createdTime;
}
