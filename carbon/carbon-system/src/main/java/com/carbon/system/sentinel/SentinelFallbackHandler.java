package com.carbon.system.sentinel;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.carbon.common.api.Paging;
import com.carbon.system.param.CarbonArticleQueryParam;
import com.carbon.system.vo.CarbonArticleQueryVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.carbon.domain.common.ApiResult;
import java.util.List;
import java.util.Collections;

/**
 * 统一的 Sentinel 降级/限流处理器。
 * 静态方法用于在 @SentinelResource 中被引用（blockHandlerClass / fallbackClass）。
 */
public class SentinelFallbackHandler {
    private static final Logger log = LoggerFactory.getLogger(SentinelFallbackHandler.class);

    // blockHandler：处理 Sentinel 限流/拒绝的情况
    public static Paging<CarbonArticleQueryVo> handleBlockForCarbonArticle(CarbonArticleQueryParam param, BlockException ex) {
        log.warn("Sentinel block handler invoked for resource carbonArticlePageList: {}", ex == null ? "null" : ex.toString());
        Page<CarbonArticleQueryVo> empty = new Page<>();
        return new Paging<>(empty);
    }

    // fallback：处理方法执行抛异常时的兜底逻辑
    public static Paging<CarbonArticleQueryVo> handleFallbackForCarbonArticle(CarbonArticleQueryParam param, Throwable ex) {
        log.error("Sentinel fallback invoked for resource carbonArticlePageList, reason: {}", ex == null ? "null" : ex.toString());
        Page<CarbonArticleQueryVo> empty = new Page<>();
        return new Paging<>(empty);
    }

    // Account list blockHandler/fallback for SysAccountController.getList()
    public static ApiResult<List<com.carbon.domain.system.vo.SysAccountModelVo>> handleGetAccountListBlock(BlockException ex) {
        log.warn("Sentinel block handler invoked for resource getAccountList: {}", ex == null ? "null" : ex.toString());
        return ApiResult.ok(java.util.Collections.emptyList());
    }

    public static ApiResult<List<com.carbon.domain.system.vo.SysAccountModelVo>> handleFallbackForAccount(Throwable ex) {
        log.error("Sentinel fallback invoked for resource getAccountList, reason: {}", ex == null ? "null" : ex.toString());
        return ApiResult.fail("服务不可用，请稍后重试");
    }

    // Tenant list blockHandler/fallback for SysTenantController.getTenantList()
    public static ApiResult<List<com.carbon.domain.system.vo.SysTenantModelVo>> handleGetTenantListBlock(BlockException ex) {
        log.warn("Sentinel block handler invoked for resource getTenantList: {}", ex == null ? "null" : ex.toString());
        return ApiResult.ok(java.util.Collections.emptyList());
    }

    public static ApiResult<List<com.carbon.domain.system.vo.SysTenantModelVo>> handleFallbackForTenant(Throwable ex) {
        log.error("Sentinel fallback invoked for resource getTenantList, reason: {}", ex == null ? "null" : ex.toString());
        return ApiResult.fail("服务不可用，请稍后重试");
    }
}
