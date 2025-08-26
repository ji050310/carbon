package com.carbon.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.carbon.domain.common.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * <p>
 * 帐号
 * </p>
 *
 * @author Li Jun
 * @since 2021-06-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "SysAccount对象", description = "帐号 ")
public class SysAccount extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "账户名")
    private String accountName;

    @ApiModelProperty(value = "用户姓名")
    private String username;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "账户状态 1 启用 0 禁用")
    private String accountStatus;

    @ApiModelProperty(value = "产品版本（字典：040）")
    private String productVersion;

    @ApiModelProperty(value = "账户类型（字典：038）")
    private String accountType;

    @ApiModelProperty(value = "所属租户id")
    private Long tenantId;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "用户头像url")
    private String avatar;

    @ApiModelProperty(value = "开户时间")
    private Date createdTime;

    @ApiModelProperty(value = "账户有效日期")
    private Date validityPeriod;
}
