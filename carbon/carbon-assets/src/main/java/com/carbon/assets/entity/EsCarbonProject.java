//package com.carbon.assets.entity;
//
//import com.baomidou.mybatisplus.annotation.IdType;
//import com.baomidou.mybatisplus.annotation.TableId;
//import com.carbon.domain.common.BaseEntity;
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
//import lombok.Data;
//import lombok.EqualsAndHashCode;
//
//import java.util.Date;
//
///**
// * <p>
// * 碳减排项目
// * </p>
// *
// * @author Li Jun
// * @since 2022-05-01
// */
//@Data
//@EqualsAndHashCode(callSuper = true)
//@ApiModel(value="CarbonProject对象", description="碳减排项目")
//public class EsCarbonProject extends BaseEntity {
//
//    private static final long serialVersionUID = 1L;
//
//    @ApiModelProperty(value = "主键")
//    @TableId(value = "id", type = IdType.AUTO)
//    private Long id;
//
//    @ApiModelProperty(value = "项目名称")
//    private String projectName;
//
//    @ApiModelProperty(value = "业主名称")
//    private String ownerName;
//
//    @ApiModelProperty(value = "项目介绍")
//    private String projectIntroduction;
//
//    @ApiModelProperty(value = "开发负责人姓名")
//    private String principalName;
//
//    @ApiModelProperty(value = "开发负责人电话")
//    private String principalPhone;
//
//    @ApiModelProperty(value = "法人姓名")
//    private String legalPersonName;
//
//    @ApiModelProperty(value = "法人联系方式")
//    private String legalPersonPhone;
//
//    @ApiModelProperty(value = "项目类型")
//    private String projectType;
//
//    @ApiModelProperty(value = "项目状态")
//    private String projectStatus;
//
//    @ApiModelProperty(value = "数据报送状态")
//    private String dataSubmissionStatus;
//
//    @ApiModelProperty(value = "国家")
//    private String country;
//
//    @ApiModelProperty(value = "省份")
//    private String province;
//
//    @ApiModelProperty(value = "城市")
//    private String city;
//
//    @ApiModelProperty(value = "项目地点")
//    private String address;
//
//    @ApiModelProperty(value = "资产开发机构")
//    private String assetsDevelopAgency;
//
//    @ApiModelProperty(value = "碳减排方法学字典编码")
//    private String carbonMethodology;
//
//    @ApiModelProperty(value = "立项日期")
//    private Date initiationDate;
//
//    @ApiModelProperty(value = "审定日期")
//    private Date approvalDate;
//
//    @ApiModelProperty(value = "备案日期")
//    private Date recordFilingDate;
//
//    @ApiModelProperty(value = "核证日期")
//    private Date certifiedDate;
//
//    @ApiModelProperty(value = "签发日期")
//    private Date issuingDate;
//
//    @ApiModelProperty(value = "所属租户")
//    private Long tenantId;
//
//    private String
//
//}
