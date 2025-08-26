package com.carbon.assets.param;

import com.carbon.domain.common.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import com.carbon.domain.common.QueryParam;

import java.util.Date;

/**
 * <p>
 * 碳信用资产 查询参数对象
 * </p>
 *
 * @author Li Jun
 * @since 2022-04-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="CarbonCreditAssetsQueryParam对象", description="碳信用资产查询参数")
public class CarbonCreditAssetsQueryParam extends QueryParam {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(name = "项目名")
    private String projectName;

    @ApiModelProperty(name = "项目状态")
    private String projectStatus;

    @ApiModelProperty(name = "方法学名称")
    private String methodName;

    @ApiModelProperty(value = "签发日期-开始")
    private Date issuingDateStart;

    @ApiModelProperty(value = "签发日期-结束")
    private Date issuingDateEnd;

    @ApiModelProperty(value = "资产状态")
    private String assetsStatus;

    @ApiModelProperty(value = "交易状态")
    private String transactionStatus;

    @ApiModelProperty(value = "核证标准（字典：007）")
    private String certificationCriteria;

    @ApiModelProperty(value = "（类型）行业编码（字典：002）")
    private String industryCode;

    @ApiModelProperty(value = "（领域编码（字典：003）")
    private String projectScopeCode;

}
