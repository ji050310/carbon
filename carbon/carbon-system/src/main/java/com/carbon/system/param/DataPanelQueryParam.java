package com.carbon.system.param;

import com.carbon.domain.common.QueryParam;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 数据面板 查询参数对象
 * </p>
 *
 * @author Li Jun
 * @since 2021-07-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="DataPanelQueryParam对象", description="数据面板查询参数")
public class DataPanelQueryParam extends QueryParam {
    private static final long serialVersionUID = 1L;
}
