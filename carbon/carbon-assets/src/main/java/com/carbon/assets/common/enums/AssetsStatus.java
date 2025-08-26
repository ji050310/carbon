package com.carbon.assets.common.enums;

/**
 * 资产状态
 * @author : Li Jun
 * @since : 2021-08-27 15:00
 **/
public enum AssetsStatus {

    WAIT("0130000004","待审批"),
    HOLD("0130000001","已签发"),
    LOCKED("0130000002","已锁定"),
    FROZEN("0130000003","已冻结"),
    ;

    private final String status;
    private final String name;


    AssetsStatus(String status, String name) {
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
