package com.carbon.system.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 数据面板 碳项目统计
 * </p>
 *
 * @author Li Jun
 * @since 2021-07-20
 */
@Data
@ApiModel(value="StatCarbonProjectVo", description="碳项目统计")
public class StatCarbonProjectVo implements Serializable{
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "预计减排量")
    private BigDecimal reductionTotal;

    @ApiModelProperty(value = "累计审定项目")
    private Integer approvedCount ;

    @ApiModelProperty(value = "累计备案项目")
    private Integer filingCount;

    @ApiModelProperty(value = "累计签发项目")
    private Integer singCount;

    @ApiModelProperty(value = "项目列表")
    private List<StatCarbonProject> projectList;

}
