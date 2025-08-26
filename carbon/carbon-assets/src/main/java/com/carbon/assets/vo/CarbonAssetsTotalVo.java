package com.carbon.assets.vo;

import com.carbon.domain.common.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 碳资产 总计
 * </p>
 *
 * @author Li Jun
 * @since 2022-04-24
 */
@Data
@ApiModel(value="CarbonAssetsTotalVo", description="碳资产 总计")
public class CarbonAssetsTotalVo implements Serializable{
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "持仓总额")
    private BigDecimal total;

    @ApiModelProperty(value = "可用数量")
    private BigDecimal availableAmount;

    @ApiModelProperty(value = "冻结数量")
    private BigDecimal frozenAmount;

    @ApiModelProperty(value = "锁定数量")
    private BigDecimal lockedAmount;

}
