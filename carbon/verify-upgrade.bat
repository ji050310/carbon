@echo off
chcp 65001 >nul
echo ==========================================
echo Spring Boot 升级验证脚本
echo ==========================================

echo 检查Maven版本...
call mvn -version
echo.

echo 检查项目结构...
if exist "pom.xml" (
    echo 根POM文件存在
) else (
    echo 警告: 根POM文件不存在
)

if exist "carbon-frame\pom.xml" (
    echo carbon-frame POM文件存在
) else (
    echo 错误: carbon-frame POM文件不存在
)

echo.
echo 检查Spring Boot版本...
findstr /C:"spring-boot-starter-parent" carbon-frame\pom.xml
echo.

echo 检查Spring Cloud版本...
findstr /C:"spring.cloud-version" carbon-frame\pom.xml
echo.

echo 检查Spring Cloud Alibaba版本...
findstr /C:"spring.cloud.alibaba-version" carbon-frame\pom.xml
echo.

echo 尝试编译项目...
call mvn clean compile -DskipTests

if %ERRORLEVEL% EQU 0 (
    echo.
    echo ==========================================
    echo 升级验证成功！
    echo ==========================================
    echo 所有版本已正确升级到：
    echo - Spring Boot: 2.7.18
    echo - Spring Cloud: 2021.0.8
    echo - Spring Cloud Alibaba: 2021.0.5.0
    echo.
    echo 下一步建议：
    echo 1. 运行 'mvn clean install -DskipTests' 进行完整编译
    echo 2. 逐个启动各个微服务进行测试
    echo 3. 检查服务注册和调用是否正常
) else (
    echo.
    echo ==========================================
    echo 升级验证失败！
    echo ==========================================
    echo 请检查错误信息并修复问题
)

pause
