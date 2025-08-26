package com.carbon.system.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author : Li Jun
 * 2021-06-29 17:57
 **/
@Data
@ApiModel(value="AccountBaseInfoUpdateParam", description="账户基本信息-更新参数")
public class AccountBaseInfoUpdateParam implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "账户ID不能为空")
    @ApiModelProperty(value = "账户id")
    private Long id;

    @NotBlank(message = "账户名不能为空")
    @ApiModelProperty(value = "账户名",required = true)
    private String accountName;

    @NotBlank(message = "手机号不能为空")
    @ApiModelProperty(value = "手机号",required = true)
    private String phone;

    @ApiModelProperty(value = "邮箱")
    private String email;
}
