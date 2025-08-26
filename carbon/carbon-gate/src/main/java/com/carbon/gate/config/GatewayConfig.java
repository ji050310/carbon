package com.carbon.gate.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

/**
 * Spring Cloud Gateway 配置类
 */
@Configuration
public class GatewayConfig {

    /**
     * CORS 配置
     * 
     * 注意：此配置已被禁用，因为CORS配置已在application.yml中通过globalcors配置
     * 如果启用此配置会导致Access-Control-Allow-Origin头部重复添加
     */
    // @Bean
    public CorsWebFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedMethod("*");
        config.addAllowedOriginPattern("*"); // 使用 allowedOriginPattern 替代 allowedOrigin
        config.addAllowedHeader("*");
        config.setAllowCredentials(true);
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        
        return new CorsWebFilter(source);
    }
    
    /**
     * 自定义路由配置（补充 YAML 配置）
     */
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                // 可以在这里添加更复杂的路由规则
                .route("health", r -> r.path("/actuator/**")
                        .uri("forward:/actuator"))
                .build();
    }
}