package com.carbon.system.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 菜单  查询参数对象
 * </p>
 *
 * @author Li Jun
 * @since 2021-06-18
 */
@Data
@ApiModel(value="SysMenuQueryParam", description="菜单 查询参数")
public class SysMenuQueryParam implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "菜单ID列表")
    List<Long> menuIds;

    @ApiModelProperty(value = "是否隐藏")
    Boolean hidden;

    @ApiModelProperty(value = "状态")
    Integer status;

    @ApiModelProperty(value = "权限级别")
    private Integer menuLevel;

    @ApiModelProperty(value = "权限名称")
    private String menuName;
}
