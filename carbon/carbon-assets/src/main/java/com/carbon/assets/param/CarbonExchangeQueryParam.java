package com.carbon.assets.param;

import com.carbon.domain.common.QueryParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 碳交易所  查询参数对象
 * </p>
 *
 * @author Li Jun
 * @since 2021-08-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="CarbonExchangeQueryParam对象", description="碳交易所 查询参数")
public class CarbonExchangeQueryParam extends QueryParam {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "模糊搜索项")
    private String searchKey;

    @ApiModelProperty(value = "交易所类型,字典ID:12")
    private Integer type;

    @ApiModelProperty(value = "状态:1.启用 2.禁用")
    private Integer status;
}
