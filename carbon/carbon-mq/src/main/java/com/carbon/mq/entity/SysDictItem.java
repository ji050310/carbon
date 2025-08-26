package com.carbon.mq.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.carbon.domain.common.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 系统字典条目
 * </p>
 *
 * @author Jiang zhenhua
 * @since 2022-05-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="SysDictItem对象", description="系统字典条目")
public class SysDictItem extends BaseEntity {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "字典项目id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "字典编码")
    private String dictCode;

    @ApiModelProperty(value = "字典value")
    private String itemValue;

    @ApiModelProperty(value = "字典中文")
    private String itemCh;

    @ApiModelProperty(value = "字典英文")
    private String itemEn;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "排序")
    private Integer sortOrder;

    @ApiModelProperty(value = "状态（1启用 0不启用）")
    private Integer status;

}
