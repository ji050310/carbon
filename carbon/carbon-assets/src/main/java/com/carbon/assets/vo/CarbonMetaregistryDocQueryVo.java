package com.carbon.assets.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;

import java.util.Date;

/**
 * <p>
 *  查询结果对象
 * </p>
 *
 * @author ChengJX
 * @since 2022-08-08
 */
@Data
@ApiModel(value="CarbonMetaregistryDocQueryVo对象", description="查询参数")
public class CarbonMetaregistryDocQueryVo implements Serializable{
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "项目备案号")
    private Long projectRefId;

    @ApiModelProperty(value = "项目仓库id")
    private Integer registryId;

    @ApiModelProperty(value = "项目仓库名称")
    private String projectRegistryName;

    @ApiModelProperty(value = "项目类型项目类型")
    private String type;
    @ApiModelProperty(value = "文档名称")
    private String title;
    @ApiModelProperty(value = "文档url")
    private String url;

    private Date finishTime;

    private Date createTime;

    private Date updateTime;

}
