# Sentinel 配置说明

本项目已集成 Sentinel 熔断机制，支持与 Nacos 和 OpenFeign 的完整整合。

## 1. 架构组件

### 已集成的组件：
- **Spring Cloud Gateway**: API网关，集成网关限流
- **Sentinel Dashboard**: 熔断监控面板 (端口: 8080)
- **Nacos**: 配置中心和服务注册中心
- **OpenFeign**: 服务间调用，支持熔断

### 端口分配：
- carbon-gate: 9091 (Sentinel端口: 8719)
- carbon-auth: 9001 (Sentinel端口: 8721)  
- carbon-system: 9002 (Sentinel端口: 8722)
- carbon-assets: 9003 (Sentinel端口: 8723)

## 2. 功能特性

### Gateway 网关层熔断：
- 路由级别的流量控制
- API分组限流规则
- 自定义限流响应处理
- 支持热更新规则配置

### 服务层熔断：
- Feign客户端熔断
- 方法级别的限流和降级
- 系统自适应限流
- 热点参数限流

### 规则持久化：
- 所有规则存储在 Nacos 配置中心
- 支持动态更新规则
- 规则变更实时生效

## 3. 使用方式

### 在代码中使用 @SentinelResource 注解：

```java
@RestController
public class UserController {
    
    @SentinelResource(
        value = "getUserInfo",
        fallback = "getUserInfoFallback",
        blockHandler = "getUserInfoBlockHandler"
    )
    @GetMapping("/user/{id}")
    public Result getUserInfo(@PathVariable Long id) {
        // 业务逻辑
        return userService.getUserById(id);
    }
    
    // 降级方法
    public Result getUserInfoFallback(Long id, Throwable ex) {
        return Result.error("服务暂时不可用，请稍后重试");
    }
    
    // 限流处理方法
    public Result getUserInfoBlockHandler(Long id, BlockException ex) {
        return Result.error("访问频率过高，请稍后重试");
    }
}
```

### Feign 客户端熔断：

```java
@FeignClient(name = "carbon-system", fallback = SystemServiceFallback.class)
public interface SystemService {
    @GetMapping("/system/user/{id}")
    Result<User> getUserById(@PathVariable("id") Long id);
}

@Component
public class SystemServiceFallback implements SystemService {
    @Override
    public Result<User> getUserById(Long id) {
        return Result.error("系统服务暂时不可用");
    }
}
```

## 4. 规则配置

### Nacos 配置示例：

**流控规则 (DataId: carbon-auth-flow-rules)**
```json
[
    {
        "resource": "getUserInfo",
        "count": 100,
        "grade": 1,
        "limitApp": "default",
        "strategy": 0,
        "controlBehavior": 0
    }
]
```

**降级规则 (DataId: carbon-auth-degrade-rules)**
```json
[
    {
        "resource": "getUserInfo", 
        "count": 0.1,
        "grade": 2,
        "timeWindow": 10,
        "minRequestAmount": 5,
        "statIntervalMs": 1000
    }
]
```

## 5. 启动说明

### 环境准备：
1. 启动 Nacos (端口: 8848)
2. 启动 Sentinel Dashboard (端口: 8080)
3. 确保 MySQL 和 Redis 正常运行

### 启动顺序：
1. carbon-auth (认证服务)
2. carbon-system (系统服务)
3. carbon-assets (资产服务)
4. carbon-gate (网关服务)

### 验证方式：
- 访问 Sentinel Dashboard: http://localhost:8080
- 查看服务注册情况: http://localhost:8848/nacos
- 通过网关访问服务: http://localhost:9091/authCenter/

## 6. 监控和运维

### Sentinel Dashboard 功能：
- 实时监控各服务的QPS、响应时间
- 动态配置限流规则
- 查看熔断情况和恢复状态
- 集群流控配置

### 日志监控：
- 各服务都会记录 Sentinel 相关日志
- 可通过日志查看规则加载和触发情况

## 7. 注意事项

1. **端口冲突**: 确保各服务的 Sentinel 端口不冲突
2. **规则配置**: 建议先在测试环境验证规则效果
3. **依赖版本**: 确保 Spring Cloud Alibaba 版本兼容
4. **网络配置**: 确保服务能访问 Nacos 和 Sentinel Dashboard
