package com.carbon.system.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 菜单 状态参数
 * </p>
 *
 * @author jzh
 * @since 2022-05-29
 */
@Data
@ApiModel(value="SysMenuStatusParam", description="菜单 状态参数")
public class SysMenuStatusParam implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "菜单ID不能为空")
    @ApiModelProperty(value = "菜单ID")
    private Long id;

    @NotNull(message = "菜单状态不能为空")
    @ApiModelProperty(value = "状态 0启用 1禁用")
    private Integer status;
}
