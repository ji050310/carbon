package com.carbon.domain.assets.vo;

import com.carbon.domain.common.QueryParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @description:
 * @author: code534
 * @time: 2022/7/15 16:24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="上传方法学参数对象", description="上传方法学参数对象")
public class MethodologyUploadParam extends QueryParam {

    @ApiModelProperty(value = "方法学编码")
    private String methodCode;

    @ApiModelProperty(value = "方法学名称")
    private String name;

    @ApiModelProperty(value = "领域编码（字典：003）")
    private String fieldCode;

    @ApiModelProperty(value = "行业编码（字典：002）")
    private String industryCode;

    @ApiModelProperty(value = "核证标准（字典：007）")
    private String certificationCriteria;

    @ApiModelProperty(value = "飞书pdf路径")
    private String pdfUrl="";

    @ApiModelProperty(value = "飞书word路径")
    private String wordUrl="";

    @ApiModelProperty(value = "方法学字典编码")
    private String dictCode;

}
