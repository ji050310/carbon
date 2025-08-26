package com.carbon.system.param;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@ApiModel(value="SysDictAddParam对象", description="系统字典添加/修改参数")
public class SysDictAddParam implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    private Long id;

    @ApiModelProperty(value = "字典编码")
    private String dictCode;

    @NotBlank(message = "字典名称不能为空")
    @ApiModelProperty(value = "字典名称")
    private String dictName;

    @ApiModelProperty(value = "字典描述")
    private String description;

    @NotNull(message = "字典类型不能为空")
    @ApiModelProperty(value = "字典类型")
    private Integer type;

}
