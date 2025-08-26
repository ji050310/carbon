package com.carbon.domain.auth.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.*;

/**
 * 认证安全数据
 *
 * @author Li Jun
 * @since 2021-06-11
 */
@Data
public class SecurityData implements Serializable {
    /**
     * 账户id
     */
    private Long accountId;
    /**
     * 账户名
     */
    private String accountName;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 租户id
     */
    private Long tenantId;

    /**
     * 租户名称
     */
    private String tenantName;

    /**
     * 租户联系方式
     */
    private String contactsPhone;

    /**
     * 角色列表
     */
    private List<String> roleCodes;
    @JsonIgnore
    private String roleCodeStr;

    /**
     * 权限编码
     */
    private List<String> permissionCodes;
    @JsonIgnore
    private String permissionCodesStr;

}
