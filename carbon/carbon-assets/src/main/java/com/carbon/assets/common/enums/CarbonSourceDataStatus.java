package com.carbon.assets.common.enums;

/**
 * @author : Li Jun
 * @since : 2021-08-27 15:00
 **/
public enum CarbonSourceDataStatus {
    /**有效资产*/
    VALID(1,"未报送"),

    /**无效资产*/
    INVALID(2,"已报送")

    ;

    private final int status;
    private final String name;


    CarbonSourceDataStatus(int status, String name) {
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
