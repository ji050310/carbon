package com.carbon.system.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 菜单  查询结果对象
 * </p>
 *
 * @author Li Jun
 * @since 2021-06-18
 */
@Data
@ApiModel(value = "SysMenuVo", description = "菜单 查询参数")
public class SysMenuVo implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "父级权限")
    private Long parentId;

    @ApiModelProperty(value = "菜单名称")
    private String menuName;

    @ApiModelProperty(value = "菜单名称")
    private String permissionName;

    @ApiModelProperty(value = "图标")
    private String menuIcon;

    @ApiModelProperty(value = "菜单状态")
    private Integer status;

    @ApiModelProperty(value = "排序")
    private Float orderNum;

    @ApiModelProperty(value = "菜单URL")
    private String menuUrl;

    @ApiModelProperty(value = "是否隐藏")
    private Boolean hidden;

    @ApiModelProperty(value = "菜单Level")
    private Integer menuLevel;


    @ApiModelProperty(value = "子菜单集合")
    private List<SysMenuVo> children = new ArrayList<>();
}
