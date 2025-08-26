package com.carbon.system.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import com.carbon.domain.common.QueryParam;

/**
 * <p>
 * 租户-用户  查询参数对象
 * </p>
 *
 * @author Zhang Jinrui
 * @since 2022-05-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="TenantUserQueryPageParam对象", description="租户-用户 查询参数")
public class TenantUserQueryPageParam extends QueryParam {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "来源渠道")
    private String sourceChannel;
}
