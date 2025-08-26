package com.carbon.assets.param;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import com.carbon.domain.common.QueryParam;

/**
 * <p>
 * 项目元注册表内容 查询参数对象
 * </p>
 *
 * @author Cbd
 * @since 2022-08-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="CarbonMetaregistryDocContentQueryParam对象", description="项目元注册表内容查询参数")
public class CarbonMetaregistryDocContentQueryParam extends QueryParam {
    private static final long serialVersionUID = 1L;
}
