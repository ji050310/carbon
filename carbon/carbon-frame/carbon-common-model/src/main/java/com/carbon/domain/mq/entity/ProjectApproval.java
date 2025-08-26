package com.carbon.domain.mq.entity;

import com.carbon.domain.common.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 项目立项审批表单MQ实体类
 *
 * @author Bae
 * @version 1.0
 * @date 2022/6/5 22:11
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value="项目立项审批表单MQ实体类", description="项目立项审批表单MQ实体类")
public class ProjectApproval {

    @ApiModelProperty(value = "项目ID")
    private Long projectId;

    @ApiModelProperty(value = "业主法人姓名")
    private String legalPersonName;

    @ApiModelProperty(value = "法人联系方式")
    private String legalPersonPhone;

    @ApiModelProperty(value = "业主名称")
    private String ownerName;

    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "采用方法学")
    private String carbonMethodology;

    @ApiModelProperty(value = "国家")
    private String country;

    @ApiModelProperty(value = "省份")
    private String province;

    @ApiModelProperty(value = "市/县/郡")
    private String city;

    @ApiModelProperty(value = "项目地点")
    private String address;

    @ApiModelProperty(value = "开发机构")
    private String developAgencies;

    @ApiModelProperty(value = "开发负责人")
    private String principalName;

    @ApiModelProperty(value = "开发负责人电话")
    private String principalPhone;

    @ApiModelProperty(value = "项目信息")
    private String projectIntroduction;

}
