package com.carbon.system.param;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * @author : Li Jun
 **/
@Data
@ApiModel(value="FeiShuEventParam", description="FeiShuEventParam")
public class FeiShuEventParam1 implements Serializable {
    private static final long serialVersionUID = 1L;

    private String ts;

    private String uuid;

    private String token;

    private String type;

    private Event event;

    private String challenge;

    @Data
    public static class Event implements Serializable {
        private static final long serialVersionUID = 1L;
        String app_id;
        String tenant_key;
        String type;
        String approval_code;
        String instance_code;
        String status;
        String operate_time;
        String uuid;

    }
}
