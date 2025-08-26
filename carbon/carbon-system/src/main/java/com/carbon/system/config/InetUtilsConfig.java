package com.carbon.system.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.commons.util.InetUtilsProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * InetUtilsProperties 配置
 * 解决多个 InetUtilsProperties Bean 冲突问题
 */
@Configuration
public class InetUtilsConfig {

    // 避免与 Spring Cloud UtilAutoConfiguration 中的同名 bean 冲突
    @Bean("carbonInetUtilsProperties")
    @Primary
    @ConfigurationProperties("spring.cloud.inetutils")
    public InetUtilsProperties carbonInetUtilsProperties() {
        return new InetUtilsProperties();
    }
}
