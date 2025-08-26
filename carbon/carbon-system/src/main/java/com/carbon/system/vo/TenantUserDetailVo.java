package com.carbon.system.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Bae
 * @version 1.0
 * @date 2022/5/3 18:17
 */
@Data
@ApiModel(value = "TenantUserDetail对象", description = "租户-用户 ")
public class TenantUserDetailVo {


    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "姓名")
    private String userName;

    @ApiModelProperty(value = "碳信分")
    private Integer carbonCredit;

    @ApiModelProperty(value = "返现余额")
    private BigDecimal balance;

    @ApiModelProperty(value = "本月获得碳信分")
    private Integer monthCarbonCredit;

    @ApiModelProperty(value = "累计碳减排量")
    private Integer totalCarbonReduction;

    @ApiModelProperty(value = "本月碳减排量")
    private Integer monthCarbonReduction;

    @ApiModelProperty(value = "累计兑换积分")
    private Integer totalExchangeIntegral;

    @ApiModelProperty(value = "本月兑换积分")
    private Integer monthExchangeIntegral;


}
