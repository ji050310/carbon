Write-Host "高频流控测试 - 每秒发送多个请求" -ForegroundColor Green
Write-Host "===================================" -ForegroundColor Green

Write-Host ""
Write-Host "快速发送20个请求 (每50ms一个)..." -ForegroundColor Yellow

$successCount = 0
$blockedCount = 0

for($i=1; $i -le 20; $i++) {
    Write-Host "请求 $i :" -NoNewline
    try {
        $response = Invoke-WebRequest -Uri "http://localhost:9001/authCenter/actuator/health" -Method Get -TimeoutSec 2
        Write-Host " ✅ 成功" -ForegroundColor Green
        $successCount++
    } catch {
        if ($_.Exception.Message -like "*429*") {
            Write-Host " 🚫 限流" -ForegroundColor Yellow
            $blockedCount++
        } else {
            Write-Host " ❌ 错误" -ForegroundColor Red
        }
    }
    Start-Sleep -Milliseconds 50  # 很快的请求频率
}

Write-Host ""
Write-Host "测试结果:" -ForegroundColor Cyan
Write-Host "成功请求: $successCount" -ForegroundColor Green  
Write-Host "被限流: $blockedCount" -ForegroundColor Yellow
Write-Host ""
Write-Host "现在去 Sentinel Dashboard 查看实时监控数据！" -ForegroundColor Green
