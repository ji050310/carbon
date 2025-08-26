package com.carbon.assets.vo;

import com.carbon.domain.common.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

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
public class CarbonMetaregistryDocContentquVo implements Serializable{
    private static final long serialVersionUID = 1L;

    private Integer id;

    @ApiModelProperty(value = "项目编码")
    private String projectCode;

    @ApiModelProperty(value = "项目名称")
    @Field(analyzer = "ik_max_word",type = FieldType.Text)
    private String projectName;

    @ApiModelProperty(value = "业主名称")
    private String ownerName;

    @ApiModelProperty(value = "领域编码")
    private String projectScopeCode;

    @ApiModelProperty(value = "领域")
    private String projectScope;

    @ApiModelProperty(value = "类型编码")
    private String projectTypeCode;

    @ApiModelProperty(value = "项目类型")
    private String projectType;

    @ApiModelProperty(value = "所用方法学编码")
    private String carbonMethodology;

    @ApiModelProperty(value = "方法学名称")
    private String carbonMethodologyName;

    @ApiModelProperty(value = "行业编码")
    @Dict(dictCode = "002")
    private String industryCode;

    @ApiModelProperty(value = "状态编码")
    private String ProjectStatusCode;

    @ApiModelProperty(value = "状态")
    private String ProjectStatus;

    @ApiModelProperty(value = "立项日期")
    private String InitiationDate;

    @ApiModelProperty(value = "项目仓库id")
    private String registryId;

    @ApiModelProperty(value = "备案号")
    private String projectRefId;

    @ApiModelProperty(value = "预计年减排量")
    private String estimatedAnnualReduction;

    @ApiModelProperty(value = "文档类型")
    private String type;

    @ApiModelProperty(value = "文档类型编码")
    private String typeCode;

    @ApiModelProperty(value = "标题")
    @Field(analyzer = "ik_max_word",type = FieldType.Text)
    private String title;

    @ApiModelProperty(value = "url")
    private String url;

    @ApiModelProperty(value = "核证标准字典编码")
    @Dict(dictCode = "007")
    private String certifiedStandardCode;

    @ApiModelProperty(value = "备案日期")
    private String recordFilingDate;

    @ApiModelProperty(value = "签发日期")
    private String issuingDate;

    @ApiModelProperty(value = "核证日期")
    private String certifiedDate;

    @ApiModelProperty(value = "审定日期")
    private String approvalDate;

    @ApiModelProperty(value = "结束时间")
    private String finishTime;

    @ApiModelProperty(value = "创建时间")
    private String createTime;

    @ApiModelProperty(value = "更新时间")
    private String updateTime;

}
