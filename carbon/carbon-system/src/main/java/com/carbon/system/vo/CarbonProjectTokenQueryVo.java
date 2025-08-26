package com.carbon.system.vo;

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
 * @author Bae
 * @since 2022-06-27
 */
@Data
@ApiModel(value="CarbonProjectTokenQueryVo对象", description="查询参数")
public class CarbonProjectTokenQueryVo implements Serializable{
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "项目id")
    private String projectId;

    @ApiModelProperty(value = "项目飞书文档")
    private String fileToken;

}
