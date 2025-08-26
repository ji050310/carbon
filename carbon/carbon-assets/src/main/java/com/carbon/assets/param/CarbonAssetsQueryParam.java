package com.carbon.assets.param;

import com.carbon.domain.common.QueryParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * <p>
 * 碳中和资产 查询参数对象
 * </p>
 *
 * @author Li Jun
 * @since 2021-07-31
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="CarbonAssetsQueryParam对象", description="碳中和资产查询参数")
public class CarbonAssetsQueryParam extends QueryParam {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "资产状态:0.无效 1.有效")
    private Integer status;

    @ApiModelProperty(value = "资产类型,字典ID:6")
    private Integer type;

    @ApiModelProperty(value = "碳资产单位,字典ID:25")
    private Integer uomType;

    @ApiModelProperty(value = "碳资产来源渠道,字典ID：11")
    private Integer sourceChannel;



    @ApiModelProperty(value = "创建日期开始时间")
    private Date startDate;

    @ApiModelProperty(value = "创建日期结束时间")
    private Date endDate;

    @ApiModelProperty(value = "子领域编码")
    private String fieldChildCode;
}
