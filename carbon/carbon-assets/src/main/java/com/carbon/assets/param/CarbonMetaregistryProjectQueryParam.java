package com.carbon.assets.param;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import com.carbon.domain.common.QueryParam;

/**
 * <p>
 * 租户碳减排项目 查询参数对象
 * </p>
 *
 * @author ChengJX
 * @since 2022-08-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="CarbonMetaregistryProjectQueryParam对象", description="租户碳减排项目查询参数")
public class CarbonMetaregistryProjectQueryParam extends QueryParam {
    private static final long serialVersionUID = 1L;
}
