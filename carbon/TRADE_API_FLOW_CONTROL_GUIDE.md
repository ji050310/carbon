## 为特定方法 /trade/carbonTradePrice/getPageList 配置限流

### 方法1：在 Sentinel Dashboard 中配置

1. **访问 Dashboard**：http://localhost:8080
2. **选择应用**：在左侧应用列表中选择 `carbon-trade`
3. **查看资源**：
   - 点击"簇点链路"或"实时监控"
   - 找到资源名：`/trade/carbonTradePrice/getPageList`
4. **添加流控规则**：
   - 点击该资源右侧的"流控"按钮
   - 或者进入"流控规则" -> "新增规则"
   - 配置：
     ```
     资源名: /trade/carbonTradePrice/getPageList
     阈值类型: QPS
     单机阈值: 3 (每秒最多3个请求)
     流控效果: 快速失败
     ```

### 方法2：在代码中使用注解（更精确控制）

如果要更精确地控制某个具体方法，可以在 Controller 方法上添加 `@SentinelResource` 注解：

```java
@RestController
@RequestMapping("/trade/carbonTradePrice")
public class CarbonTradePriceController {
    
    @GetMapping("/getPageList")
    @SentinelResource(
        value = "getPageList", // 自定义资源名
        blockHandler = "getPageListBlocked" // 限流时的降级方法
    )
    public ApiResult getPageList() {
        // 业务逻辑
        return ApiResult.success();
    }
    
    // 限流降级方法
    public ApiResult getPageListBlocked(BlockException ex) {
        return ApiResult.fail("系统繁忙，请稍后重试");
    }
}
```

### 方法3：网关级别限流

如果要在网关层面对该接口限流：

1. 在 Sentinel Dashboard 选择 `carbon-gate` 应用
2. 配置 API 分组：
   ```
   API名称: trade_api
   匹配规则: /trade/**
   ```
3. 为 API 分组配置流控规则

### 测试流控效果

运行以下测试脚本：
