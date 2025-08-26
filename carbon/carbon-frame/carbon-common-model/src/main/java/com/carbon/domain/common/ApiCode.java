package com.carbon.domain.common;

/**
 * <p>
 * REST API 响应码
 * </p>
 *
 * @author Li Jun
 * @since 2021-06-11
 */
public enum ApiCode {

    SUCCESS(200, "操作成功"),

    UNAUTHORIZED(401, "请登录"),

    NOT_PERMISSION(403, "没有权限"),

    NOT_FOUND(404, "您请求的资源不存在"),

    FAIL(500, "操作失败"),

    DIALOG_FAIL(510, "操作失败"),


    LOGIN_EXCEPTION(4000, "登陆失败"),


    SYSTEM_EXCEPTION(5000, "系统异常!"),

    PARAMETER_EXCEPTION(5001, "请求参数校验异常"),

    PARAMETER_PARSE_EXCEPTION(5002, "请求参数解析异常"),

    HTTP_MEDIA_TYPE_EXCEPTION(5003, "HTTP Media 类型异常"),

    SPRING_BOOT_PLUS_EXCEPTION(5100, "系统处理异常"),

    BUSINESS_EXCEPTION(5101, "业务处理异常"),

    DAO_EXCEPTION(5102, "数据库处理异常"),

    VERIFICATION_CODE_EXCEPTION(5103, "验证码校验异常"),

    AUTHENTICATION_EXCEPTION(5104, "登陆授权异常"),

    UNAUTHENTICATED_EXCEPTION(5105, "没有访问权限"),

    UNAUTHORIZED_EXCEPTION(5106, "没有访问权限"),

    OTHER_PLACE_EXCEPTION(5107, "账号在其他处登录，请重新登录，妥善保管账号!"),

    LOCK_EXCEPTION(5108, "您已处理成功,请勿重复操作!"),

    CUSTOMER_EXISTS_EXCEPTION(5200, "乐乐齐用户已存在"),
    ;

    private final int code;
    private final String msg;

    ApiCode(final int code, final String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static ApiCode getApiCode(int code) {
        ApiCode[] ecs = ApiCode.values();
        for (ApiCode ec : ecs) {
            if (ec.getCode() == code) {
                return ec;
            }
        }
        return SUCCESS;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
