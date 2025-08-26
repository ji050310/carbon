package com.carbon.system.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 碳文章 新增对象
 * </p>
 *
 * @author jzh
 * @since 2022-05-18
 */
@Data
@ApiModel(value = "CarbonArticleAddVo对象", description = "碳文章添加参数")
public class CarbonArticleAddVo implements Serializable {

    @ApiModelProperty(value = "跳转url")
    private String url;

    @ApiModelProperty(value = "跳转token")
    private String token;
}
