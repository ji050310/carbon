package com.carbon.assets.param;

import com.carbon.domain.common.QueryParam;
import com.carbon.domain.common.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * <p>
 * 碳减排项目 查询参数对象
 * </p>
 *
 * @author Li Jun
 * @since 2021-07-31
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="CarbonProjectQueryParam对象", description="碳减排项目查询参数")
public class CarbonProjectQueryParam extends QueryParam {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "项目名称")
    private String name;

    @ApiModelProperty(value = "项目状态")
    private String projectStatus;

    @ApiModelProperty(value = "申请日期开始时间")
    private Date startDate;

    @ApiModelProperty(value = "申请日期结束时间")
    private Date endDate;

    @ApiModelProperty(value = "立项日期-开始")
    private Date initiationDateStart;

    @ApiModelProperty(value = "立项日期-结束")
    private Date initiationDateEnd;

    @ApiModelProperty(value = "方法学名称/编号")
    private String methodologyName;

    @ApiModelProperty(value = "核证标准")
    private String certificationCriteria;

    @ApiModelProperty(value = "方法学领域类型编码（字典：003）")
    private String fieldCode;

    @ApiModelProperty(value = "方法学子领域类型编码")
    private String fieldChildCode;

    @ApiModelProperty(value = "行业编码（字典：002）")
    private String industryCode;

    @ApiModelProperty(value = "项目领域编码（字典：003）")
    private String projectScopeCode;
}
