package com.carbon.assets.param;

import com.carbon.domain.common.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import com.carbon.domain.common.QueryParam;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  查询参数对象
 * </p>
 *
 * @author ChengJX
 * @since 2022-08-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="CarbonMetaregistryDocQueryParam对象", description="查询参数")
public class CarbonMetaregistryDocQueryParam extends QueryParam {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "项目仓库id")
    private Long registryId;

    @ApiModelProperty(value = "搜索关键词")
    private String searchKey;

    @ApiModelProperty(value = "方法学关键词")
    private String methodSearchKey;

    @Dict(dictCode = "021")
    @ApiModelProperty(value = "文档类型")
    private String type;

    @ApiModelProperty(value = "文档标题")
    private String title;

    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "核证标准字典编码")
    private String certifiedStandardCode;

    @ApiModelProperty(value = "项目领域")
    private String projectScopeCode;

    @ApiModelProperty(value = "行业编码")
    private String projectIndustryCode;

    @ApiModelProperty(value = "状态编码")
    private String projectStatusCode;

    @ApiModelProperty(value = "项目类型")
    private String projectTypeCode;

    @ApiModelProperty(value = "备案日期")
    private String recordFilingDate;

    @ApiModelProperty(value = "起始备案时间")
    private String refDateStart;

    @ApiModelProperty(value = "结束备案时间")
    private String refDateEnd;

    @ApiModelProperty(value = "起始签发时间")
    private String issueDateStart;

    @ApiModelProperty(value = "结束签发时间")
    private String issueDateEnd;

    @ApiModelProperty(value = "完成日期-开始")
    private Date completionDateStart;

    @ApiModelProperty(value = "完成日期-结束")
    private Date completionDateEnd;

    private List<String> methodCodeList;
}
