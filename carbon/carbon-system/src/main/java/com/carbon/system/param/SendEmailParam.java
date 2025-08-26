package com.carbon.system.param;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author : Jiang zhenhua
 * 2022年5月5日20:40:32
 **/
@Data
@ApiModel(value="SendEmailParam", description="账户基本信息-邮箱更新参数")
public class SendEmailParam implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "账户ID不能为空")
    @ApiModelProperty(value = "账户id")
    private Long id;

    @NotBlank(message = "密码不能为空")
    @ApiModelProperty(value = "账户密码")
    private String password;

    @NotBlank(message = "邮箱不能为空")
    @ApiModelProperty(value = "邮箱")
    private String email;

}
