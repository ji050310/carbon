package com.carbon.system.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>
 * 项目飞书文档编号
 * </p>
 *
 * @author jzh
 * @since 2022-05-18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "项目飞书文档编号", description = "项目飞书文档编号")
public class ProjectFileCode implements Serializable {

    @ApiModelProperty(value = "项目对应飞书文档编号")
    private Long code;

}
