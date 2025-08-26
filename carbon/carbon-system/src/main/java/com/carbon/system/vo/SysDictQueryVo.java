package com.carbon.system.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;

import java.util.Date;

/**
 * <p>
 * 系统字典 查询结果对象
 * </p>
 *
 * @author Li Jun
 * @since 2022-05-01
 */
@Data
@ApiModel(value="SysDictQueryVo对象", description="系统字典查询参数")
public class SysDictQueryVo implements Serializable{
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    private Long id;

    @ApiModelProperty(value = "字典编码")
    private String dictCode;

    @ApiModelProperty(value = "字典名称")
    private String dictName;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "字典类型0为string,1为number")
    private Integer type;

    @ApiModelProperty(value = "创建时间")
    private Date createdTime;

    @ApiModelProperty(value = "更新时间")
    private Date updatedTime;

    @ApiModelProperty(value = "删除状态")
    private Integer delFlag;

}
