package com.carbon.domain.system.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 系统字典条目 查询结果对象
 * </p>
 *
 * @author Li Jun
 * @since 2022-04-25
 */
@Data
@ApiModel(value="SysDictItemQueryVo对象", description="系统字典条目查询参数")
public class SysDictModelVo implements Serializable{
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "字典项文本")
    private String name;

    @ApiModelProperty(value = "字典项值")
    private String value;

    @ApiModelProperty(value = "字典编码")
    private String dictCode;

}
