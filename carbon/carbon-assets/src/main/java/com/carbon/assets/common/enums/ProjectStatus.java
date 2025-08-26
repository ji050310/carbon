package com.carbon.assets.common.enums;

/**
 * @author : Li Jun
 * @since : 2021-08-27 15:00
 * 减排项目状态
 **/
public enum ProjectStatus {

    STATUS_1("0100000001","已创建"),
    STATUS_2("0100000002","已立项"),
    STATUS_3("0100000003","开发中"),
    STATUS_4("0100000004","已开发"),
    STATUS_5("0100000005","审定中"),
    STATUS_6("0100000006","已审定"),
    STATUS_7("0100000007","备案中"),
    STATUS_8("0100000008","已备案"),
    STATUS_9("0100000009","监测中"),
    STATUS_10("0100000010","核证中"),
    STATUS_11("0100000011","已核证"),
    STATUS_12("0100000012","签发中"),
    STATUS_13("0100000013","已签发")
    ;

    private final String status;
    private final String name;


    ProjectStatus(String status, String name) {
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
