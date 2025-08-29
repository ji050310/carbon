@echo off
echo Sentinel 流控测试脚本
echo ===================

echo.
echo 第1步：生成初始流量，让服务在Sentinel Dashboard中显示
echo.

echo 正在访问认证服务健康检查...
curl -X GET "http://localhost:9001/authCenter/actuator/health"
echo.

echo 正在通过网关访问认证服务...
curl -X GET "http://localhost:9091/authCenter/actuator/health" 
echo.

echo 正在通过网关访问资产服务...
curl -X GET "http://localhost:9091/assets/actuator/health"
echo.

timeout /t 3 /nobreak

echo.
echo 第2步：请打开 Sentinel Dashboard 配置流控规则
echo 访问地址: http://localhost:8080
echo 用户名: sentinel, 密码: sentinel
echo.
echo 按任意键继续进行流控测试...
pause

echo.
echo 第3步：开始流控测试 - 快速发送20个请求到认证服务
echo 预期效果：超过阈值的请求会被限流
echo.

for /L %%i in (1,1,20) do (
    echo 发送第%%i个请求...
    curl -s -w "状态码: %%{http_code} 响应时间: %%{time_total}s\n" -X GET "http://localhost:9001/authCenter/actuator/health"
    timeout /t 0.1 /nobreak >nul
)

echo.
echo 测试完成！请在 Sentinel Dashboard 中查看：
echo - 实时监控数据
echo - QPS统计
echo - 拒绝请求数量
echo - 响应时间变化
echo.

echo 如需测试网关流控，按任意键继续...
pause

echo.
echo 第4步：网关流控测试 - 通过网关发送请求
echo.

for /L %%i in (1,1,15) do (
    echo 通过网关发送第%%i个请求...
    curl -s -w "状态码: %%{http_code}\n" -X GET "http://localhost:9091/authCenter/actuator/health"
    timeout /t 0.2 /nobreak >nul
)

echo.
echo 全部测试完成！
echo 请在 Sentinel Dashboard 中查看各项监控数据
echo.
pause
