package com.carbon.system.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import com.carbon.domain.common.QueryParam;

import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 系统字典条目 查询参数对象
 * </p>
 *
 * @author Jiang zhenhua
 * @since 2022-05-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="SysDictItemQueryParam对象", description="系统字典条目查询参数")
public class SysDictItemQueryParam extends QueryParam {
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "字典编码不能为空")
    @ApiModelProperty(value = "字典编码")
    private String dictCode;

    @ApiModelProperty(value = "子字典编码value")
    private String itemValue;

    @ApiModelProperty(value = "字典中文名称")
    private String itemCh;

    @ApiModelProperty(value = "状态")
    private Integer status;
}
