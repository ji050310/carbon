package com.carbon.domain.sentinel;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.carbon.domain.common.ApiResult;
import com.carbon.domain.system.param.ChangePasswordParam;
import com.carbon.domain.system.param.SysAccountParam;
import com.carbon.domain.system.vo.SysAccountModelVo;
import com.carbon.domain.system.vo.SysTenantModelVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;

/**
 * 通用的 Sentinel 处理器，提供静态的 blockHandler/fallback 方法供 @SentinelResource 引用。
 */
public class CommonSentinelHandler {
    private static final Logger log = LoggerFactory.getLogger(CommonSentinelHandler.class);

    // 示例：被限流时对 SystemServiceApi 中 list 返回兜底数据
    public static ApiResult<List<SysAccountModelVo>> handleGetAccountListBlock(Throwable ex) {
        log.warn("Sentinel block handler for getAccountList: {}", ex == null ? "null" : ex.toString());
        return ApiResult.ok(Collections.emptyList());
    }

    public static ApiResult<List<SysTenantModelVo>> handleGetTenantListBlock(Throwable ex) {
        log.warn("Sentinel block handler for getTenantList: {}", ex == null ? "null" : ex.toString());
        return ApiResult.ok(Collections.emptyList());
    }

    // 通用 fallback，用于服务异常时返回默认失败响应
    public static <T> ApiResult<T> fallback(Throwable ex) {
        log.error("Sentinel fallback invoked: {}", ex == null ? "null" : ex.toString());
        return ApiResult.fail("服务不可用，请稍后重试");
    }
}
