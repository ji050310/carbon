package com.carbon.common.enums;

/**
 * 飞书事件审批定义枚举
 * @author Li Jun
 * @since 2021-06-11
 */
public enum ApprovalCodeEnum {

    ASSETS_APPROVAL("4D98C03D-03DE-4A58-87D3-98720938655B", "资产上传审批"),  //在飞书网站中创建审批后 可以将对应的审批code替换为左侧声明的code
    TRADE_ACCOUNT_APPROVAL("85844BAC-A936-4738-BD56-A482EF8EB59A", "添加交易账户审批"),
    PROJECT_INITIATION_APPROVAL("763A4410-5D72-4496-9299-9B72E0F30F3B", "项目立项审批"),
    QUOTA_APPROVAL("14CC5B9C-2169-4E44-869F-195A42814F54","碳配额审批");
    ;

    private String code;
    private String title;

    private ApprovalCodeEnum(String code, String title) {
        this.code = code;
        this.title = title;
    }

    ApprovalCodeEnum() {
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return title;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.title = message;
    }
}
