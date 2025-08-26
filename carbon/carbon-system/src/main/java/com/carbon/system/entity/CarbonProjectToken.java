package com.carbon.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.carbon.domain.common.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author Bae
 * @since 2022-06-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="项目文档", description="项目文档")
public class CarbonProjectToken extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "项目飞书文档编号")
    private Long textCode;

    @ApiModelProperty(value = "项目id")
    private String projectId;

    @ApiModelProperty(value = "项目飞书文档")
    private String fileToken;

}
