package com.carbon.system.param;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * @author : Li Jun
 **/
@Data
@ApiModel(value="FeiShuApprovalCallbackParam", description="飞书审批回调参数")
public class FeiShuApprovalCallbackParam implements Serializable {
    private static final long serialVersionUID = 1L;

    private String action_type;
    private String action_context;
    private String user_id;
    private String approval_code;
    private String instance_id;
    private String task_id;
    private String reason;
    private String token;

    private Attachments attachments;

    @Data
    public static class Attachments implements Serializable {
        private static final long serialVersionUID = 1L;

        String file_type;
        String file_name;
        String file_size;
        String url;
    }
}
