package com.carbon.system.param;


import com.carbon.domain.common.QueryParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * <p>
 * 系统字典条目 新增参数对象
 * </p>
 *
 * @author Jiang zhenhua
 * @since 2022-05-13
 */
@Data
@ApiModel(value="SysDictItemAddParam对象", description="系统字典条目添加参数")
public class SysDictItemAddParam implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "字典项id")
    private Long id;

    @NotBlank
    @ApiModelProperty(value = "字典编号")
    private String dictCode;

    @NotBlank
    @ApiModelProperty(value = "字典value")
    private String itemValue;

    @NotBlank
    @ApiModelProperty(value = "名称")
    private String itemCh;

    @ApiModelProperty(value = "字典描述")
    private String description;

    @ApiModelProperty(value = "状态  1启用  0不启用")
    private Integer status;
}