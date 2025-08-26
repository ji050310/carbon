package com.carbon.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 账户角色
 * </p>
 *
 * @author Li Jun
 * @since 2021-06-18
 */
@Data
@ApiModel(value = "SysAccountRole对象", description = "账户角色 ")
public class SysAccountRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "账户ID")
    private Long accountId;

    @ApiModelProperty(value = "角色ID")
    private Long roleId;

}
