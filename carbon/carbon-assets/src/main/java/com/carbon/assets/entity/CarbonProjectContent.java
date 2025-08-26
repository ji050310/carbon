package com.carbon.assets.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.carbon.domain.common.BaseEntity;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

/**
 * <p>
 * 碳减排项目文档内容
 * </p>
 *
 * @author Cbd
 * @since 2022-08-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Document(indexName = "esproject")
@ApiModel(value="CarbonProjectContent对象", description="碳减排项目文档内容")
public class CarbonProjectContent extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "项目id")
    private Long projectId;

    @ApiModelProperty(value = "项目名称")
    @Field(analyzer = "ik_max_word",type = FieldType.Text)
    private String projectName;

    @ApiModelProperty(value = "项目内容")
    @Field(analyzer = "ik_max_word",type = FieldType.Text)
    private String projectContent;

    @ApiModelProperty(value = "内容url")
    private String contentUrl;

    @ApiModelProperty(value = "业主名称")
    private String ownerName;

    @ApiModelProperty(value = "项目领域字典编码")
    private String projectScopeCode;

    @Field(analyzer = "ik_max_word",type = FieldType.Text)
    @ApiModelProperty(value = "项目领域")
    private String projectScope;

    @ApiModelProperty(value = "项目类型编码")
    private String projectTypeCode;

    @ApiModelProperty(value = "项目类型")
    private String projectType;

    @ApiModelProperty(value = "项目状态")
    private String projectStatus;

    @ApiModelProperty(value = "项目地点")
    private String address;

    @ApiModelProperty(value = "碳减排方法学字典编码")
    private String carbonMethodology;

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

    @ApiModelProperty(value = "产生减排量时间")
    private String reductionDate;

    @ApiModelProperty(value = "所属租户")
    private Long tenantId;

    @ApiModelProperty(value = "业主名称编码")
    private String ownerNameCode;

    @ApiModelProperty("创建者id")
    private Long creatorId;
}
