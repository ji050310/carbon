Write-Host "Sentinel 流控测试" -ForegroundColor Green
Write-Host "===============" -ForegroundColor Green

Write-Host ""
Write-Host "开始发送10个请求测试流控..." -ForegroundColor Yellow

for($i=1; $i -le 10; $i++) {
    Write-Host "请求 $i :" -NoNewline
    try {
        $response = Invoke-WebRequest -Uri "http://localhost:9001/authCenter/actuator/health" -Method Get
        Write-Host " ✅ 成功 (状态码: $($response.StatusCode))" -ForegroundColor Green
    } catch {
        if ($_.Exception.Message -like "*429*") {
            Write-Host " 🚫 被限流 (429 Too Many Requests)" -ForegroundColor Yellow
        } else {
            Write-Host " ❌ 失败: $($_.Exception.Message)" -ForegroundColor Red
        }
    }
    Start-Sleep -Milliseconds 100
}

Write-Host ""
Write-Host "测试完成！请在 Sentinel Dashboard 查看监控数据" -ForegroundColor Green
