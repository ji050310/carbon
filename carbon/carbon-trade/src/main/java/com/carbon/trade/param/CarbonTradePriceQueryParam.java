package com.carbon.trade.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import com.carbon.domain.common.QueryParam;

import java.util.Date;

/**
 * <p>
 * 碳交易询报价 查询参数对象
 * </p>
 *
 * @author lin rizhao
 * @since 2022-05-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="CarbonTradePriceQueryParam对象", description="碳交易询报价查询参数")
public class CarbonTradePriceQueryParam extends QueryParam {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "交易角色（字典：027）")
    private String tradeRole;

    @ApiModelProperty(value = "买方机构名称")
    private String buyerName;

    @ApiModelProperty(value = "卖方机构名称")
    private String sellerName;

    @ApiModelProperty(value = "项目领域字典码编码")
    private String projectScopeCode;

    @ApiModelProperty(value = "项目领域")
    private String projectScope;

    @ApiModelProperty(value = "项目类型（字典：004）")
    private String projectType;

    @ApiModelProperty(value = "资产类型（字典：014）")
    private String assetType;

    @ApiModelProperty(value = "询报价截止日期-开始")
    private Date expirationDateStart;

    @ApiModelProperty(value = "询报价截止日期-结束")
    private Date expirationDateEnd;

    @ApiModelProperty(value = "租户ID",hidden = true)
    private Long tenantId;
}
