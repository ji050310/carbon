package com.carbon.assets.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.carbon.domain.common.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * <p>
 * 碳减排项目监测数据
 * </p>
 *
 * @author Li Jun
 * @since 2022-05-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="CarbonProjectMonitoringData对象", description="碳减排项目监测数据")
public class CarbonProjectMonitoringData extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
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
