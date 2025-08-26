package com.carbon.assets.param;

import com.carbon.domain.common.QueryParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @description:
 * @author: code534
 * @time: 2022/7/12 15:50
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="CarbonDataSubmissionQueryParam对象", description="数据报送提交参数")
public class CarbonDataSubmissionQueryParam extends QueryParam {

    @ApiModelProperty(name = "报送人id")
    private String creatorId;
    @ApiModelProperty(name = "报送因子")
    private String parameterList;
    @ApiModelProperty(name = "项目id")
    private String projectId;
    @ApiModelProperty(name = "因子编码")
    private String factorCode;
//    @ApiModelProperty(name = "指标编码")
//    private String indexCode;
//    @ApiModelProperty(name = "模型编码")
//    private String modelCode;
//    @ApiModelProperty(name = "算法编码")
//    private String algorithmCode;
//    @ApiModelProperty(name = "因子编码")
//    private String factorCode;
    @ApiModelProperty(name = "数据来源")
    private String sourceFrom;



}
