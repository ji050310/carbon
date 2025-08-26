package com.carbon.assets.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;

import java.util.Date;

/**
 * <p>
 * 碳减排方法学文档 查询结果对象
 * </p>
 *
 * @author Cbd
 * @since 2022-08-09
 */
@Data
@ApiModel(value="CarbonMethodologyContentQueryVo对象", description="碳减排方法学文档查询参数")
public class CarbonMethodologyContentQueryVo implements Serializable{
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "方法学名称")
    private String name;

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

}
