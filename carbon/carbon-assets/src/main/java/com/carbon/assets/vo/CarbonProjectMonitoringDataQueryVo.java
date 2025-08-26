package com.carbon.assets.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;

import java.util.Date;

/**
 * <p>
 * 碳减排项目监测数据 查询结果对象
 * </p>
 *
 * @author Li Jun
 * @since 2022-05-11
 */
@Data
@ApiModel(value="CarbonProjectMonitoringDataQueryVo对象", description="碳减排项目监测数据查询参数")
public class CarbonProjectMonitoringDataQueryVo implements Serializable{
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "项目ID")
    private Long carbonProjectId;

    @ApiModelProperty(value = "状态")
    private String status;

    @ApiModelProperty(value = "参数")
    private String parameter;

    @ApiModelProperty(value = "单位")
    private String unit;

    @ApiModelProperty(value = "来源")
    private String source;

    @ApiModelProperty(value = "数据值")
    private String dateValue;

    @ApiModelProperty(value = "测量方法")
    private String measuringMethod;

    @ApiModelProperty(value = "监测频率")
    private String frequency;

    @ApiModelProperty(value = "AQ/CQ 程序")
    private String program;

    @ApiModelProperty(value = "数据用途")
    private String purpose;

    @ApiModelProperty(value = "评价")
    private String appraise;

}
