package com.carbon.mq.vo;

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
 * @author JinRui Zhang
 * @since 2022-06-21
 */
@Data
@ApiModel(value="CarbonProjectInstanceQueryVo对象", description="查询参数")
public class CarbonProjectInstanceQueryVo implements Serializable{
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "项目ID")
    private Long projectId;

    @ApiModelProperty(value = "项目审批实例code")
    private String instanceCode;

}
