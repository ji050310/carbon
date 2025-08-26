package com.carbon.assets.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
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
@ApiModel(value="CarbonMetaregistryDocContentQueryVo对象", description="项目元注册表内容查询参数")
public class CarbonMetaregistryDocContentQueryVo implements Serializable{
    private static final long serialVersionUID = 1L;

    private Integer id;

    @ApiModelProperty(value = "项目仓库id")
    private Integer registryId;

    private Long projectMetaId;

    private String content;

    private String type;

    private String title;

    private String url;

    private Date finishTime;

    private Date createTime;

    private Date updateTime;

}
