package com.carbon.system.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author : Li Jun
 **/
@Data
@ApiModel(value="FeiShuEventParam", description="FeiShuEventParam")
public class FeiShuEventParam implements Serializable {
    private static final long serialVersionUID = 1L;

    private String schema;

    private Header header;

    @Data
    public static class Header implements Serializable {
        private static final long serialVersionUID = 1L;

        String event_id;
        String token;
        String create_time;
        String event_type;
        String tenant_key;
        String app_id;
    }
}
