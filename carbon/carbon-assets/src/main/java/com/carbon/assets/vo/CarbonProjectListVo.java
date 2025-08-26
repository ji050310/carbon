package com.carbon.assets.vo;

import com.carbon.assets.param.CarbonProjectOwnerDataAnnex;
import com.carbon.domain.common.annotation.Dict;
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
@ApiModel(value="CarbonProjectListVo", description="碳减排项目列表vo")
public class CarbonProjectListVo implements Serializable{
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "业主名称")
    private String ownerName;

    @Dict(dictCode = "010")
    @ApiModelProperty(value = "项目状态")
    private String projectStatus;

    @Dict(dictCode = "031")
    @ApiModelProperty(value = "数据报送状态")
    private String dataSubmissionStatus;

    @ApiModelProperty(value = "项目地点")
    private String address;

    //    @Dict(dictCode = "009")
    @ApiModelProperty(value = "碳减排方法学字典编码")
    private String carbonMethodology;

    @ApiModelProperty(value = "碳减排方法学名称")
    private String carbonMethodologyName;

    @ApiModelProperty(value = "项目领域字典编码")
    @Dict(dictCode = "003")
    private String projectScopeCode;

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

    @ApiModelProperty(value = "项目设计文档")
    private String designDocument;

    @ApiModelProperty(value = "指标code")
    private String indexCode;

    @ApiModelProperty(value = "开发负责人姓名")
    private String principalName;

    @ApiModelProperty(value = "开发负责人电话")
    private String principalPhone;

    @ApiModelProperty(value = "开发机构")
    private String assetsDevelopAgency;

    @ApiModelProperty(value = "国家")
    @Dict(dictCode = "006")
    private String country;

    @ApiModelProperty(value = "省份")
    @Dict(dictCode = "011")
    private String province;

    @ApiModelProperty(value = "城市")
    @Dict(dictCode = "012")
    private String city;

    @ApiModelProperty(value = "开发跟进人")
    private String developmentFollower;

    @ApiModelProperty(value = "项目信息")
    private String projectMsg;

    @ApiModelProperty(value = "备注")
    private String remarks;

    private List parameterValueList;
}
