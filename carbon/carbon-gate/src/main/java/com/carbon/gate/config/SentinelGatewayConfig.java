package com.carbon.gate.config;

import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayFlowRule;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayRuleManager;
import com.alibaba.csp.sentinel.datasource.ReadableDataSource;
import com.alibaba.csp.sentinel.datasource.nacos.NacosDataSource;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayRuleManager;
import com.alibaba.csp.sentinel.datasource.ReadableDataSource;

import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.GatewayCallbackManager;
import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.DefaultBlockRequestHandler;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Set;
import java.util.Collections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Configuration
public class SentinelGatewayConfig {

    private static final Logger log = LoggerFactory.getLogger(SentinelGatewayConfig.class);

    // 从 application.yml 注入 nacos 地址，默认 127.0.0.1:8848
    @Value("${spring.cloud.nacos.discovery.server-addr:127.0.0.1:8848}")
    private String nacosServerAddr;
    private final String groupId = "SENTINEL_GROUP";

    private final ObjectMapper objectMapper = new ObjectMapper();

    @PostConstruct
    public void doInit() {
        // 动态注册 NacosDataSource
        registerGwFlowDataSource();
        registerFlowDataSource();
        registerDegradeDataSource();

        // 自定义阻断处理
        initBlockHandler();
    }

    private void registerGwFlowDataSource() {
        String dataId = "carbon-gate-gw-flow-rules";
        log.info("Register NacosDataSource for gw-flow, dataId={}", dataId);

        ReadableDataSource<String, java.util.Set<GatewayFlowRule>> gwDs =
                new NacosDataSource<>(nacosServerAddr, groupId, dataId, source -> {
                    try {
                        if (source == null || source.trim().isEmpty()) {
                            log.warn("Nacos gw-flow dataId={} is empty, use empty rule set", dataId);
                            return Collections.emptySet();
                        }
                        // 解析为 Set 而不是 List
                        return objectMapper.readValue(source, new TypeReference<java.util.Set<GatewayFlowRule>>() {});
                    } catch (Exception ex) {
                        log.error("parse gw-flow rules error", ex);
                        return Collections.emptySet();
                    }
                });

        // 现在类型匹配：SentinelProperty<Set<GatewayFlowRule>>
        GatewayRuleManager.register2Property(gwDs.getProperty());
        log.info("Gateway gw-flow datasource registered: {}", dataId);
    }

    private void registerFlowDataSource() {
        String dataId = "carbon-gate-flow-rules";
        log.info("Register NacosDataSource for flow, dataId={}", dataId);

        ReadableDataSource<String, List<FlowRule>> flowDs =
                new NacosDataSource<>(nacosServerAddr, groupId, dataId,
                        source -> {
                            try {
                                if (source == null || source.trim().isEmpty()) {
                                    log.warn("Nacos flow dataId={} is empty, use empty flow list", dataId);
                                    return Collections.emptyList();
                                }
                                return objectMapper.readValue(source, new TypeReference<List<FlowRule>>() {});
                            } catch (Exception ex) {
                                log.error("parse flow rules error", ex);
                                return Collections.emptyList();
                            }
                        });

        FlowRuleManager.register2Property(flowDs.getProperty());
        log.info("Flow datasource registered: {}", dataId);
    }

    private void registerDegradeDataSource() {
        String dataId = "carbon-gate-degrade-rules";
        log.info("Register NacosDataSource for degrade, dataId={}", dataId);

        ReadableDataSource<String, List<DegradeRule>> degradeDs =
                new NacosDataSource<>(nacosServerAddr, groupId, dataId,
                        source -> {
                            try {
                                if (source == null || source.trim().isEmpty()) {
                                    log.warn("Nacos degrade dataId={} is empty, use empty degrade list", dataId);
                                    return Collections.emptyList();
                                }
                                return objectMapper.readValue(source, new TypeReference<List<DegradeRule>>() {});
                            } catch (Exception ex) {
                                log.error("parse degrade rules error", ex);
                                return Collections.emptyList();
                            }
                        });

        DegradeRuleManager.register2Property(degradeDs.getProperty());
        log.info("Degrade datasource registered: {}", dataId);
    }

    private void initBlockHandler() {
        GatewayCallbackManager.setBlockHandler(new DefaultBlockRequestHandler() {
            @Override
            public Mono<ServerResponse> handleRequest(ServerWebExchange exchange, Throwable ex) {
                // 自定义返回体，便于客户端识别
                return ServerResponse.ok().bodyValue("{\"code\":429,\"message\":\"请求过于频繁，请稍后重试\"}");
            }
        });
        log.info("Custom Gateway block handler registered.");
    }
}