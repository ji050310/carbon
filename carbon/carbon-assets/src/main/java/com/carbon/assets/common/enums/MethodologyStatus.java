package com.carbon.assets.common.enums;

/**
 * @author : Li Jun
 * @since : 2021-08-27 15:00
 * 方法学状态
 **/
public enum MethodologyStatus {
    /**有效*/
    VALID(1,"启用"),

    /**无效*/
    INVALID(2,"禁用")

    ;

    private final int status;
    private final String name;


    MethodologyStatus(int status, String name) {
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
