# Spring Boot 升级指南

## 概述

本指南将帮助您将碳资产管理平台从 Spring Boot 2.3.2.RELEASE 升级到 2.7.18 版本。

## 当前环境

- **当前版本**: Spring Boot 2.3.2.RELEASE
- **目标版本**: Spring Boot 2.7.18
- **JDK版本**: 8u202 (完全兼容)
- **项目架构**: 微服务架构

## 升级文件说明

本升级包包含以下文件：

1. **upgrade-spring-boot-2.7.md** - 详细的升级指南
2. **upgrade-spring-boot.sh** - Linux/Mac 自动化升级脚本
3. **upgrade-spring-boot.bat** - Windows 自动化升级脚本
4. **check-dependencies.py** - 依赖检查脚本
5. **carbon-frame/pom-upgraded.xml** - 升级后的POM文件模板

## 快速升级步骤

### 方法一：使用自动化脚本（推荐）

#### Windows 用户：
```cmd
# 在项目根目录下运行
upgrade-spring-boot.bat
```

#### Linux/Mac 用户：
```bash
# 在项目根目录下运行
chmod +x upgrade-spring-boot.sh
./upgrade-spring-boot.sh
```

### 方法二：手动升级

1. **备份项目**
   ```bash
   cp -r carbon carbon_backup_$(date +%Y%m%d)
   ```

2. **替换父POM文件**
   ```bash
   cp carbon-frame/pom-upgraded.xml carbon-frame/pom.xml
   ```

3. **清理并重新编译**
   ```bash
   mvn clean install -DskipTests
   ```

## 版本升级对照表

| 组件 | 当前版本 | 升级后版本 | 说明 |
|------|----------|------------|------|
| Spring Boot | 2.3.2.RELEASE | 2.7.18 | 主框架 |
| Spring Cloud | Hoxton.SR8 | 2021.0.8 | 微服务框架 |
| Spring Cloud Alibaba | 2.2.5.RELEASE | 2021.0.5.0 | 阿里云组件 |
| FastJSON | 1.2.47 | 1.2.83 | 安全版本 |
| Maven Surefire Plugin | 2.12.4 | 3.0.0 | 测试插件 |

## 升级后验证

### 1. 编译验证
```bash
mvn clean compile -DskipTests
```

### 2. 依赖检查
```bash
python check-dependencies.py
```

### 3. 依赖树分析
```bash
mvn dependency:tree
```

### 4. 服务启动测试
按以下顺序启动服务：
1. Nacos (服务注册中心)
2. Redis (缓存)
3. MySQL (数据库)
4. 各个微服务模块

## 可能遇到的问题

### 1. 依赖冲突
**现象**: 编译时出现依赖冲突错误
**解决**: 
```bash
mvn dependency:tree -Dverbose
# 根据输出结果排除冲突的依赖
```

### 2. 配置文件兼容性
**现象**: 启动时配置错误
**解决**: 检查 `application.yml` 中的配置项，特别是：
- `spring.main.allow-bean-definition-overriding`
- 数据库连接配置
- Redis连接配置

### 3. 微服务注册失败
**现象**: 服务无法注册到Nacos
**解决**: 
- 确保Nacos版本兼容（建议2.0+）
- 检查网络连接
- 验证配置文件中的Nacos地址

## 推荐的附加软件版本

| 软件 | 推荐版本 | 说明 |
|------|----------|------|
| Maven | 3.8+ | 构建工具 |
| Nacos | 2.2+ | 服务注册发现 |
| Redis | 6.0+ | 缓存 |
| MySQL | 8.0+ | 数据库 |
| RocketMQ | 4.9+ | 消息队列 |
| Elasticsearch | 7.17+ | 搜索引擎 |

## 回滚方案

如果升级过程中遇到问题，可以快速回滚：

```bash
# 恢复备份
rm -rf carbon
cp -r carbon_backup_YYYYMMDD carbon

# 或者使用Git回滚
git reset --hard HEAD~1
```

## 升级检查清单

- [ ] 项目备份完成
- [ ] POM文件更新完成
- [ ] 编译通过
- [ ] 依赖检查通过
- [ ] 所有服务正常启动
- [ ] 服务注册成功
- [ ] 数据库连接正常
- [ ] 微服务间调用正常
- [ ] 前端接口访问正常
- [ ] 性能测试通过

## 注意事项

1. **分阶段升级**: 建议先在测试环境进行升级测试
2. **数据备份**: 升级前务必备份数据库
3. **监控**: 升级后密切监控系统性能和日志
4. **文档**: 更新相关技术文档和部署文档
5. **团队协作**: 确保团队成员了解升级变更

## 技术支持

如果在升级过程中遇到问题，请：

1. 查看项目日志文件
2. 检查依赖冲突
3. 参考Spring Boot官方升级指南
4. 联系技术支持团队

## 升级完成

升级完成后，您的项目将获得：

- 更好的性能和稳定性
- 最新的安全补丁
- 更好的兼容性
- 更多的功能特性

恭喜您完成Spring Boot升级！
