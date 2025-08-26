package com.carbon.system.param;

import com.carbon.domain.common.QueryParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 角色  查询参数对象
 * </p>
 *
 * @author Li Jun
 * @since 2021-06-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="SysRoleQueryParam对象", description="角色 查询参数")
public class SysRoleQueryParam extends QueryParam {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "角色名称")
    private String roleName;

    @ApiModelProperty(value = "状态 0启用，1禁用")
    private String status;
}
