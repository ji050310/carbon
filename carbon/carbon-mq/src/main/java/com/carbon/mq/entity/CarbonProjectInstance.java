package com.carbon.mq.entity;

import com.carbon.domain.common.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author JinRui Zhang
 * @since 2022-06-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value="CarbonProjectInstance对象", description="")
public class CarbonProjectInstance extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "项目ID")
    private String projectId;

    @ApiModelProperty(value = "项目审批实例code")
    private String instanceCode;

}
