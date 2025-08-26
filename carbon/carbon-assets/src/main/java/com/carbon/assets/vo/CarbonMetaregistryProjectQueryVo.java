package com.carbon.assets.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;

import java.util.Date;

/**
 * <p>
 * 租户碳减排项目 查询结果对象
 * </p>
 *
 * @author ChengJX
 * @since 2022-08-22
 */
@Data
@ApiModel(value="CarbonMetaregistryProjectQueryVo对象", description="租户碳减排项目查询参数")
public class CarbonMetaregistryProjectQueryVo implements Serializable{
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "项目库id")
    private Integer metaregistryId;

    @ApiModelProperty(value = "项目介绍")
    private String projectIntroduction;

    @ApiModelProperty(value = "所属租户")
    private Long tenantId;

}
