package com.carbon.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.carbon.domain.common.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 菜单
 * </p>
 *
 * @author Li Jun
 * @since 2021-06-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "SysPermission对象", description = "菜单 ")
public class SysMenu extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "菜单id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @NotNull(message = "父级菜单ID不能为空")
    @ApiModelProperty(value = "父级权限")
    private Long parentId;

    @NotBlank(message = "菜单名称不能为空")
    @ApiModelProperty(value = "权限菜单名称")
    private String menuName;

    @NotNull(message = "菜单级别不能为空")
    @ApiModelProperty(value = "权限菜单级别")
    private Integer menuLevel;

//    @NotBlank(message = "菜单图标不能为空")
    @ApiModelProperty(value = "图标")
    private String menuIcon;

//    @NotNull(message = "菜单排序不能为空")
    @ApiModelProperty(value = "排序")
    private Float orderNum;

    @NotBlank(message = "菜单url不能为空")
    @ApiModelProperty(value = "菜单URL")
    private String menuUrl;

    @NotNull(message = "菜单状态不能为空")
    @ApiModelProperty(value = "状态 0启用，1禁用")
    private Integer status;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "是否隐藏")
    private Boolean hidden;
}
