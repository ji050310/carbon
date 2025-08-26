package com.carbon.system.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 账户 修改手机号参数对象
 * </p>
 *
 * @author Jiang zhenhua
 * @since 2022-04-23
 */
@Data
@ApiModel(value="ChangePhoneParam对象", description="账户手机号参数")
public class ChangePhoneParam {

    @NotNull(message = "账户ID不能为空")
    @ApiModelProperty(value = "账户id")
    private Long id;

    @NotBlank(message = "账户手机号不能为空")
    @ApiModelProperty(value = "账户手机号")
    private String phone;

    @ApiModelProperty(value = "验证码")
    private String code;
}
