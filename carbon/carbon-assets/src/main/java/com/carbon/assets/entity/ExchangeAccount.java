package com.carbon.assets.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.carbon.domain.common.BaseEntity;
import com.carbon.domain.common.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 交易账户
 * </p>
 *
 * @author Li Jun
 * @since 2021-08-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ExchangeAccount对象", description = "交易账户 ")
public class ExchangeAccount extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @NotBlank(message = "账户名不能为空")
    @ApiModelProperty(value = "账户名")
    private String accountName;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "账户类型：0.管理员 1.普通账户")
    private Integer accountType;

    @ApiModelProperty(value = "账户凭证")
    private String accountCredentials;

    @ApiModelProperty(value = "交易经纪人ID")
    private Long brokerId;

    @NotNull(message = "选择一个交易所！")
    @ApiModelProperty(value = "碳交易所ID")
    private Long carbonExchangeId;

    @ApiModelProperty(value = "碳配额余额")
    private BigDecimal carbonAmount;

    @ApiModelProperty(value = "ccer余额额")
    private BigDecimal ccerAmount;

    @Dict(dictCode = "043")
    @ApiModelProperty(value = "账户状态(字典编码)")
    private String accountStatus;

    @ApiModelProperty(value = "最近登录时间")
    private Date loginTime;

    @ApiModelProperty(value = "绑定时间")
    private Date bindingTime;

    @ApiModelProperty(value = "所属租户")
    private Long tenantId;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "账户cookie")
    private String cookie;

}
