package com.carbon.trade.common.enums;

/**
 * 交易角色
 * @author : Li Jun
 * @since : 2021-08-27 15:00
 **/
public enum TradeRoleEnum {

    BUYER("0270000001","买方"),
    SELLER("0270000002","卖方"),
    ;

    private final String status;
    private final String name;


    TradeRoleEnum(String status, String name) {
        this.status = status;
        this.name = name;
    }


    public String getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }
}
