package com.carbon.gate.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * 全局认证过滤器
 * 基于 Spring Cloud Gateway 的 GlobalFilter
 */
@Slf4j
@Component
public class AuthGlobalFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        
        String path = request.getURI().getPath();
        log.debug("请求路径: {}", path);
        
        // 放行某些路径，如健康检查、登录接口等
        if (isAllowedPath(path)) {
            return chain.filter(exchange);
        }
        
        // 检查认证头 - 支持多种token头部格式
        String token = getTokenFromRequest(request);
        if (token == null || token.trim().isEmpty()) {
            return unauthorizedResponse(response, "Missing token");
        }
        
        // 这里可以添加更复杂的token验证逻辑
        // 例如调用认证服务验证token的有效性
        
        return chain.filter(exchange);
    }
    
    /**
     * 从请求中获取token，支持多种头部格式
     */
    private String getTokenFromRequest(ServerHttpRequest request) {
        // 优先检查前端发送的token头部
        String token = request.getHeaders().getFirst("token");
        if (token != null && !token.trim().isEmpty()) {
            return token;
        }
        
        // 兼容Authorization头部
        String authHeader = request.getHeaders().getFirst("Authorization");
        if (authHeader != null && !authHeader.trim().isEmpty()) {
            // 支持Bearer token格式
            if (authHeader.startsWith("Bearer ")) {
                return authHeader.substring(7);
            }
            return authHeader;
        }
        
        return null;
    }
    
    /**
     * 检查路径是否允许无认证访问
     */
    private boolean isAllowedPath(String path) {
        // 允许的路径列表
        String[] allowedPaths = {
            "/authCenter/auth/login",
            "/authCenter/login",
            "/authCenter/register", 
            "/authCenter/auth/register",
            "/actuator",
            "/v2/api-docs",
            "/doc.html",
            "/webjars/",
            "/swagger-ui/"
        };
        
        for (String allowedPath : allowedPaths) {
            if (path.startsWith(allowedPath)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * 返回未认证响应
     */
    private Mono<Void> unauthorizedResponse(ServerHttpResponse response, String message) {
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        
        String body = String.format("{\"code\":401,\"message\":\"%s\",\"data\":null}", message);
        DataBuffer buffer = response.bufferFactory().wrap(body.getBytes(StandardCharsets.UTF_8));
        
        return response.writeWith(Mono.just(buffer));
    }
    
    @Override
    public int getOrder() {
        // 设置过滤器顺序，数值越小优先级越高
        return -100;
    }
}