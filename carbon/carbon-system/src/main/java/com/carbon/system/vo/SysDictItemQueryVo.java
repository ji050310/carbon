package com.carbon.system.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;

import java.util.Date;

/**
 * <p>
 * 系统字典条目 查询结果对象
 * </p>
 *
 * @author Jiang zhenhua
 * @since 2022-05-11
 */
@Data
@ApiModel(value="SysDictItemQueryVo对象", description="系统字典条目查询参数")
public class SysDictItemQueryVo implements Serializable{
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "字典项目id")
    private Long id;

    @ApiModelProperty(value = "字典编码")
    private String dictCode;

    @ApiModelProperty(value = "字典value")
    private String itemValue;

    @ApiModelProperty(value = "字典中文")
    private String itemCh;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "状态（1启用 0不启用）")
    private Integer status;

}
