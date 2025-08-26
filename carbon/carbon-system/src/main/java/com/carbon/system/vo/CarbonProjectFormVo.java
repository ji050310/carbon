package com.carbon.system.vo;

import com.carbon.domain.common.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * <p>
 * 碳减排项目表单更新
 * </p>
 *
 * @author Li Jun
 * @since 2022-05-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="碳减排项目表单更新", description="碳减排项目表单更新")
public class CarbonProjectFormVo extends BaseEntity {

    @ApiModelProperty(value = "业主名称")
    private String ownerName;

    @ApiModelProperty(value = "法人联系方式")
    private String legalPersonPhone;

    @ApiModelProperty(value = "法人姓名")
    private String legalPersonName;

    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "碳减排方法学字典编码")
    private String carbonMethodology;

    @ApiModelProperty(value = "国家")
    private String country;

    @ApiModelProperty(value = "省份")
    private String province;

    @ApiModelProperty(value = "城市")
    private String city;

    @ApiModelProperty(value = "项目地点")
    private String address;

    @ApiModelProperty(value = "资产开发机构")
    private String assetsDevelopAgency;

    @ApiModelProperty(value = "开发负责人姓名")
    private String principalName;

    @ApiModelProperty(value = "开发负责人电话")
    private String principalPhone;

    @ApiModelProperty(value = "项目介绍")
    private String projectIntroduction;

    @ApiModelProperty(value = "材料信息（打包文件）")
    private String materialInformation;

    @ApiModelProperty(value = "立项日期")
    private String initiationDate;

    @ApiModelProperty(value = "项目状态")
    private String projectStatus;

    @ApiModelProperty(value = "审批人")
    private String approvalPerson;

    @ApiModelProperty(value = "审批备注")
    private String approvalMark;




}
