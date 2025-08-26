package com.carbon.assets.param;

import com.carbon.domain.common.QueryParam;
import com.carbon.domain.common.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * <p>
 * 碳减排方法学 查询参数对象
 * </p>
 *
 * @author Li Jun
 * @since 2021-07-31
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="CarbonMethodologyQueryParam对象", description="碳减排方法学查询参数")
public class CarbonMethodologyQueryParam extends QueryParam {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "模糊搜索项")
    private String searchKey;

    @ApiModelProperty(value = "方法学状态")
    private String status;

    @ApiModelProperty(value = "方法学状态（字典：045）")
    private String statusCode;

    @ApiModelProperty(value = "领域编码（字典：003）")
    private String fieldCode;

    @ApiModelProperty(value = "子领域编码（字典：004）")
    private String fieldChildCode;

    @ApiModelProperty(value = "行业编码（字典：002）")
    private String industryCode;

    @ApiModelProperty(value = "核证标准")
    private Integer certificationCriteria;

}
