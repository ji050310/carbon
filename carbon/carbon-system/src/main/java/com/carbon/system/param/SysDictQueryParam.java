package com.carbon.system.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import com.carbon.domain.common.QueryParam;

/**
 * <p>
 * 系统字典 查询参数对象
 * </p>
 *
 * @author Li Jun
 * @since 2022-05-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="SysDictQueryParam对象", description="系统字典查询参数")
public class SysDictQueryParam extends QueryParam {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "字典编码")
    private String dictCode;

    @ApiModelProperty(value = "字典名称")
    private String dictName;

}
