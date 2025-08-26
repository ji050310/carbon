package com.carbon.domain.auth.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 登录信息
 * </p>
 *
 * @author Li Jun
 * @since 2021-06-11
 **/
@Data
@ApiModel(value = "LoginInfoVo", description = "登录信息")
public class LoginInfoVo implements Serializable {

    private static final long serialVersionUID = 387986819989323045L;

    @ApiModelProperty(value = "token")
    private String token;

    @ApiModelProperty(value = "数据")
    private SecurityData securityData;

}
