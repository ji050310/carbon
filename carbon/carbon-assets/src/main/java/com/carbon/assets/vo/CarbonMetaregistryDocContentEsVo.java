package com.carbon.assets.vo;

import com.carbon.domain.common.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 项目元注册表内容 查询结果对象
 * </p>
 *
 * @author Cbd
 * @since 2022-08-15
 */
@Data
@Document(indexName = "esmetaregistry")
@ApiModel(value="CarbonMetaregistryDocContentEsVo对象", description="项目飞书文档内容参数")
public class CarbonMetaregistryDocContentEsVo{

    private Integer id=000;

    @ApiModelProperty(value = "仓库备案号")
    private String refId="";

    @ApiModelProperty(value = "字典编码")
    private String projectCode="";

    @ApiModelProperty(value = "项目名称")
    private String projectName="";

    @ApiModelProperty(value = "项目领域")
    private String projectScope="";

    private String projectScopeCode="";

    @ApiModelProperty(value = "项目类型")
    private String projectScopeType="";

    @ApiModelProperty(value = "项目类型")
    private String projectScopeTypeCode="";

    @ApiModelProperty(value = "行业")
    private String projectIndustryCode="";

    @ApiModelProperty(value = "项目业主")
    private String projectOwner="";

    @ApiModelProperty(value = "项目核证标准编码")
    private String certifiedStandardCode="";

    @ApiModelProperty(value = "项目核证标准")
    private String certifiedStandard="";

    @ApiModelProperty(value = "方法学字典编码")
    private String methodologyCode="";

    @ApiModelProperty(value = "方法学名称")
    private String methodologyName="";

    @ApiModelProperty(value = "方法学行业编码")
    private String industryCode="";

    @ApiModelProperty(value = "项目状态编码")
    private String projectStatusCode="";

    @ApiModelProperty(value = "项目状态")
    private String projectStatus="";

    @ApiModelProperty(value = "审定日期")
    private String approvalDate="";

    @ApiModelProperty(value = "备案日期")
    private String recordFilingDate="";

    @ApiModelProperty(value = "核证日期")
    private String certifiedDate="";

    @ApiModelProperty(value = "签发日期")
    private String issuingDate="";

    @ApiModelProperty(value = "预计年减排量")
    private String estimatedAnnualReduction="";

    @ApiModelProperty(value = "备案日期时间戳")
    private Long recordFilingTime=0L;

    @ApiModelProperty(value = "签发日期时间戳")
    private Long issuingTime=0L;

    @ApiModelProperty(value = "文档标题")
    private String title="";

    @ApiModelProperty(value = "文档备案号")
    private String projectRefId="";

    @ApiModelProperty(value = "文档类型编码")
    private String typeCode="";

    @ApiModelProperty(value = "文档内容")
    private String content="";

    @ApiModelProperty(value = "url")
    private String url="";
}
