package com.carbon.assets.param;

import com.carbon.domain.common.QueryParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 中和资产交易  查询参数对象
 * </p>
 *
 * @author Li Jun
 * @since 2021-09-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="CarbonAssetsBusinessQueryParam对象", description="中和资产交易 查询参数")
public class CarbonAssetsBusinessQueryParam extends QueryParam {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "卖方用户账户id(长安链账户id)")
    private String userIdFrom;

    @ApiModelProperty(value = "卖方机构名称")
    private String orgNameFrom;

    @ApiModelProperty(value = "买方机构名称")
    private String orgNameTo;
}
