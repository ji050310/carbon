package com.carbon.assets.common.enums;

/**
 * @author : Li Jun
 * @since : 2021-08-27 15:00
 * 交易账户状态
 **/
public enum ExchangeAccountStatus {
    /**未登录*/
    NOT_LOGGED_IN(1,"未登录"),

    /**已登录*/
    LOGGED_IN(2,"已登录")

    ;

    private final int status;
    private final String name;


    ExchangeAccountStatus(int status, String name) {
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
