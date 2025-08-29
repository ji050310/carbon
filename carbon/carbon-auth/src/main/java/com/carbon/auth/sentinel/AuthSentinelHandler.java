package com.carbon.auth.sentinel;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.carbon.domain.common.ApiResult;
import com.carbon.domain.auth.param.LoginParam;
import com.carbon.domain.auth.vo.LoginInfoVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;

public class AuthSentinelHandler {
    private static final Logger log = LoggerFactory.getLogger(AuthSentinelHandler.class);

    // Precise blockHandler for login(LoginParam, HttpServletRequest)
    public static ApiResult<LoginInfoVo> handleAuthLogin(LoginParam loginParam, HttpServletRequest request, BlockException ex) {
        log.warn("Auth block handler for login: {}", ex == null ? "null" : ex.toString());
        return ApiResult.fail("服务限流，请稍后重试");
    }

    // Precise blockHandler for sendForgotPasswordCode(String phone)
    public static ApiResult<Boolean> handleForgotPasswordCode(String phone, BlockException ex) {
        log.warn("Auth block handler for forgotPasswordCode: {}", ex == null ? "null" : ex.toString());
        return ApiResult.fail("服务限流，请稍后重试");
    }
}
