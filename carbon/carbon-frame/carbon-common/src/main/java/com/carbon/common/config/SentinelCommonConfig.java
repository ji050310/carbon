package com.carbon.common.config;

import com.alibaba.fastjson.JSON;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * Sentinel 通用配置类
 * 
 * @author Carbon Team
 */
@Configuration
public class SentinelCommonConfig {

    /**
     * 通用的降级处理方法
     */
    public static class CommonFallbackHandler {

        /**
         * 通用降级方法
         */
        public static String handleFallback(String methodName, Throwable ex) {
            Map<String, Object> result = new HashMap<>();
            result.put("code", 500);
            result.put("message", "服务暂时不可用，请稍后重试");
            result.put("data", null);
            result.put("method", methodName);
            result.put("timestamp", System.currentTimeMillis());
            
            if (ex != null) {
                result.put("error", ex.getMessage());
            }
            
            return JSON.toJSONString(result);
        }

        /**
         * 通用限流处理方法
         */
        public static String handleBlockException(String methodName, Throwable ex) {
            Map<String, Object> result = new HashMap<>();
            result.put("code", 429);
            result.put("message", "请求过于频繁，请稍后重试");
            result.put("data", null);
            result.put("method", methodName);
            result.put("timestamp", System.currentTimeMillis());
            
            return JSON.toJSONString(result);
        }
    }
}
