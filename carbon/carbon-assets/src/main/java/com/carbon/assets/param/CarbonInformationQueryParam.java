package com.carbon.assets.param;

import com.carbon.domain.common.QueryParam;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 碳资讯 查询参数对象
 * </p>
 *
 * @author Li Jun
 * @since 2022-01-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="CarbonInformationQueryParam对象", description="碳资讯查询参数")
public class CarbonInformationQueryParam extends QueryParam {
    private static final long serialVersionUID = 1L;
}
