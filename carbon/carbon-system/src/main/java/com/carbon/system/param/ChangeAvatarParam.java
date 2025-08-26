package com.carbon.system.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * <p>
 * 账户 修改头像参数对象
 * </p>
 *
 * @author Jiang zhenhua
 * @since 2022-04-23
 */
@Data
@ApiModel(value="ChangeAvatarParam对象", description="账户头像参数")
public class ChangeAvatarParam implements Serializable {

    @NotNull(message = "账户id不能为空")
    @ApiModelProperty(value = "账户id")
    private Long id;

    @ApiModelProperty(value = "头像url")
    private String avatar;

}
