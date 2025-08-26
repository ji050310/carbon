package com.carbon.common.exception;

import cn.hutool.core.util.StrUtil;
import com.carbon.common.enums.ExpCodeEnum;
import com.carbon.domain.common.ApiCode;
import com.carbon.domain.common.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Li Jun
 * @since 2021-06-11
 */
@ControllerAdvice
@RestController
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 非法参数验证异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.OK)
    public ApiResult<List<String>> handleMethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        StringBuilder builder = StrUtil.builder();
        for (FieldError fieldError : fieldErrors) {
            builder.append(StrUtil.COMMA).append(fieldError.getDefaultMessage());
        }
        String errorMsg = builder.toString().replaceFirst(StrUtil.COMMA,StrUtil.EMPTY);
        log.error("fieldErrors" + errorMsg);
        return ApiResult.fail(ApiCode.PARAMETER_EXCEPTION, errorMsg);
    }


    /**
     * HTTP解析请求参数异常
     */
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.OK)
    public ApiResult<Object> httpMessageNotReadableException(HttpMessageNotReadableException exception) {
        log.error("httpMessageNotReadableException:", exception);
        return ApiResult.fail(ApiCode.PARAMETER_EXCEPTION);
    }

    /**
     * HTTP
     */
    @ExceptionHandler(value = HttpMediaTypeException.class)
    @ResponseStatus(HttpStatus.OK)
    public ApiResult<Object> httpMediaTypeException(HttpMediaTypeException exception) {
        log.error("httpMediaTypeException:", exception);
        return ApiResult.fail(ApiCode.PARAMETER_EXCEPTION);
    }



    /**
     * 业务异常处理
     */
    @ExceptionHandler(value = CommonBizException.class)
    @ResponseStatus(HttpStatus.OK)
    public ApiResult<Object> businessExceptionHandler(CommonBizException exception) {
        ExpCodeEnum codeEnum = exception.getCodeEnum();
        return ApiResult.result(Integer.parseInt(codeEnum.getCode()),exception.getMessage(),null);
    }


    /**
     * 默认的异常处理
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public ApiResult<Object> exceptionHandler(Exception exception) {
        log.error("exception:", exception);
        return ApiResult.fail(ApiCode.SYSTEM_EXCEPTION);
    }

//    /**
//     * 分布式锁异常处理
//     */
//    @ExceptionHandler(value = KlockTimeoutException.class)
//    @ResponseStatus(HttpStatus.OK)
//    public ApiResult<Object> lockExceptionHandler(KlockTimeoutException exception) {
//        log.error("分布式锁异常:", exception);
//        return ApiResult.fail(ApiCode.LOCK_EXCEPTION);
//    }

}
