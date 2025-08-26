package com.carbon.system.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 *  查询结果对象
 * </p>
 *
 * @author JinRui Zhang
 * @since 2022-06-21
 */
@Data
@ApiModel(value="CarbonApprovalQueryVo", description="查询参数")
public class CarbonApprovalQueryVo implements Serializable{
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "业务ID")
    private Long businessId;

    @ApiModelProperty(value = "审批定义code")
    private String approvalCode;

    @ApiModelProperty(value = "审批实例code")
    private String instanceCode;

}
