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
@ApiModel(value="RegisterParam", description="注册参数")
public class RegisterParam implements Serializable {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "账号名")
    @NotBlank(message="请输用户名")
    private String accountName;

    @ApiModelProperty(value = "密码")
    @NotBlank(message="请输入密码")
    private String password;

    @ApiModelProperty(value = "确认密码")
    @NotBlank(message="请输入确认密码")
    private String confirmPassword;

    @ApiModelProperty(value = "手机号")
    @NotBlank(message="请输入手机号")
    private String phone;

    @ApiModelProperty(value = "验证码")
    @NotBlank(message="请输入验证码")
    private String code;

}
