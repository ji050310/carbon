package com.carbon.system.entity;

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
 * 碳信分详情
 * </p>
 *
 * @author Zhang Jinrui
 * @since 2022-05-02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="CrmCarbonCreditDetail对象", description="碳信分详情")
public class CrmCarbonCreditDetail extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

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
