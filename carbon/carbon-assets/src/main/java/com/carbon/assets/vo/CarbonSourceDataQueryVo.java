package com.carbon.assets.vo;

import com.carbon.domain.common.annotation.Dict;
import com.carbon.assets.common.enums.CarbonSourceDataStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 碳源数据 查询结果对象
 * </p>
 *
 * @author Li Jun
 * @since 2021-08-07
 */
@Data
@ApiModel(value = "CarbonSourceDataQueryVo对象", description = "碳源数据查询参数")
public class CarbonSourceDataQueryVo implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @Dict(enumType = CarbonSourceDataStatus.class)
    @ApiModelProperty(value = "状态")
    private Integer status;

    @ApiModelProperty(value = "资产数量")
    private BigDecimal amount;

    @Dict(dictCode = "assets_type")
    @ApiModelProperty(value = "资产类型")
    private Long type;

    @Dict(dictCode = "assets_unit")
    @ApiModelProperty(value = "资产单位类型")
    private Long uomType;

    @Dict(dictCode = "carbon_channel_type")
    @ApiModelProperty(value = "资产来源渠道")
    private Long sourceChannel;

    @ApiModelProperty(value = "起始日期")
    private Date startDate;

    @ApiModelProperty(value = "截止日期")
    private Date endDate;

    @ApiModelProperty(value = "报送时间")
    private Date submittedTime;

    @ApiModelProperty(value = "审核时间")
    private Date auditTime;

    @ApiModelProperty(value = "所属租户")
    private Long tenantId;

    @ApiModelProperty(value = "创建者ID")
    private Long creatorId;

    @ApiModelProperty(value = "创建时间")
    private Date createdTime;

    @ApiModelProperty(value = "碳减排项目")
    private String carbonProjectName;

    @Dict(dictCode = "009")
    @ApiModelProperty(value = "碳减排方法学字典")
    private String carbonMethodology;

}
