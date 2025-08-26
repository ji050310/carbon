package com.carbon.assets.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.carbon.domain.common.BaseEntity;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * <p>
 * 碳减排项目
 * </p>
 *
 * @author Li Jun
 * @since 2022-05-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="CarbonProject对象", description="碳减排项目")
public class CarbonProject extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "项目领域")
    private String projectScope;

    private String projectScopeCode;


    @ApiModelProperty(value = "业主名称")
    private String ownerName;

    @ApiModelProperty(value = "项目介绍")
    private String projectIntroduction;

    @ApiModelProperty(value = "项目文档(项目材料)")
    private String designDocument;

    @ApiModelProperty(value = "开发负责人姓名")
    private String principalName;

    @ApiModelProperty(value = "开发负责人电话")
    private String principalPhone;

    @ApiModelProperty(value = "法人姓名")
    private String legalPersonName;

    @ApiModelProperty(value = "法人联系方式")
    private String legalPersonPhone;

    @ApiModelProperty(value = "项目类型")
    private String projectType;

    private String projectTypeCode;

    @ApiModelProperty(value = "项目状态")
    private String projectStatus;

    @ApiModelProperty(value = "数据报送状态")
    private String dataSubmissionStatus;

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

    @ApiModelProperty(value = "碳减排方法学字典编码")
    private String carbonMethodology;

    @ApiModelProperty(value = "立项日期")
    private Date initiationDate;

    @ApiModelProperty(value = "审定日期")
    private Date approvalDate;

    @ApiModelProperty(value = "备案日期")
    private Date recordFilingDate;

    @ApiModelProperty(value = "核证日期")
    private Date certifiedDate;

    @ApiModelProperty(value = "签发日期")
    private Date issuingDate;

    @ApiModelProperty(value = "所属租户")
    private Long tenantId;

    @ApiModelProperty(value = "备案号")
    private String refId;

    @ApiModelProperty(value = "开发跟进人")
    private String developmentFollower;

    @ApiModelProperty(value = "项目信息")
    private String projectMsg;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "预计减排量")
    private String estimatedReduction;
}
