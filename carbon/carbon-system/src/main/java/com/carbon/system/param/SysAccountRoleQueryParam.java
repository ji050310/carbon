package com.carbon.system.param;

import com.carbon.domain.common.QueryParam;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 账户角色  查询参数对象
 * </p>
 *
 * @author Li Jun
 * @since 2021-06-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="SysAccountRoleQueryParam对象", description="账户角色 查询参数")
public class SysAccountRoleQueryParam extends QueryParam {
    private static final long serialVersionUID = 1L;
}
