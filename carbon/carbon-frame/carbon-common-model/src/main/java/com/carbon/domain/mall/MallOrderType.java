package com.carbon.domain.mall;

import lombok.Data;

public enum MallOrderType {
    MEMBER_ORDER("MEMBER_ORDER", "会员订单"),
    GOOD_ORDER("GOOD_ORDER","商品订单");
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String message;


    MallOrderType(String good_order, String msg) {
        this.code = good_order;
        this.message = msg;
    }
}
