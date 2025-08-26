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
 * 系统字典
 * </p>
 *
 * @author Li Jun
 * @since 2022-05-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="SysDict对象", description="系统字典")
public class SysDict extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "字典编码")
    private String dictCode;

    @ApiModelProperty(value = "字典名称")
    private String dictName;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "字典类型0为string,1为number")
    private Integer type;

    @ApiModelProperty(value = "删除状态")
    private Integer delFlag;

}
