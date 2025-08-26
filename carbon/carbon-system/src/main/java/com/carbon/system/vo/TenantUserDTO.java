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
@ApiModel(value = "TenantUserDTO对象", description = "租户-用户 ")
public class TenantUserDTO {

    @ApiModelProperty(value = "碳用户外部ID")
    private String carbonUserId;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "低碳等级")
    private String carbonLevel;

    @ApiModelProperty(value = "来源渠道")
    private String sourceChannel;

    @ApiModelProperty(value = "碳减排量")
    private Integer carbonReduction;

    @ApiModelProperty(value = "碳信分")
    private int carbonCredit;

    @ApiModelProperty(value = "返现余额")
    private BigDecimal balance;

}
