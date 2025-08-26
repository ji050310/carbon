package com.carbon.common.enums;


import static com.carbon.common.constants.ExpPrefix.*;

/**
 * 异常枚举
 * @author Li Jun
 * @since 2021-06-11
 */
public enum ExpCodeEnum {

    /**
     * 通用异常
     */
    DEFAULT_ERROR("0000", "默认异常"),
    UNKNOW_ERROR(COM_EXP_PREFIX + "000", "未知异常"),
    ERROR_404(COM_EXP_PREFIX + "001", "没有该接口"),
    PARAM_NULL(COM_EXP_PREFIX + "002", "参数为空"),
    NO_REPEAT(COM_EXP_PREFIX + "003", "请勿重复提交"),
    SESSION_NULL(COM_EXP_PREFIX + "004", "请求头中SessionId不存在"),
    HTTP_REQ_METHOD_ERROR(COM_EXP_PREFIX + "005", "HTTP请求方法不正确"),
    JSONERROR(COM_EXP_PREFIX + "006", "JSON解析异常"),
    VERIFICATION_CODE_ERROR(COM_EXP_PREFIX + "007", "验证码输入有误"),
    OPERATE_FAIL_ERROR(COM_EXP_PREFIX + "008", "操作失败"),


    /**
     * 公共模块异常
     */
    COMMON_DICT_SAVE_ERROR(DICT_EXP_PREFIX + "000", "字典保存失败!"),
    COMMON_DICT_EDIT_ERROR(DICT_EXP_PREFIX + "001", "字典保存失败!"),
    COMMON_DICT_DELETE_ERROR(DICT_EXP_PREFIX + "002", "字典删除失败!"),


    /**
     * 系统权限模块异
     */
    SYSTEM_SECURITY_TOKEN_ERROR(ACCOUNT_EXP_PREFIX + "002", "token已无效，请从新登录"),


    /**
     * 账户模块
     */
    SYSTEM_SECURITY_USER_NULL(ACCOUNT_EXP_PREFIX + "000", "请输入正确的用户名或密码！"),
    SYSTEM_SECURITY_USER_PASSWORD_ERROR(ACCOUNT_EXP_PREFIX + "001", "请输入正确的用户名或密码！"),
    SYS_ACCOUNT_NAME_ALREADY_EXISTS(ACCOUNT_EXP_PREFIX + "002","用户名已存在"),
    SYS_ACCOUNT_NOT_EXISTS(ACCOUNT_EXP_PREFIX + "003","账户不存在"),
    SYS_ACCOUNT_PHONE_ALREADY_EXISTS(ACCOUNT_EXP_PREFIX + "004","手机号已存在"),
    SYS_ACCOUNT_DISABLE(ACCOUNT_EXP_PREFIX + "005", "账号已被禁用，请联系管理员！"),
    SYS_ACCOUNT_NO_OPENED(ACCOUNT_EXP_PREFIX + "006", "未开户"),
    SYS_ACCOUNT_OTHER_LOGIN(ACCOUNT_EXP_PREFIX + "007", "异地登录！"),
    SYS_ACCOUNT_REGISTER_CONFIRM_PASSWORD_ERROR(ACCOUNT_EXP_PREFIX + "008", "两次输入密码不一致"),



    /**
     * 业务异常
     */
    SYS_Tenant_OrgName_UNIQUE(Tenant_EXP_PREFIX+"001","租户编码已经存在"),

    ;

    private String code;
    private String message;

    private ExpCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    ExpCodeEnum() {
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
