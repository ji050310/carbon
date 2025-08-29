package com.carbon.common.config;

import feign.Feign;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

/**
 * Feign Sentinel 配置类
 * 
 * @author Carbon Team
 */
@Configuration
@ConditionalOnClass(Feign.class)
@ConditionalOnProperty(name = "feign.sentinel.enabled", havingValue = "true")
public class FeignSentinelConfig {

    // Feign Sentinel 自动配置
    // Spring Cloud Alibaba 会自动处理 Feign 的熔断配置
}
