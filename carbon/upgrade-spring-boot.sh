#!/bin/bash

# Spring Boot 升级脚本
# 从 2.3.2.RELEASE 升级到 2.7.18

set -e

echo "=========================================="
echo "Spring Boot 升级脚本"
echo "从 2.3.2.RELEASE 升级到 2.7.18"
echo "=========================================="

# 检查是否在正确的目录
if [ ! -f "carbon-frame/pom.xml" ]; then
    echo "错误: 请在carbon项目根目录下运行此脚本"
    exit 1
fi

# 备份项目
echo "第一步: 备份项目..."
BACKUP_DIR="carbon_backup_$(date +%Y%m%d_%H%M%S)"
cp -r . "../$BACKUP_DIR"
echo "项目已备份到: ../$BACKUP_DIR"

# 更新父POM文件
echo "第二步: 更新父POM文件..."
sed -i 's/<version>2.3.2.RELEASE<\/version>/<version>2.7.18<\/version>/g' carbon-frame/pom.xml
sed -i 's/<spring.cloud-version>Hoxton.SR8<\/spring.cloud-version>/<spring.cloud-version>2021.0.8<\/spring.cloud-version>/g' carbon-frame/pom.xml
sed -i 's/<spring.cloud.alibaba-version>2.2.5.RELEASE<\/spring.cloud.alibaba-version>/<spring.cloud.alibaba-version>2021.0.5.0<\/spring.cloud.alibaba-version>/g' carbon-frame/pom.xml
sed -i 's/<fastjson.version>1.2.47<\/fastjson.version>/<fastjson.version>1.2.83<\/fastjson.version>/g' carbon-frame/pom.xml

# 更新Maven插件版本
echo "第三步: 更新Maven插件..."
sed -i 's/<version>2.12.4<\/version>/<version>3.0.0<\/version>/g' carbon-frame/pom.xml

# 移除单独的spring-boot-starter-validation依赖
echo "第四步: 清理依赖管理..."
# 这里需要手动编辑，因为sed比较复杂

echo "第五步: 清理Maven缓存..."
mvn clean

echo "第六步: 重新编译项目..."
mvn compile -DskipTests

echo "=========================================="
echo "升级完成！"
echo "请检查以下内容："
echo "1. 检查 carbon-frame/pom.xml 文件是否正确更新"
echo "2. 运行 'mvn clean install -DskipTests' 进行完整编译"
echo "3. 逐个启动服务进行测试"
echo "4. 如果遇到问题，可以从备份恢复: ../$BACKUP_DIR"
echo "=========================================="

# 显示更新后的版本信息
echo "更新后的版本信息："
grep -A 5 -B 5 "spring-boot-starter-parent" carbon-frame/pom.xml
echo ""
grep -A 3 -B 3 "spring.cloud-version" carbon-frame/pom.xml
echo ""
grep -A 3 -B 3 "spring.cloud.alibaba-version" carbon-frame/pom.xml
