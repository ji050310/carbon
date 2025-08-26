package com.carbon.domain.system.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author : Li Jun
 * @since : 2021-06-29 17:57
 **/
@Data
@ApiModel(value="ChangePasswordParam", description="修改密码参数")
public class ChangePasswordParam implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "客户id不能为空")
    @ApiModelProperty(value = "账户id")
    private Long id;

    @ApiModelProperty(value = "原密码")
    private String oldPassword;

    @ApiModelProperty(value = "原密码Md5",hidden = true)
    private String oldPasswordMd5;

    @NotBlank(message = "新密码不能为空")
    @ApiModelProperty(value = "新密码",required = true)
    private String newPassword;
}
