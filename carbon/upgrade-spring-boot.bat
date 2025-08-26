@echo off
chcp 65001
echo ==========================================
echo Spring Boot 升级脚本
echo 从 2.3.2.RELEASE 升级到 2.7.18
echo ==========================================

REM 检查是否在正确的目录
if not exist "carbon-frame\pom.xml" (
    echo 错误: 请在carbon项目根目录下运行此脚本
    pause
    exit /b 1
)

REM 备份项目
echo 第一步: 备份项目...
set BACKUP_DIR=carbon_backup_%date:~0,4%%date:~5,2%%date:~8,2%_%time:~0,2%%time:~3,2%%time:~6,2%
set BACKUP_DIR=%BACKUP_DIR: =0%
xcopy /E /I /Y . "..\%BACKUP_DIR%"
echo 项目已备份到: ..\%BACKUP_DIR%

REM 更新父POM文件
echo 第二步: 更新父POM文件...
powershell -Command "(Get-Content 'carbon-frame\pom.xml') -replace '<version>2.3.2.RELEASE</version>', '<version>2.7.18</version>' | Set-Content 'carbon-frame\pom.xml'"
powershell -Command "(Get-Content 'carbon-frame\pom.xml') -replace '<spring.cloud-version>Hoxton.SR8</spring.cloud-version>', '<spring.cloud-version>2021.0.8</spring.cloud-version>' | Set-Content 'carbon-frame\pom.xml'"
powershell -Command "(Get-Content 'carbon-frame\pom.xml') -replace '<spring.cloud.alibaba-version>2.2.5.RELEASE</spring.cloud.alibaba-version>', '<spring.cloud.alibaba-version>2021.0.5.0</spring.cloud.alibaba-version>' | Set-Content 'carbon-frame\pom.xml'"
powershell -Command "(Get-Content 'carbon-frame\pom.xml') -replace '<fastjson.version>1.2.47</fastjson.version>', '<fastjson.version>1.2.83</fastjson.version>' | Set-Content 'carbon-frame\pom.xml'"

REM 更新Maven插件版本
echo 第三步: 更新Maven插件...
powershell -Command "(Get-Content 'carbon-frame\pom.xml') -replace '<version>2.12.4</version>', '<version>3.0.0</version>' | Set-Content 'carbon-frame\pom.xml'"

echo 第四步: 清理Maven缓存...
call mvn clean

echo 第五步: 重新编译项目...
call mvn compile -DskipTests

echo ==========================================
echo 升级完成！
echo 请检查以下内容：
echo 1. 检查 carbon-frame\pom.xml 文件是否正确更新
echo 2. 运行 'mvn clean install -DskipTests' 进行完整编译
echo 3. 逐个启动服务进行测试
echo 4. 如果遇到问题，可以从备份恢复: ..\%BACKUP_DIR%
echo ==========================================

REM 显示更新后的版本信息
echo 更新后的版本信息：
findstr /C:"spring-boot-starter-parent" carbon-frame\pom.xml
echo.
findstr /C:"spring.cloud-version" carbon-frame\pom.xml
echo.
findstr /C:"spring.cloud.alibaba-version" carbon-frame\pom.xml

pause
