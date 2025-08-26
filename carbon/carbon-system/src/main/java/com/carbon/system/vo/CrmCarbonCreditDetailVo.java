package com.carbon.system.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;

import java.util.Date;

/**
 * <p>
 * 碳信分详情 查询结果对象
 * </p>
 *
 * @author Zhang Jinrui
 * @since 2022-05-02
 */
@Data
@ApiModel(value="CrmCarbonCreditDetailQueryVo对象", description="碳信分详情查询参数")
public class CrmCarbonCreditDetailVo implements Serializable{
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户ID")
    private String userId;

    @ApiModelProperty(value = "减排方式")
    private String reduceMethod;

    @ApiModelProperty(value = "减排平台")
    private String reductionPlatform;

    @ApiModelProperty(value = "频次（周）")
    private Integer frequency;

    @ApiModelProperty(value = "按周结算")
    private Date clearTime;

    @ApiModelProperty(value = "预计碳减排量")
    private Integer carbonReduction;

    @ApiModelProperty(value = "碳信分")
    private Integer carbonCredit;

}
