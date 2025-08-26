package com.carbon.domain.auth.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * <p>
 * 登录参数
 * </p>
 * @author Li Jun
 * @since 2021-06-11
 */
@Data
@ApiModel(value="LoginParam", description="登录参数")
public class LoginParam implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 账号名
     */
    @ApiModelProperty(value = "账号名")
    @NotBlank(message="请输入账号")
    private String accountName;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    @NotBlank(message="请输入密码")
    private String password;

//    /**
//     * 验证码
//     */
//    @ApiModelProperty(value = "验证码")
//    @NotBlank(message="验证码不能为空")
//    private String captcha;
}
