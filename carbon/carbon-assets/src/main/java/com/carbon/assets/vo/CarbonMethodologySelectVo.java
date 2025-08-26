package com.carbon.assets.vo;

import com.carbon.domain.common.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 碳减排方法学 下拉选择列表
 * </p>
 *
 * @author Li Jun
 * @since 2022-05-02
 */
@Data
@ApiModel(value="CarbonMethodologySelectVo", description="方法学下拉选择列表")
public class CarbonMethodologySelectVo implements Serializable{
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "方法学名称")
    private String name;
}
