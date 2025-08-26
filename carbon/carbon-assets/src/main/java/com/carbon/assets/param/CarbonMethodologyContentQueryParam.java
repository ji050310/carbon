package com.carbon.assets.param;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import com.carbon.domain.common.QueryParam;

/**
 * <p>
 * 碳减排方法学文档 查询参数对象
 * </p>
 *
 * @author Cbd
 * @since 2022-08-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="CarbonMethodologyContentQueryParam对象", description="碳减排方法学文档查询参数")
public class CarbonMethodologyContentQueryParam extends QueryParam {
    private static final long serialVersionUID = 1L;
}
