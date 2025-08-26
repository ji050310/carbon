package com.carbon.common.exception;

import com.carbon.common.enums.ExpCodeEnum;

import java.io.Serializable;

/**
 * 通用业务异常（由异常状态码区分不同的业务异常）
 * @author Li Jun
 * @since 2021-06-11
 */
public class CommonBizException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 1719172041047970870L;
    private ExpCodeEnum codeEnum;

    public CommonBizException(ExpCodeEnum codeEnum){
        super(codeEnum.getMessage());
        this.codeEnum = codeEnum;
    }


    public CommonBizException(String message){
        super(message);
        this.codeEnum = ExpCodeEnum.DEFAULT_ERROR;
    }

    public ExpCodeEnum getCodeEnum() {
        return codeEnum;
    }

    public CommonBizException(ExpCodeEnum codeEnum, String message){
        super(message);
        this.codeEnum = codeEnum;
    }

}
