package com.carbon.domain.common;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * REST API 返回结果
 * </p>
 *
 * @author Li Jun
 * @since 2021-06-11
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
public class ApiResult<T> implements Serializable {

    private int code;

    private T data;

    private String msg;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date time;

    public static <T> ApiResult<T> result(int code, String msg, T data) {
        return new ApiResult<T>(code,data,msg, DateUtil.date());
    }

    public static <T> ApiResult<T> result(ApiCode apiCode, String msg, T data) {
        String message = apiCode.getMsg();
        if (StrUtil.isNotBlank(msg)) {
            message = msg;
        }
        return new ApiResult<T>(apiCode.getCode(),data,message, DateUtil.date());
    }

    public static <T> ApiResult<T> result(ApiCode apiCode, T data) {
        return result(apiCode, null, data);
    }

    public static <T> ApiResult<T> ok(T data) {
        return result(ApiCode.SUCCESS, data);
    }

    public static <T> ApiResult<T> ok() {
        return ok(null);
    }

    public static <T> ApiResult<T> ok(String msg) {
        return result(ApiCode.SUCCESS, msg, null);
    }

    public static <T> ApiResult<T> fail(ApiCode apiCode) {
        return result(apiCode, null);
    }
    public static <T> ApiResult<T> fail(ApiCode apiCode,String msg) {
        return result(apiCode,msg, null);
    }

    public static <T> ApiResult<T> fail(String msg) {
        return result(ApiCode.FAIL, msg, null);
    }

    public static <T> ApiResult<T> result(boolean flag) {
        if (flag) {
            return ok();
        }
        return fail("");
    }

    public static <T> ApiResult<T> result(ApiCode apiCode) {
        return result(apiCode, null);
    }
}
