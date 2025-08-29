# Sentinel 流控测试指南

## 服务端口信息
- **carbon-gate (网关)**: http://localhost:9091
- **carbon-auth (认证服务)**: http://localhost:9001/authCenter
- **carbon-assets (资产服务)**: http://localhost:9003/assets
- **Sentinel Dashboard**: http://localhost:8080 (用户名/密码: sentinel/sentinel)
- **Nacos控制台**: http://localhost:8848/nacos (用户名/密码: nacos/nacos)

## 测试步骤

### 第1步：访问 Sentinel Dashboard
1. 打开浏览器访问: http://localhost:8080
2. 登录用户名: `sentinel`, 密码: `sentinel`
3. 查看是否显示 `carbon-auth` 和 `carbon-gate` 服务

### 第2步：生成一些访问流量
执行以下命令生成访问流量，让服务在Sentinel中显示：

```bash
# 访问认证服务
curl -X GET "http://localhost:9001/authCenter/actuator/health"

# 通过网关访问认证服务  
curl -X GET "http://localhost:9091/authCenter/actuator/health"

# 通过网关访问资产服务
curl -X GET "http://localhost:9091/assets/actuator/health"
```

### 第3步：配置流控规则
在 Sentinel Dashboard 中：
1. 选择对应的服务（如 carbon-auth）
2. 点击"流控规则"
3. 新增规则：
   - 资源名：选择一个API接口
   - 阈值类型：QPS
   - 单机阈值：5 (每秒最多5个请求)
   - 流控效果：快速失败

### 第4步：测试流控效果
使用压测工具测试流控是否生效：

```bash
# 使用 for 循环快速发送多个请求
for i in {1..20}; do curl -X GET "http://localhost:9001/authCenter/actuator/health" & done
```

### 第5步：观察流控效果
- 在 Sentinel Dashboard 中查看实时监控
- 观察QPS、拒绝数等指标
- 被限流的请求应该返回 429 状态码或自定义限流响应

## 高级测试

### 网关流控测试
测试网关级别的流控：
1. 在 Sentinel Dashboard 选择 carbon-gate 服务
2. 配置API分组流控规则
3. 测试通过网关的请求限流

### 服务间调用流控测试
测试微服务之间的调用流控：
1. 找到服务间调用的资源名（通常以 Feign 客户端命名）
2. 配置对应的流控规则
3. 测试服务降级效果

### 系统规则测试
配置系统级别的保护规则：
1. 在"系统规则"中配置
2. 设置CPU使用率、系统负载等阈值
3. 观察系统保护效果

## 故障排查

### 如果服务未在Dashboard显示：
1. 检查服务是否正常启动
2. 确认 Sentinel 客户端端口（8721, 8722）是否正常
3. 访问几次服务接口触发监控数据上报
4. 检查应用日志中的 Sentinel 相关信息

### 如果流控不生效：
1. 确认资源名是否正确
2. 检查规则配置是否保存成功
3. 验证请求路径是否匹配规则
4. 查看 Sentinel 日志获取详细信息

## 日志位置
- Sentinel 日志: `C:\Users\ji\logs\csp\`
- 应用日志: 查看各服务控制台输出
