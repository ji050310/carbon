package com.carbon.domain.system.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 开通会员套餐表 查询结果对象
 * </p>
 *
 * @author Cbd
 * @since 2022-09-14
 */
@Data
@ApiModel(value="UmsMemberSetmealQueryVo对象", description="开通会员套餐表查询参数")
public class UmsMemberSetmealQueryVo implements Serializable{
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "套餐id")
    private Long id;

    @ApiModelProperty(value = "套餐字典编码")
    private String setmealCode;

    @ApiModelProperty(value = "套餐名称")
    private String setmealName;

    @ApiModelProperty(value = "会员级别")
    private Integer memberLevel;

    @ApiModelProperty(value = "套餐价格")
    private Double setmealPrice;

    @ApiModelProperty(value = "开通月数")
    private Integer openMonth;

    @ApiModelProperty(value = "备注")
    private String remarks;

}
