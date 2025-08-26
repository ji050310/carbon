package com.carbon.assets.param;

import com.carbon.domain.common.QueryParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @description:
 * @author: code534
 * @time: 2022/7/15 16:24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="BusinessDataQueryParam对象", description="项目评估业务数据 查询参数")
public class BusinessDataQueryParam  extends QueryParam {

    @ApiModelProperty(value = "项目领域")
    private String name;
    @ApiModelProperty(value = "类型")
    private String type;
    @ApiModelProperty(value = "年份")
    private String time;

}
