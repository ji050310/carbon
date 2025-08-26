package com.carbon.gate.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 请求日志过滤器
 * 记录请求信息用于调试
 */
@Slf4j
@Component
public class RequestLogFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        
        long startTime = System.currentTimeMillis();
        String path = request.getURI().getPath();
        String method = request.getMethodValue();
        String query = request.getURI().getQuery();
        
        log.info("网关请求开始 -> 方法: {}, 路径: {}, 查询参数: {}", method, path, query);
        
        return chain.filter(exchange).then(
            Mono.fromRunnable(() -> {
                long endTime = System.currentTimeMillis();
                log.info("网关请求结束 -> 路径: {}, 耗时: {}ms", path, (endTime - startTime));
            })
        );
    }
    
    @Override
    public int getOrder() {
        // 在其他业务过滤器之后执行
        return 0;
    }
}
