package com.carbon.assets.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 碳减排方法学
 * </p>
 *
 * @author Li Jun
 * @since 2022-05-02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="CarbonMethodology对象", description="碳减排方法学")
public class CarbonMethodology extends EsBaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "方法学名称")
    private String name;

    @ApiModelProperty(value = "方法学状态（字典）")
    private String statusCode;

    @ApiModelProperty(value = "方法学类型（字典）")
    private String typeCode;

    @ApiModelProperty(value = "方法学编码")
    private String methodCode;

    @ApiModelProperty(value = "方法学字典编码")
    private String dictCode;

    @ApiModelProperty(value = "核证标准（字典：007）")
    private String certificationCriteria;

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

    @ApiModelProperty(value = "子领域编码（字典：004）")
    private String fieldChildCode;

    @ApiModelProperty(value = "行业编码（字典：002）")
    private String industryCode;

//    @ApiModelProperty(value = "行业名称")
//    private String industryCodeName;

    @ApiModelProperty(value = "创建者id")
    private Long creatorId;

    @ApiModelProperty(value = "更新人id")
    private Long updatedId;

//    @ApiModelProperty(value = "创建时间")
//    private Date createdTime;
//
//    @ApiModelProperty(value = "更新时间")
//    private Date updatedTime;

}
