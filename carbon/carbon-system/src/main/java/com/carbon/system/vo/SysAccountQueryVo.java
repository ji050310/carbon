package com.carbon.system.vo;

import com.carbon.domain.common.annotation.Dict;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 帐号  查询结果对象
 * </p>
 *
 * @author Li Jun
 * @since 2021-06-18
 */
@Data
@ApiModel(value = "SysAccountQueryVo对象", description = "帐号 查询参数")
public class SysAccountQueryVo implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "账户ID")
    private Long id;

    @ApiModelProperty(value = "账户名")
    private String accountName;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @Dict(dictCode = "038")
    @ApiModelProperty(value = "账户类型 字典038")
    private String accountType;

    @ApiModelProperty(value = "头像url")
    private String avatar;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @Dict(dictCode = "040")
    @ApiModelProperty(value = "产品版本 字典040")
    private String productVersion;

    @Dict(dictCode = "039")
    @ApiModelProperty(value = "账户状态 字典039")
    private String accountStatus;

    @ApiModelProperty(value = "所属租户")
    private Long tenantId;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    private Date createdTime;

    @ApiModelProperty(value = "更新时间")
    private Date updatedTime;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "账户有效日期")
    private Date validityPeriod;

    @ApiModelProperty(value = "账户角色")
    private SysAccountRoleVo accountRole;

}
