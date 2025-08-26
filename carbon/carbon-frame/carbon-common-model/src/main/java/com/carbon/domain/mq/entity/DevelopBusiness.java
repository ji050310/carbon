package com.carbon.domain.mq.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 开发业务跟进表单MQ实体类
 *
 * @author Bae
 * @version 1.0
 * @date 2022/6/5 22:11
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value="开发业务跟进表单MQ实体类", description="开发业务跟进表单MQ实体类")
public class DevelopBusiness {

    @ApiModelProperty(value = "客户姓名")
    private String customerName;

    @ApiModelProperty(value = "机构名称")
    private String agenciesName;

    @ApiModelProperty(value = "联系电话")
    private String contactNumber;

    @ApiModelProperty(value = "方法学名称")
    private String methodologyName;

    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "项目地点")
    private String projectLocation;

    @ApiModelProperty(value = "跟进状态")
    private String progressStatus;

    @ApiModelProperty(value = "赢单率")
    private String winRate;

    @ApiModelProperty(value = "跟进人")
    private String followPeople;

    @ApiModelProperty(value = "跟进备注")
    private String followRemark;

    @ApiModelProperty(value = "签约付费情况")
    private String payState;

    @ApiModelProperty(value = "开发状态")
    private String developState;

    @ApiModelProperty(value = "开发负责人")
    private String developPrincipal;

    @ApiModelProperty(value = "开发备注")
    private String developRemark;
    
}
