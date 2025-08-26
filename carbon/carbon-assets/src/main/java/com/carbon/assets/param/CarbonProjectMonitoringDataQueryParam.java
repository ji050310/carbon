package com.carbon.assets.param;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import com.carbon.domain.common.QueryParam;

/**
 * <p>
 * 碳减排项目监测数据 查询参数对象
 * </p>
 *
 * @author Li Jun
 * @since 2022-05-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="CarbonProjectMonitoringDataQueryParam对象", description="碳减排项目监测数据查询参数")
public class CarbonProjectMonitoringDataQueryParam extends QueryParam {
    private static final long serialVersionUID = 1L;
}
