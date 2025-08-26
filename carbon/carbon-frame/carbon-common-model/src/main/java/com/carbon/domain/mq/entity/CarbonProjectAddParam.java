package com.carbon.domain.mq.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * <p>
 * 碳减排项目 添加参数
 * </p>
 *
 * @author Li Jun
 * @since 2021-07-31
 */
@Data
@ApiModel(value="CarbonProjectAddParam", description="碳减排项目 添加参数")
public class CarbonProjectAddParam implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "项目ID（）")
    private Long id;

    @NotEmpty(message = "项目名称不能为空")
    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @NotEmpty(message = "业主名称不能为空")
    @ApiModelProperty(value = "业主名称")
    private String ownerName;

    @ApiModelProperty(value = "项目介绍")
    private String projectIntroduction;

    @ApiModelProperty(value = "项目文档(项目材料)")
    private String designDocument;

    @ApiModelProperty(value = "项目类型")
    private String projectType;

    @NotEmpty(message = "国家不能为空")
    @ApiModelProperty(value = "国家")
    private String country;

    @NotEmpty(message = "省份不能为空")
    @ApiModelProperty(value = "省份")
    private String province;

    @NotEmpty(message = "城市不能为空")
    @ApiModelProperty(value = "城市")
    private String city;

    @NotEmpty(message = "项目地点不能为空")
    @ApiModelProperty(value = "项目地点")
    private String address;

    @NotEmpty(message = "资产开发机构不能为空")
    @ApiModelProperty(value = "资产开发机构")
    private String assetsDevelopAgency;

    @ApiModelProperty(value = "碳减排方法学字典编码")
    private String carbonMethodology;


    @ApiModelProperty(value = "开发负责人姓名")
    private String principalName;

    @ApiModelProperty(value = "开发负责人电话")
    private String principalPhone;

    @ApiModelProperty(value = "法人姓名")
    private String legalPersonName;

    @ApiModelProperty(value = "法人联系方式")
    private String legalPersonPhone;
}
