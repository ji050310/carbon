package com.carbon.system.entity;

import com.carbon.domain.common.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 飞书审批记录
 * </p>
 *
 * @author JinRui Zhang
 * @since 2022-06-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value="CarbonApproval", description="飞书审批记录")
public class CarbonApproval extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "业务ID")
    private Long businessId;

    @ApiModelProperty(value = "审批定义code")
    private String approvalCode;

    @ApiModelProperty(value = "审批实例code")
    private String instanceCode;

}
