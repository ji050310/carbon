echo off
REM Sentinel 规则配置脚本 (Windows)
REM 用于向 Nacos 添加默认的 Sentinel 规则配置

set NACOS_SERVER=127.0.0.1:8848
set GROUP_ID=SENTINEL_GROUP

echo ==========================================
echo 配置 Sentinel 规则到 Nacos
echo ==========================================

REM 网关流控规则
echo 配置网关流控规则...
curl -X POST "http://%NACOS_SERVER%/nacos/v1/cs/configs" ^
  -d "dataId=carbon-gate-gw-flow-rules" ^
  -d "group=%GROUP_ID%" ^
  -d "content=[{\"resource\":\"authCenter\",\"count\":100,\"intervalSec\":1,\"grade\":1,\"burst\":5},{\"resource\":\"system\",\"count\":50,\"intervalSec\":1,\"grade\":1,\"burst\":3},{\"resource\":\"assets\",\"count\":80,\"intervalSec\":1,\"grade\":1,\"burst\":5},{\"resource\":\"trade\",\"count\":60,\"intervalSec\":1,\"grade\":1,\"burst\":3}]"

REM 认证服务流控规则
echo 配置认证服务流控规则...
curl -X POST "http://%NACOS_SERVER%/nacos/v1/cs/configs" ^
  -d "dataId=carbon-auth-flow-rules" ^
  -d "group=%GROUP_ID%" ^
  -d "content=[{\"resource\":\"/authCenter/auth/login\",\"count\":20,\"grade\":1,\"limitApp\":\"default\",\"strategy\":0,\"controlBehavior\":0},{\"resource\":\"/authCenter/auth/logout\",\"count\":10,\"grade\":1,\"limitApp\":\"default\",\"strategy\":0,\"controlBehavior\":0}]"

REM 认证服务降级规则
echo 配置认证服务降级规则...
curl -X POST "http://%NACOS_SERVER%/nacos/v1/cs/configs" ^
  -d "dataId=carbon-auth-degrade-rules" ^
  -d "group=%GROUP_ID%" ^
  -d "content=[{\"resource\":\"/authCenter/auth/login\",\"count\":0.5,\"grade\":2,\"timeWindow\":10,\"minRequestAmount\":3,\"statIntervalMs\":1000,\"slowRatioThreshold\":0.3}]"

REM 系统服务流控规则
echo 配置系统服务流控规则...
curl -X POST "http://%NACOS_SERVER%/nacos/v1/cs/configs" ^
  -d "dataId=carbon-system-flow-rules" ^
  -d "group=%GROUP_ID%" ^
  -d "content=[{\"resource\":\"/system/user\",\"count\":30,\"grade\":1,\"limitApp\":\"default\",\"strategy\":0,\"controlBehavior\":0},{\"resource\":\"/system/role\",\"count\":20,\"grade\":1,\"limitApp\":\"default\",\"strategy\":0,\"controlBehavior\":0}]"

REM 资产服务流控规则
echo 配置资产服务流控规则...
curl -X POST "http://%NACOS_SERVER%/nacos/v1/cs/configs" ^
  -d "dataId=carbon-assets-flow-rules" ^
  -d "group=%GROUP_ID%" ^
  -d "content=[{\"resource\":\"/assets/carbon\",\"count\":50,\"grade\":1,\"limitApp\":\"default\",\"strategy\":0,\"controlBehavior\":0},{\"resource\":\"/assets/project\",\"count\":30,\"grade\":1,\"limitApp\":\"default\",\"strategy\":0,\"controlBehavior\":0}]"

REM 交易服务流控规则
echo 配置交易服务流控规则...
curl -X POST "http://%NACOS_SERVER%/nacos/v1/cs/configs" ^
  -d "dataId=carbon-trade-flow-rules" ^
  -d "group=%GROUP_ID%" ^
  -d "content=[{\"resource\":\"/trade/order\",\"count\":40,\"grade\":1,\"limitApp\":\"default\",\"strategy\":0,\"controlBehavior\":0},{\"resource\":\"/trade/transaction\",\"count\":25,\"grade\":1,\"limitApp\":\"default\",\"strategy\":0,\"controlBehavior\":0}]"

echo ==========================================
echo Sentinel 规则配置完成!
echo 请访问 Nacos 控制台查看配置: http://%NACOS_SERVER%/nacos
echo 请访问 Sentinel 控制台查看规则: http://127.0.0.1:8080
echo ==========================================
pause
