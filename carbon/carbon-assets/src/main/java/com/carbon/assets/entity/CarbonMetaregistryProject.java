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
 * 租户碳减排项目
 * </p>
 *
 * @author ChengJX
 * @since 2022-08-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="CarbonMetaregistryProject对象", description="租户碳减排项目")
public class CarbonMetaregistryProject extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "项目库id")
    private Integer metaregistryId;

    @ApiModelProperty(value = "项目介绍")
    private String projectIntroduction;

    @ApiModelProperty(value = "所属租户")
    private Long tenantId;

}
