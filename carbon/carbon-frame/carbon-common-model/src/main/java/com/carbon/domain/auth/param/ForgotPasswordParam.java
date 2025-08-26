package com.carbon.domain.auth.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * <p>
 * 注册参数
 * </p>
 * 2022-03-26
 * @author Li Jun
 */
@Data
@ApiModel(value="ForgotPasswordParam", description="忘记密码参数")
public class ForgotPasswordParam implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "密码")
    @NotBlank(message="请输入密码")
    private String password;

    @ApiModelProperty(value = "手机号")
    @NotBlank(message="请输入手机号")
    private String phone;

    @ApiModelProperty(value = "验证码")
    @NotBlank(message="请输入验证码")
    private String code;

}
