package com.carbon.assets.vo;

import com.carbon.assets.entity.CarbonMetaregistryDoc;
import com.carbon.domain.common.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 项目仓库 查询结果对象
 * </p>
 *
 * @author ChengJX
 * @since 2022-08-09
 */
@Data
@ApiModel(value="CarbonMetaregistryQueryVo对象", description="项目仓库查询参数")
public class CarbonMetaregistryQueryVo implements Serializable{
    private static final long serialVersionUID = 1L;

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

    @ApiModelProperty(value = "项目业主")
    private String projectOwner;

    @ApiModelProperty(value = "项目核证标准编码")
    private String certifiedStandardCode;

    @ApiModelProperty(value = "项目核证标准")
    private String certifiedStandard;

    @ApiModelProperty(value = "方法学字典编码")
    private String methodologyCode;

    @ApiModelProperty(value = "方法学名称")
    private String methodologyName;

    @ApiModelProperty(value = "项目状态编码")
    private String ProjectStatusCode;

    @ApiModelProperty(value = "项目状态")
    private String ProjectStatus;

    @ApiModelProperty(value = "审定日期")
    private String approvalDate="";

    @ApiModelProperty(value = "备案日期")
    private String recordFilingDate="";

    @ApiModelProperty(value = "核证日期")
    private Date certifiedDate;

    @ApiModelProperty(value = "签发日期")
    private String issuingDate="";

    @ApiModelProperty(value = "预计年减排量")
    private String estimatedAnnualReduction;

    @ApiModelProperty(value = "文档标题")
    private String title;

    @ApiModelProperty(value = "文档内容")
    private String content;

    @ApiModelProperty(value = "所处国家")
    private String projectCountry;

    @ApiModelProperty(value = "所处省/州")
    private String projectProvince;

    @ApiModelProperty(value = "项目地点")
    private String projectSite;

    @ApiModelProperty("项目开发商")
    private String projectDeveloper;
}
