package com.carbon.system.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 账户角色  查询结果对象
 * </p>
 *
 * @author Li Jun
 * @since 2021-06-18
 */
@Data
@ApiModel(value = "SysAccountRoleQueryVo对象", description = "账户角色 查询参数")
public class SysAccountRoleVo implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "账户ID")
    private Long accountId;

    @ApiModelProperty(value = "角色ID")
    private List<Long> roleIds;

    @ApiModelProperty(value = "角色名称")
    private List<String> roleNames;

    @ApiModelProperty(value = "角色code")
    private List<String> roleCodes;
}
