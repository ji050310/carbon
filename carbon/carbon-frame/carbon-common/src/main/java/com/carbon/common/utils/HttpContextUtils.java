package com.carbon.common.utils;

import com.alibaba.fastjson.JSON;
import com.carbon.common.constants.CommonConstant;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * HttpContextUtils
 *
 * @author Li Jun
 * @since 2021-06-11
 **/
public class HttpContextUtils {

    private static final String UTF8 = "UTF-8";
    private static final String CONTENT_TYPE = "application/json";


    public static HttpServletRequest getHttpServletRequest() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null){
            return null;
        }
        return attributes.getRequest();
    }

    public static String getDomain(){
        HttpServletRequest request = getHttpServletRequest();
        if (request == null){
            return null;
        }
        StringBuffer url = request.getRequestURL();
        return url.delete(url.length() - request.getRequestURI().length(), url.length()).toString();
    }

    public static String getOrigin(){
        HttpServletRequest request = getHttpServletRequest();
        if (request == null){
            return null;
        }
        return request.getHeader("Origin");
    }

    /**
     * 返回请求
     */
    public static void printJSON(HttpServletResponse response, Object object) {
        response.setCharacterEncoding(UTF8);
        response.setContentType(CONTENT_TYPE);
        try (PrintWriter printWriter = response.getWriter()) {
            printWriter.write(JSON.toJSONString(object));
            printWriter.flush();
        } catch (IOException ignored) {
        }
    }

    /**
     * 获取当前请求的 token
     */
    public static String getRequestToken(){
        //从header中获取token
        HttpServletRequest request = getHttpServletRequest();
        if (request == null){
            return null;
        }
        return getHttpServletRequest().getHeader(CommonConstant.TOKEN);
    }

    /**
     * 获取当前请求的 账户id
     */
    public static Long getAccountId(){
        return JwtUtil.getTokenInfo(getRequestToken(),JwtUtil.JWT_ACCOUNT_ID);
    }


    /**
     * 获取当前请求的 租户id
     */
    public static Long getTenantId(){
        return JwtUtil.getTokenInfo(getRequestToken(),JwtUtil.JWT_TENANT_ID);
    }

}
