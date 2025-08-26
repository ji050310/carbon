package com.carbon.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.carbon.system.vo.StatHomeDataVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 数据面板
 * </p>
 *
 * @author Li Jun
 * @since 2021-07-20
 */
@Data
@TableName(autoResultMap = true)
@ApiModel(value="DataPanel对象", description="数据面板")
public class DataPanel implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "首页数据")
    @TableField(typeHandler = JacksonTypeHandler.class)
    private StatHomeDataVo homeData;

    @ApiModelProperty(value = "所属租户")
    private Long tenantId;
}
