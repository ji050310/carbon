package com.carbon.assets.sentinel;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.carbon.common.api.Paging;
import com.carbon.assets.vo.CarbonMethodologyQueryVo;
import com.carbon.domain.common.ApiResult;
import com.carbon.assets.param.CarbonMethodologyQueryParam;
import com.carbon.assets.vo.CarbonMethodologySelectVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;

public class AssetsSentinelHandler {
    private static final Logger log = LoggerFactory.getLogger(AssetsSentinelHandler.class);

    public static Paging<CarbonMethodologyQueryVo> handleGetCarbonMethodologyPageList(Object param, BlockException ex) {
        log.warn("Assets block handler: {}", ex == null ? "null" : ex.toString());
    Page<CarbonMethodologyQueryVo> empty = new Page<>();
    return new Paging<>(empty);
    }

    // Precise blockHandler matching controller: getCarbonMethodologyPageList(@RequestBody CarbonMethodologyQueryParam)
    public static ApiResult<Paging<CarbonMethodologyQueryVo>> handleGetCarbonMethodologyPageList(CarbonMethodologyQueryParam param, BlockException ex) {
        log.warn("Assets block handler for getCarbonMethodologyPageList: {}", ex == null ? "null" : ex.toString());
        Page<CarbonMethodologyQueryVo> empty = new Page<>();
        return ApiResult.ok(new Paging<>(empty));
    }

    // Precise blockHandler matching controller: getCarbonMethodologyList()
    public static ApiResult<List<CarbonMethodologySelectVo>> handleGetCarbonMethodologyList(BlockException ex) {
        log.warn("Assets list block handler: {}", ex == null ? "null" : ex.toString());
        return ApiResult.ok(Collections.emptyList());
    }
}
