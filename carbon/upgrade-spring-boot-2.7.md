# Spring Boot 2.3.2 升级到 2.7.18 指南

## 升级概述
- 当前版本: Spring Boot 2.3.2.RELEASE
- 目标版本: Spring Boot 2.7.18
- JDK版本: 8u202 (兼容)

## 版本兼容性矩阵
| 组件 | 当前版本 | 升级后版本 | 说明 |
|------|----------|------------|------|
| Spring Boot | 2.3.2.RELEASE | 2.7.18 | 主框架 |
| Spring Cloud | Hoxton.SR8 | 2021.0.8 | 微服务框架 |
| Spring Cloud Alibaba | 2.2.5.RELEASE | 2021.0.5.0 | 阿里云组件 |
| Maven | 当前版本 | 3.6+ | 构建工具 |

## 升级步骤

### 第一步：备份项目
```bash
# 备份整个项目
cp -r carbon carbon_backup_$(date +%Y%m%d)
```

### 第二步：更新父POM文件
修改 `carbon-frame/pom.xml` 中的版本信息：

```xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.7.18</version>
    <relativePath/>
</parent>

<properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
    <java.version>1.8</java.version>
    <fastjson.version>1.2.83</fastjson.version>  <!-- 升级到安全版本 -->
    <spring.cloud-version>2021.0.8</spring.cloud-version>
    <spring.cloud.alibaba-version>2021.0.5.0</spring.cloud.alibaba-version>
    <rocketmq.version>2.2.3</rocketmq.version>
</properties>
```

### 第三步：更新依赖管理
在 `dependencyManagement` 中更新：

```xml
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-dependencies</artifactId>
            <version>${spring.cloud-version}</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-alibaba-dependencies</artifactId>
            <version>${spring.cloud.alibaba-version}</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
        <!-- 移除单独的spring-boot-starter-validation依赖，由父POM管理 -->
    </dependencies>
</dependencyManagement>
```

### 第四步：更新Maven插件
```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-surefire-plugin</artifactId>
    <version>3.0.0</version>  <!-- 升级版本 -->
    <configuration>
        <skipTests>true</skipTests>
    </configuration>
</plugin>
```

### 第五步：配置文件调整

#### 5.1 数据库连接配置
在 `application-dev.yml` 中确保使用正确的MySQL驱动：

```yaml
spring:
  datasource:
    druid:
      driver-class-name: com.mysql.cj.jdbc.Driver
      url: jdbc:mysql://localhost:3306/carbon?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai&useSSL=false&allowPublicKeyRetrieval=true
```

#### 5.2 Elasticsearch配置
如果使用Elasticsearch，需要更新配置：

```yaml
spring:
  elasticsearch:
    rest:
      uris: http://localhost:9200
      connection-timeout: 1s
      read-timeout: 30s
```

### 第六步：代码兼容性检查

#### 6.1 检查废弃的API
- 检查 `@EnableEurekaClient` 是否还在使用（建议使用 `@EnableDiscoveryClient`）
- 检查 `@EnableHystrix` 是否还在使用（Spring Cloud 2021.x中已移除）

#### 6.2 更新注解
- 确保所有微服务都使用 `@EnableDiscoveryClient`
- 检查Feign客户端配置

### 第七步：测试升级

#### 7.1 清理并重新编译
```bash
cd carbon
mvn clean install -DskipTests
```

#### 7.2 逐个启动服务测试
```bash
# 按依赖顺序启动服务
# 1. 启动Nacos
# 2. 启动Redis
# 3. 启动MySQL
# 4. 启动各个微服务
```

## 可能遇到的问题及解决方案

### 1. 依赖冲突
如果遇到依赖冲突，使用以下命令分析：
```bash
mvn dependency:tree -Dverbose
```

### 2. 配置文件兼容性
- 检查 `application.yml` 中的配置项是否在新版本中已废弃
- 特别注意 `spring.main.allow-bean-definition-overriding` 配置

### 3. 数据库连接问题
- 确保MySQL驱动版本兼容
- 检查连接池配置

### 4. 微服务注册问题
- 确保Nacos版本兼容（建议使用2.0+）
- 检查服务发现配置

## 升级验证清单

- [ ] 所有服务能够正常启动
- [ ] 服务注册到Nacos成功
- [ ] 数据库连接正常
- [ ] Redis连接正常
- [ ] 微服务间调用正常
- [ ] 前端接口访问正常
- [ ] 日志输出正常
- [ ] 性能测试通过

## 回滚方案

如果升级过程中遇到问题，可以快速回滚：

```bash
# 恢复备份
rm -rf carbon
cp -r carbon_backup_$(date +%Y%m%d) carbon

# 或者使用Git回滚
git reset --hard HEAD~1
```

## 注意事项

1. **分阶段升级**: 建议先在测试环境进行升级测试
2. **数据备份**: 升级前务必备份数据库
3. **监控**: 升级后密切监控系统性能和日志
4. **文档**: 更新相关技术文档和部署文档

## 推荐的附加软件版本

| 软件 | 推荐版本 | 说明 |
|------|----------|------|
| Maven | 3.8+ | 构建工具 |
| Nacos | 2.2+ | 服务注册发现 |
| Redis | 6.0+ | 缓存 |
| MySQL | 8.0+ | 数据库 |
| RocketMQ | 4.9+ | 消息队列 |
| Elasticsearch | 7.17+ | 搜索引擎 |
