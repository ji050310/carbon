package com.carbon.assets.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;

import java.util.Date;

/**
 * <p>
 *  查询结果对象
 * </p>
 *
 * @author Code534
 * @since 2022-07-14
 */
@Data
@ApiModel(value="CarbonAssetAssessmentQueryVo对象", description="查询参数")
public class CarbonAssetAssessmentQueryVo implements Serializable{
    private static final long serialVersionUID = 1L;

    private Integer id;

    @ApiModelProperty(value = "字典id")
    private String itemId;

    @ApiModelProperty(value = "项目类型")
    private String projectType;

    @ApiModelProperty(value = "项目领域")
    private String fieldType;

    @ApiModelProperty(value = "字典value")
    private String questionValue;

    @ApiModelProperty(value = "字典名称")
    private String questionName;

    private String answer;

}
