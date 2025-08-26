package com.carbon.system.common.enums;

/**
 * @author : Li Jun
 * @since : 2021-08-27 15:00
 **/
public enum TenantUserStatus {
    /**用户状态*/
    VALID(0,"无效"),

    /**用户状态*/
    INVALID(1,"有效")

    ;

    private final int status;
    private final String name;


    TenantUserStatus(int status, String name) {
        this.status = status;
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }
}
