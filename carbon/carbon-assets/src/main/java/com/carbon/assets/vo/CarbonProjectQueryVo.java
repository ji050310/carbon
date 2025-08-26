package com.carbon.assets.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.carbon.assets.param.CarbonProjectOwnerDataAnnex;
import com.carbon.domain.common.annotation.Dict;
import com.carbon.domain.common.annotation.OperatorName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 碳减排项目 查询结果对象
 * </p>
 *
 * @author Li Jun
 * @since 2022-05-01
 */
@Data
@ApiModel(value="CarbonProjectQueryVo对象", description="碳减排项目查询参数")
public class CarbonProjectQueryVo implements Serializable{
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "业主名称")
    private String ownerName;

    @ApiModelProperty(value = "项目介绍")
    private String projectIntroduction;

    @ApiModelProperty(value = "开发负责人姓名")
    private String principalName;

    @ApiModelProperty(value = "开发负责人电话")
    private String principalPhone;

    @ApiModelProperty(value = "法人姓名")
    private String legalPersonName;

    @ApiModelProperty(value = "法人联系方式")
    private String legalPersonPhone;

    @Dict(dictCode = "010")
    @ApiModelProperty(value = "项目状态")
    private String projectStatus;

    @Dict(dictCode = "006")
    @ApiModelProperty(value = "国家")
    private String country;

    @Dict(dictCode = "011")
    @ApiModelProperty(value = "省份")
    private String province;

    @Dict(dictCode = "012")
    @ApiModelProperty(value = "城市")
    private String city;

    @ApiModelProperty(value = "项目地点")
    private String address;

    @ApiModelProperty(value = "资产开发机构")
    private String assetsDevelopAgency;

//    @Dict(dictCode = "009")
    @ApiModelProperty(value = "方法学字典")
    private String carbonMethodology;

    @ApiModelProperty(value = "方法学名称")
    private String carbonMethodologyName;

    @Dict(dictCode = "007")
    @ApiModelProperty(value = "核证标准")
    private String certificationCriteria;

    @Dict(dictCode = "003")
    @ApiModelProperty(value = "领域类型编码（字典：003）")
    private String fieldCode;

    @Dict(dictCode = "004")
    @ApiModelProperty(value = "子领域编码（字典：004）")
    private String fieldChildCode;

    @Dict(dictCode = "002")
    @ApiModelProperty(value = "行业编码（字典：002）")
    private String industryCode;

    @ApiModelProperty(value = "项目设计文档")
    private String designDocument;

    @ApiModelProperty(value = "审定日期")
    private Date approvalDate;

    @ApiModelProperty(value = "备案日期")
    private Date recordFilingDate;

    @ApiModelProperty(value = "核证日期")
    private Date certifiedDate;

    @ApiModelProperty(value = "立项日期")
    private Date initiationDate;

    @ApiModelProperty(value = "签发日期")
    private Date issuingDate;

    @ApiModelProperty(value = "所属租户")
    private Long tenantId;

    @OperatorName
    @ApiModelProperty(value = "创建人ID")
    private Long creatorId;

    @ApiModelProperty(value = "创建时间")
    private Date createdTime;

    @OperatorName
    @ApiModelProperty(value = "更新人ID")
    private Long updatedId;

    @ApiModelProperty(value = "更新时间")
    private Date updatedTime;


    @ApiModelProperty(value = "附件列表")
    private List<CarbonProjectOwnerDataAnnex> annexList;
}
