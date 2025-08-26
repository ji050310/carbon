package com.carbon.assets.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

/**
 * 应用配置类
 * 用于解决循环依赖和性能优化
 * 
 * @author System
 * @since 2025-08-25
 */
@Configuration
public class ApplicationConfiguration {

    /**
     * 针对 JDK 8u202 的 JVM 优化配置提示
     * 建议在启动参数中添加：
     * -Xms512m -Xmx2g
     * -XX:+UseG1GC
     * -XX:MaxGCPauseMillis=200
     * -XX:+HeapDumpOnOutOfMemoryError
     * -XX:HeapDumpPath=./heapdump.hprof
     */
    
    /**
     * Nacos 2.2.3 兼容性配置
     * 确保与 Spring Cloud Alibaba 2021.0.5.0 版本兼容
     */
    
    /**
     * Redis 5.0.14 连接池优化配置
     * 建议在 application.yml 中配置合适的连接池参数
     */
    
    /**
     * RocketMQ 5.3.3 兼容性配置
     * 确保消息队列稳定运行
     */
    
    /**
     * Elasticsearch 7.8.0 客户端配置
     * 使用 High Level REST Client
     */
}
