package com.carbon.assets.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import com.carbon.domain.common.QueryParam;

import java.util.Date;

/**
 * <p>
 * 项目仓库 查询参数对象
 * </p>
 *
 * @author ChengJX
 * @since 2022-08-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="CarbonMetaregistryQueryParam对象", description="项目仓库查询参数")
public class CarbonMetaregistryQueryParam extends QueryParam {
    private static final long serialVersionUID = 1L;
     // 增加一个接口请求type,如果是 【碳资产管理】【碳信用】选择项目，时候得传 true,默认false
    //  @ApiModelProperty(value = "是 【碳资产管理】【碳信用】选择项目，时候得传 true,默认false")
    //  private Boolean isCreditAssets = false;

    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "项目核证标准")
    private String certifiedStandardCode;

    @ApiModelProperty(value = "项目所属行业字典码编码")
    private String projectIndustryCode;

    @ApiModelProperty(value = "项目状态编码")
    private String projectStatusCode;

    @ApiModelProperty(value = "项目领域编码")
    private String projectScopeCode;

    @ApiModelProperty(value = "方法学名称")
    private String methodologyName;

    @ApiModelProperty(value = "方法学字典编码")
    private String methodologyCode;

    @ApiModelProperty(value = "备案日期-开始")
    private Date refDateStart;

    @ApiModelProperty(value = "备案日期-结束")
    private Date refDateEnd;

    @ApiModelProperty(value = "项目类型")
    private String projectTypeCode;

    @ApiModelProperty(value = "签发日期-开始")
    private Date issueDateStart;

    @ApiModelProperty(value = "签发日期-结束")
    private Date issueDateEnd;
}
