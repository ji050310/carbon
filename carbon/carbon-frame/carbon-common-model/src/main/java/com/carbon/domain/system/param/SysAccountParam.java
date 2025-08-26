package com.carbon.domain.system.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author : Li Jun
 * @since : 2021-06-29 17:57
 **/
@Data
@ApiModel(value="AddAccountParam", description="添加账户参数")
public class SysAccountParam implements Serializable {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "账户id")
    private Long id;

    @NotBlank(message = "账户名不能为空")
    @ApiModelProperty(value = "账户名",required = true)
    private String accountName;

    @ApiModelProperty(value = "用户姓名")
    private String username;

    @NotBlank(message = "手机号不能为空")
    @ApiModelProperty(value = "手机号",required = true)
    private String phone;

    @NotBlank(message = "密码不能为空")
    @ApiModelProperty(value = "密码",required = true)
    private String password;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @NotBlank(message = "账户状态不能为空")
    @ApiModelProperty(value = "账户状态 字典039")
    private String accountStatus;

    @ApiModelProperty(value = "产品版本")
    private String productVersion;

    @ApiModelProperty(value = "账户类型")
    private String accountType;

    @ApiModelProperty(value = "所属公司id")
    private Long tenantId;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "开户时间")
    private Date createdTime;

    @ApiModelProperty(value = "账户有效期")
    private Date validityPeriod;

    @NotEmpty(message = "角色列表不能为空")
    @ApiModelProperty(value = "角色列表",required = true)
    private List<Long> roleIds;
}
