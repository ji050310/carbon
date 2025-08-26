package com.carbon.assets.param;

import com.carbon.domain.common.QueryParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * <p>
 * 碳源数据 查询参数对象
 * </p>
 *
 * @author Li Jun
 * @since 2021-08-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="CarbonSourceDataQueryParam对象", description="碳源数据查询参数")
public class CarbonSourceDataQueryParam extends QueryParam {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "碳数据id")
    private Long id;

    @ApiModelProperty(value = "状态:0.无效 1.有效")
    private Integer status;

    @ApiModelProperty(value = "类型,字典ID:10")
    private Integer type;

    @ApiModelProperty(value = "碳资产单位,字典ID:25")
    private Integer uomType;

    @ApiModelProperty(value = "碳资产来源渠道,字典ID：11")
    private Integer sourceChannel;

    @ApiModelProperty(value = "起始日期")
    private Date startDate;

    @ApiModelProperty(value = "截止日期")
    private Date endDate;

    @ApiModelProperty(value = "报送时间-开始")
    private Date submittedTimeStart;
    @ApiModelProperty(value = "报送时间-结束")
    private Date submittedTimeEnd;

    @ApiModelProperty(value = "审核时间-开始")
    private Date auditTimeStart;
    @ApiModelProperty(value = "审核时间-结束")
    private Date auditTimeEnd;

    @ApiModelProperty(value = "碳减排项目ID")
    private String carbonProjectId;

}
