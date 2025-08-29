# Sentinel Handler 放置与约定指南

目的
- 说明项目中如何组织 Sentinel 的 `blockHandler` / `fallback` 实现。
- 统一命名、签名与放置位置，降低耦合，便于维护与扩展。

适用背景
- 本仓库已采用 `@SentinelResource` 在控制器层进行方法级流控/降级。
- 采用“通用 + 模块化”策略：通用 fallback 放到 `common-model`，模块级的 `blockHandler` 放到对应模块内。

文件与包约定
- 通用 fallback（必备）
  - 位置：`carbon-frame/carbon-common-model/src/main/java/com/carbon/domain/sentinel/CommonSentinelHandler.java`
  - 包名：`com.carbon.domain.sentinel`
  - 作用：提供项目通用的 `<T> ApiResult<T> fallback(Throwable ex)` 等通用兜底逻辑与可复用方法。

- 模块级 blockHandler（按模块维护、按需精确化）
  - 位置示例：
    - system: `carbon-system/src/main/java/com/carbon/system/sentinel/SentinelFallbackHandler.java`（或 `SystemSentinelHandler`）
    - auth: `carbon-auth/src/main/java/com/carbon/auth/sentinel/AuthSentinelHandler.java`
    - assets: `carbon-assets/src/main/java/com/carbon/assets/sentinel/AssetsSentinelHandler.java`
    - trade: `carbon-trade/src/main/java/com/carbon/trade/sentinel/TradeSentinelHandler.java`
  - 包名：`com.carbon.<module>.sentinel`
  - 作用：实现与该模块控制器方法签名严格匹配的 `blockHandler`（返回合适的空集合/分页/错误信息等）。

为什么采用“通用 + 模块化”
- 通用 fallback：统一响应格式、减少重复、快速覆盖大多数错误场景。
- 模块级 blockHandler：能返回合理的领域型兜底数据（例如空 `Paging<T>`、空 List、缓存值），并能访问模块私有类型与缓存，避免把所有领域类型暴露到 common。

Sentinel 方法签名约定（必须严格）
- 方法可见性：推荐 `public static`。
- blockHandler 签名：原方法参数列表 + 最后一个参数 `BlockException`；返回类型必须与原方法一致。
  - 例如：
    - 控制器：`ApiResult<Paging<Foo>> list(QueryParam p)`
    - blockHandler：`public static ApiResult<Paging<Foo>> handleList(QueryParam p, BlockException ex)`
- fallback 签名：原方法参数列表 + 可选最后一个参数 `Throwable`（建议写上）；返回类型必须与原方法一致。
  - 例如：`public static ApiResult<Paging<Foo>> fallbackForList(QueryParam p, Throwable ex)`
- 如果原方法没有参数，blockHandler 仍需包含 `BlockException`（签名为 `(..., BlockException ex)`）或只有 `BlockException` 若无其它参数。

命名与组织建议
- 模块级 class 名：`<Module>SentinelHandler` 或 `SentinelFallbackHandler`（保持团队一致）。
- blockHandler 命名格式：`handle<PascalMethodName>Block` 或 `handleGetXxxBlock`。
- fallback 命名格式：`handleFallbackFor<PascalMethodName>`。
- 在 `@SentinelResource` 中：
  - `blockHandlerClass = <Module>SentinelHandler.class`
  - `blockHandler = "<方法名>"`
  - `fallbackClass = com.carbon.domain.sentinel.CommonSentinelHandler.class`（通用）
  - `fallback = "fallback"` 或 指向模块级的精确 fallback 名称

示例（控制器注解 + handler）

控制器（示例）
```java
@PostMapping("/getPageList")
@SentinelResource(value = "getFooPageList",
    blockHandler = "handleGetFooPageList",
    blockHandlerClass = FooSentinelHandler.class,
    fallback = "fallback",
    fallbackClass = com.carbon.domain.sentinel.CommonSentinelHandler.class)
public ApiResult<Paging<FooVo>> getFooPageList(@RequestBody FooQueryParam param) { ... }
```

模块级 handler（示例）
```java
package com.carbon.foo.sentinel;

public class FooSentinelHandler {
    public static ApiResult<Paging<FooVo>> handleGetFooPageList(FooQueryParam param, BlockException ex) {
        Page<FooVo> empty = new Page<>();
        return ApiResult.ok(new Paging<>(empty));
    }
}
```

通用 fallback（示例）
```java
package com.carbon.domain.sentinel;

public class CommonSentinelHandler {
    public static <T> ApiResult<T> fallback(Throwable ex) {
        // 统一日志与友好消息
        return ApiResult.fail("服务不可用，请稍后重试");
    }
}
```

实践建议与注意事项
- 严格匹配签名：常见编译错误来自签名不匹配（参数类型、参数个数或返回类型不一致）。
- 尽量把通用的“空分页/空列表/统一错误格式”放在 common，复杂的领域逻辑放在模块级 handler。
- 如果某模块需要访问模块私有服务（缓存、DB），可以把 handler 写在模块内并在实现中调用模块服务（注意 handler 必须是静态方法或使用指定的 bean 方案）。
- 文档化：每次新增 `@SentinelResource` 时，请同时在模块的 sentinel 包中添加对应的 handler stub（IDE 可生成）。

如何本地验证（快速）
- 编译并安装 common-model（确保 `CommonSentinelHandler` 在本地仓库）：

```powershell
cd e:\carbon-main\carbon-main\carbon\carbon-frame\carbon-common-model
mvn -DskipTests install
```

- 编译某个模块，例如 `carbon-assets`：

```powershell
cd e:\carbon-main\carbon-main\carbon\carbon-assets
mvn -DskipTests package
```

- 手动触发：用 Postman 或脚本请求控制器方法并触发限流/异常以验证 `blockHandler` / `fallback` 的返回。

下一步建议
- 我可以为当前所有已注解的 `@SentinelResource` 自动生成一份对照表（控制器方法 -> 推荐 handler 签名），并把 handler stub 写入对应模块。该任务可自动完成并通过编译验证。

文件位置
- 本文件：`carbon-frame/SENTINEL_HANDLER_GUIDE.md`

---
生成于项目源码审查后，若需调整命名规范（例如统一 handler 类名或方法前缀），我可以按团队偏好批量重命名并更新所有 `@SentinelResource` 注解。
