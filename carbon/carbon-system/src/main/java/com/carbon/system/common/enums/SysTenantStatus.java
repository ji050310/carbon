package com.carbon.system.common.enums;

/**
 * @author : Li Jun
 * @since : 2021-08-27 15:00
 * 系统租户状态
 **/
public enum SysTenantStatus {
    /**有效*/
    VALID(0,"无效"),

    /**无效*/
    INVALID(1,"有效")

    ;

    private final int status;
    private final String name;


    SysTenantStatus(int status, String name) {
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
