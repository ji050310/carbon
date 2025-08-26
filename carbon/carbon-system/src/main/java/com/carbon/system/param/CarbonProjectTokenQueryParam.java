package com.carbon.system.param;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import com.carbon.domain.common.QueryParam;

/**
 * <p>
 *  查询参数对象
 * </p>
 *
 * @author Bae
 * @since 2022-06-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="CarbonProjectTokenQueryParam对象", description="查询参数")
public class CarbonProjectTokenQueryParam extends QueryParam {
    private static final long serialVersionUID = 1L;
}
