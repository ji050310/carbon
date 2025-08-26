package com.carbon.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.carbon.domain.common.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 角色
 * </p>
 *
 * @author Li Jun
 * @since 2021-06-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "SysRole对象", description = "角色 ")
public class SysRole extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "角色ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @NotBlank(message = "角色编码不能为空")
    @ApiModelProperty(value = "角色编码",required = true)
    private String roleCode;

    @NotBlank(message = "角色名称不能为空")
    @ApiModelProperty(value = "角色名称",required = true)
    private String roleName;

    @NotBlank(message = "状态不能为空")
    @ApiModelProperty(value = "状态 0启用，1禁用",required = true)
    private String status;

    @ApiModelProperty(value = "备注")
    private String remarks;

}
