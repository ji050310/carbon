package com.carbon.assets.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.carbon.domain.common.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 项目仓库
 * </p>
 *
 * @author ChengJX
 * @since 2022-08-09
 */
@Data
@ApiModel(value="CarbonMetaregistry对象", description="项目仓库")
public class CarbonMetaregistry{

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "备案号")
    private String refId;

    @ApiModelProperty(value = "字典编码")
    private String projectCode;

    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "项目领域")
    private String projectScope;

    private String projectScopeCode;

    @ApiModelProperty(value = "项目类型")
    private String projectScopeType;

    private String projectScopeTypeCode;

    @ApiModelProperty(value = "项目业主")
    private String projectOwner;

    @ApiModelProperty(value = "项目开发商")
    private String projectDeveloper;

    @ApiModelProperty(value = "项目核证标准")
    private String certifiedStandard;

    @ApiModelProperty(value = "采用方法学编号")
    private String methodologySn;

    @ApiModelProperty(value = "方法学字典编码")
    private String methodologyCode;

    @ApiModelProperty(value = "方法学名称")
    private String methodologyName;

    @ApiModelProperty(value = "项目状态编码")
    private String projectStatusCode;

    @ApiModelProperty(value = "项目状态")
    private String projectStatus;

    @ApiModelProperty(value = "项目品质")
    private String projectQuality;

    @ApiModelProperty(value = "世界区域")
    private String projectArea;

    @ApiModelProperty(value = "所处国家")
    private String projectCountry;

    @ApiModelProperty(value = "省/州")
    private String projectProvince;

    @ApiModelProperty(value = "项目位置")
    private String projectSite;

    @ApiModelProperty(value = "预计年减排量")
    private String estimatedAnnualReduction;

    @ApiModelProperty(value = "备案减排量")
    private String refReduction;

    @ApiModelProperty(value = "签发量")
    private String issuedReduction;

    @ApiModelProperty(value = "注销量")
    private String retiredReduction;

    @ApiModelProperty(value = "剩余量")
    private String remainingReduction;

    @ApiModelProperty(value = "项目链接")
    private String projectUrl;

    @ApiModelProperty(value = "审定机构")
    private String projectAuditor;

    @ApiModelProperty(value = "备案时间")
    private Date recordFilingDate;

    @ApiModelProperty(value = "公示时间")
    private Date pubTime;

    @ApiModelProperty(value = "签发时间")
    private Date issuingDate;

    @ApiModelProperty(value = "核证机构")
    private String projectVerifier;

    @ApiModelProperty(value = "提交时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
}
