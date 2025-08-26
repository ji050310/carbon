package com.carbon.assets.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import com.carbon.domain.common.QueryParam;

import java.util.Date;

/**
 * <p>
 * 碳配额资产 查询参数对象
 * </p>
 *
 * @author Li Jun
 * @since 2022-04-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="CarbonQuotaAssetsQueryParam对象", description="碳配额资产查询参数")
public class CarbonQuotaAssetsQueryParam extends QueryParam {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(name = "持有机构名称")
    private String agencyName;

    @ApiModelProperty(value = "有效期-开始")
    private Date expiryDateStart;

    @ApiModelProperty(value = "有效期-结束")
    private Date expiryDateEnd;

    @ApiModelProperty(value = "资产状态")
    private String assetsStatus;

    @ApiModelProperty(value = "交易状态")
    private String transactionStatus;
}
