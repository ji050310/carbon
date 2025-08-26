package com.carbon.assets.vo;

import com.carbon.domain.common.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;

import java.util.Date;

/**
 * <p>
 * 碳减排方法学 查询结果对象
 * </p>
 *
 * @author Li Jun
 * @since 2022-05-02
 */
@Data
@ApiModel(value="CarbonMethodologyQueryVo对象", description="碳减排方法学查询参数")
public class CarbonMethodologyQueryVo implements Serializable{
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "方法学名称")
    private String name;

    @ApiModelProperty(value = "方法学状态（字典）")
    private String status;

    @ApiModelProperty(value = "方法学状态编码")
    private String statusCode;

    @ApiModelProperty(value = "方法学类型（字典）")
    private String typeCode;

    @ApiModelProperty(value = "方法学编码")
    private String methodCode;

    @ApiModelProperty(value = "方法学字典编码")
    private String dictCode;

    @Dict(dictCode = "007")
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

    @Dict(dictCode = "003")
    @ApiModelProperty(value = "领域编码（字典：003）")
    private String fieldCode;

    @Dict(dictCode = "004")
    @ApiModelProperty(value = "子领域编码（字典：004）")
    private String fieldChildCode;

    @Dict(dictCode = "002")
    @ApiModelProperty(value = "行业编码（字典：002）")
    private String industryCode;

}
