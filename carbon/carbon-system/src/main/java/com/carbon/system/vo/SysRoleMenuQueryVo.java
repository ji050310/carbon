package com.carbon.system.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 角色权限 查询结果对象
 * </p>
 *
 * @author Li Jun
 * @since 2021-06-18
 */
@Data
@ApiModel(value = "SysRolePermissionQueryVo对象", description = "角色权限查询参数")
public class SysRoleMenuQueryVo implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "角色ID")
    private Long roleId;

    @ApiModelProperty(value = "菜单ID")
    private Long menuId;

    @ApiModelProperty(value = "菜单名称")
    private String menuName;

}
