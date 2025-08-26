package com.carbon.assets.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.carbon.domain.common.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 碳源数据
 * </p>
 *
 * @author Li Jun
 * @since 2021-08-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "CarbonSourceData对象", description = "碳源数据")
public class CarbonSourceData extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "碳数据上链记录Id")
    private String txId;

    @ApiModelProperty(value = "状态,字典：44")
    private Integer status;

    @ApiModelProperty(value = "类型")
    private Integer type;

    @ApiModelProperty(value = "资产单位")
    private Integer uomType;

    @ApiModelProperty(value = "资产数量")
    private BigDecimal amount;

    @ApiModelProperty(value = "资产来源渠道ID")
    private Integer sourceChannel;

    @ApiModelProperty(value = "碳减排项目ID")
    private String carbonProjectId;

    @ApiModelProperty(value = "起始日期")
    private Date startDate;

    @ApiModelProperty(value = "截止日期")
    private Date endDate;

    @ApiModelProperty(value = "报送时间")
    private Date submittedTime;

    @ApiModelProperty(value = "审核时间")
    private Date auditTime;

    @ApiModelProperty(value = "所属租户",hidden = true)
    private Long tenantId;

}
