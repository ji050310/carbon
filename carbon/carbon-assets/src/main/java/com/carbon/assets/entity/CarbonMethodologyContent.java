package com.carbon.assets.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.carbon.domain.common.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;
import com.carbon.domain.common.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

/**
 * <p>
 * 碳减排方法学文档
 * </p>
 *
 * @author Cbd
 * @since 2022-08-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Document(indexName = "esmethodology")
@ApiModel(value="CarbonMethodologyContent对象", description="碳减排方法学文档")
public class CarbonMethodologyContent extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id",type =IdType.AUTO )
    @Id
    private Long id;

    @ApiModelProperty(value = "方法学id")
    private Long methodId;

    @Field(analyzer = "ik_max_word",type = FieldType.Text)
    @ApiModelProperty(value = "方法学名称")
    private String name;

    @Field(analyzer = "ik_max_word",type = FieldType.Text)
    @ApiModelProperty(value = "方法学内容")
    private String content;

    @ApiModelProperty(value = "方法学状态（字典）")
    private String status;

    @ApiModelProperty(value = "方法学状态编码")
    private String statusCode;

    private String typeCode;

    @ApiModelProperty(value = "方法学编码")
    private String methodCode;

    @ApiModelProperty(value = "方法学字典编码")
    private String dictCode;

    @ApiModelProperty(value = "核证标准（字典：007）")
    private String certificationCriteria;

    @ApiModelProperty(value = "核证标准中文值")
    private String certificationCriteriaName;

    @ApiModelProperty(value = "方法学原文件url")
    private String sourceFileUrl;

    @ApiModelProperty(value = "引用的CDM方法学编号")
    private String cdmCode;

    @ApiModelProperty(value = "翻译版本号")
    private String translateVersion;

    @ApiModelProperty(value = "备案批次")
    private String filingBatch;

    @ApiModelProperty(value = "领域编码（字典：003）")
    private String fieldCode;

    @ApiModelProperty(value = "领域")
    private String fieldCodeName;

    @ApiModelProperty(value = "子领域编码（字典：004）")
    private String fieldChildCode;

    @ApiModelProperty(value = "行业编码（字典：002）")
    private String industryCode;

    @Field(analyzer = "ik_max_word",type = FieldType.Text)
    @ApiModelProperty(value = "行业名称")
    private String industryCodeName;

}
