#!/bin/bash

# Carbon Assets 项目启动脚本
# 适配环境：JDK 8u202, Nacos 2.2.3, Redis 5.0.14, RocketMQ 5.3.3, Elasticsearch 7.8.0

echo "=================================="
echo "Carbon Assets 服务启动脚本"
echo "=================================="

# JVM 优化参数（适配 JDK 8u202）
JVM_OPTS="-Xms512m -Xmx2g"
JVM_OPTS="$JVM_OPTS -XX:+UseG1GC"
JVM_OPTS="$JVM_OPTS -XX:MaxGCPauseMillis=200"
JVM_OPTS="$JVM_OPTS -XX:+HeapDumpOnOutOfMemoryError"
JVM_OPTS="$JVM_OPTS -XX:HeapDumpPath=./heapdump.hprof"

# Spring Boot 优化参数
SPRING_OPTS="--spring.profiles.active=test"
SPRING_OPTS="$SPRING_OPTS --server.port=9003"
SPRING_OPTS="$SPRING_OPTS --spring.main.allow-circular-references=true"

# 禁用 Swagger（解决兼容性问题）
SPRING_OPTS="$SPRING_OPTS --springfox.documentation.enabled=false"

echo "JVM参数: $JVM_OPTS"
echo "Spring参数: $SPRING_OPTS"
echo "=================================="

# 启动应用
java $JVM_OPTS -jar carbon-assets-1.0-SNAPSHOT.jar $SPRING_OPTS

echo "=================================="
echo "应用启动完成，请检查日志"
echo "=================================="
