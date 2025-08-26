package com.carbon.assets.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.carbon.domain.common.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @author Code534
 * @since 2022-07-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="CarbonAssetAssessment对象", description="")
public class CarbonAssetAssessment extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "字典id")
    private String itemId;

    @ApiModelProperty(value = "项目类型")
    private String projectType;

    @ApiModelProperty(value = "项目领域")
    private String fieldType;

    @ApiModelProperty(value = "字典value")
    private String questionValue;

    @ApiModelProperty(value = "字典名称")
    private String questionName;

    private String answer;

}
