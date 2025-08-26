package com.carbon.system.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * <p>
 * 碳文章 状态参数对象
 * </p>
 *
 * @author Jiang zhenhua
 * @since 2022-04-23
 */
@Data
@ApiModel(value="CarbonArticleStatuParam对象", description="碳文章状态参数")
public class CarbonArticleStatuParam implements Serializable {
    @NotNull(message = "文章id不能为空")
    @ApiModelProperty(value = "文章id")
    private Long id;

    @NotNull(message = "文章状态不能为空")
    @ApiModelProperty(value = "文章状态")
    private String status;
}
