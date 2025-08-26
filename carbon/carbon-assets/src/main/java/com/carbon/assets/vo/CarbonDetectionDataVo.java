package com.carbon.assets.vo;

import com.carbon.domain.common.BaseEntity;
import com.carbon.domain.common.annotation.Dict;
import com.carbon.domain.common.annotation.OperatorName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @description:
 * @author: code534
 * @time: 2022/7/12 14:04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="CarbonDetectionDataVo对象", description="碳资产检测数据报送")
public class CarbonDetectionDataVo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "单位")
    private String unit;

    @ApiModelProperty(value = "数据/参数")
    private String modeName;

    @ApiModelProperty(value = "QA/QC程序")
    private String qaQcProgram;

    @ApiModelProperty(value = "测量方法和程序")
    private String methodProgram;

    @ApiModelProperty(value = "监测频率")
    private String frequence;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "数据用途")
    private String dataUse;

    @ApiModelProperty(value = "评论")
    private String comment;

    @ApiModelProperty(value = "数据来源")
    private String sourceForm;

    @OperatorName
    @ApiModelProperty(value = "创建者id")
    private Long creatorId;

    @OperatorName
    @ApiModelProperty(value = "更新者id")
    private Long updatedId;

    @ApiModelProperty(value = "更新时间")
    private Date updatedTime;

    @ApiModelProperty(value = "创建时间")
    private Date createdTime;

    @ApiModelProperty(value = "参数列表")
    private String parameterList;

}
