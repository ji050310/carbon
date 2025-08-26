package com.carbon.trade.common.enums;

/**
 * 交易状态
 * @author : Li Jun
 * @since : 2021-08-27 15:00
 **/
public enum TradeStatusEnum {

    OFFER("0160000001","询报价"),
    INTENDED_TRADE("0160000002","意向成交"),
    IN_TRADE ("0160000003","交易履约"),
    TRADED("0160000004","已交易"),
    CANCEL_TRADE("0160000005","已取消易"),
    ;

    private final String status;
    private final String name;


    TradeStatusEnum(String status, String name) {
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
