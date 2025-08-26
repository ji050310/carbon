package com.carbon.assets.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.carbon.domain.common.BaseEntity;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * <p>
 * 项目元注册表内容
 * </p>
 *
 * @author Cbd
 * @since 2022-08-15
 */
@Data
@ApiModel(value="CarbonMetaregistryDocContent对象", description="项目元注册表内容")
public class CarbonMetaregistryDocContent{

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer docId;

    private String content;

    private String projectRefId;

    private String typeCode;

    private String title;

    private String url;
}
