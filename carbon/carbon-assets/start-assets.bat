@echo off
REM Carbon Assets 项目启动脚本 (Windows)
REM 适配环境：JDK 8u202, Nacos 2.2.3, Redis 5.0.14, RocketMQ 5.3.3, Elasticsearch 7.8.0

echo ==================================
echo Carbon Assets 服务启动脚本
echo ==================================

REM JVM 优化参数（适配 JDK 8u202）
set JVM_OPTS=-Xms512m -Xmx2g -XX:+UseG1GC -XX:MaxGCPauseMillis=200 -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=./heapdump.hprof

REM Spring Boot 优化参数
set SPRING_OPTS=--spring.profiles.active=test --server.port=9003 --spring.main.allow-circular-references=true --springfox.documentation.enabled=false

echo JVM参数: %JVM_OPTS%
echo Spring参数: %SPRING_OPTS%
echo ==================================

REM 启动应用
java %JVM_OPTS% -jar carbon-assets-1.0-SNAPSHOT.jar %SPRING_OPTS%

echo ==================================
echo 应用启动完成，请检查日志
echo ==================================
pause
