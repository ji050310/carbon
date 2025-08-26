package com.carbon.assets.controller;

import cn.hutool.core.bean.BeanUtil;
import com.carbon.assets.service.CarbonQuotaAssetsService;
import com.carbon.assets.param.CarbonQuotaAssetsQueryParam;
import com.carbon.assets.vo.CarbonAssetsTotalVo;
import com.carbon.assets.vo.CarbonQuotaAssetsQueryVo;
import com.carbon.assets.entity.CarbonQuotaAssets;
import com.carbon.assets.common.BaseController;
import com.carbon.domain.chainmaker.api.ChainMakerServiceApi;
import com.carbon.domain.chainmaker.param.CarbonQuotaAssetsParam;
import com.carbon.domain.common.ApiResult;
import com.carbon.common.api.Paging;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;


/**
 * <p>
 * 碳配额资产 前端控制器
 * </p>
 *
 * @author Li Jun
 * @since 2022-04-24
 */
@Slf4j
@RestController
@RequestMapping("/carbonQuotaAssets")
@Api(value = "碳配额资产模块", tags = {"碳配额资产模块"})
public class CarbonQuotaAssetsController extends BaseController {

    @Autowired
    private CarbonQuotaAssetsService carbonQuotaAssetsService;

    @Autowired
    private ChainMakerServiceApi chainMakerServiceApi;

    /**
     * 添加碳配额资产
     */
    @PostMapping("/add")
    @ApiOperation(value = "添加碳配额资产",notes = "添加碳配额资产")
    public ApiResult<Boolean> addCarbonQuotaAssets(@Valid @RequestBody CarbonQuotaAssets carbonQuotaAssets) {
        carbonQuotaAssets.setAvailableAmount(carbonQuotaAssets.getTotal());
        //资产估值=总值*50
        carbonQuotaAssets.setValuation(carbonQuotaAssets.getTotal().multiply(new BigDecimal("50.00")));
        boolean flag = carbonQuotaAssetsService.save(carbonQuotaAssets);
        //发送mq飞书审批消息
        carbonQuotaAssetsService.SendFeishuApprove(carbonQuotaAssets);
        try {
            //长安链-上链
            chainMakerServiceApi.addQuotaAssets(BeanUtil.copyProperties(carbonQuotaAssets, CarbonQuotaAssetsParam.class));
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return ApiResult.result(flag);
    }

    /**
     * 修改碳配额资产
     */
    @PutMapping("/update")
    @ApiOperation(value = "修改碳配额资产",notes = "修改碳配额资产")
    public ApiResult<Boolean> updateCarbonQuotaAssets(@Valid @RequestBody CarbonQuotaAssets carbonQuotaAssets) {
        boolean flag = carbonQuotaAssetsService.updateById(carbonQuotaAssets);
        return ApiResult.result(flag);
    }

    /**
     * 获取碳配额资产
     */
    @GetMapping("/info/{id}")
    @ApiOperation(value = "碳配额资产-详情",notes = "查看碳配额资产")
    public ApiResult<CarbonQuotaAssetsQueryVo> getCarbonQuotaAssets(@PathVariable String id) {
        CarbonQuotaAssetsQueryVo carbonQuotaAssetsQueryVo = carbonQuotaAssetsService.getCarbonQuotaAssetsById(id);
        return ApiResult.ok(carbonQuotaAssetsQueryVo);
    }

    /**
     * 碳配额资产分页列表
     */
    @PostMapping("/getPageList")
    @ApiOperation(value = "碳配额资产-分页列表",notes = "碳配额资产分页列表")
    public ApiResult<Paging<CarbonQuotaAssetsQueryVo>> getCarbonQuotaAssetsPageList(@Valid @RequestBody(required = false) CarbonQuotaAssetsQueryParam carbonQuotaAssetsQueryParam) {
        Paging<CarbonQuotaAssetsQueryVo> paging = carbonQuotaAssetsService.getCarbonQuotaAssetsPageList(carbonQuotaAssetsQueryParam);
        return ApiResult.ok(paging);
    }

    @GetMapping("/assetsTotal")
    @ApiOperation(value = "碳配额资产-总计",notes = "碳配额资产-总计")
    public ApiResult<CarbonAssetsTotalVo> getCarbonAssetsTotal() {
        CarbonAssetsTotalVo vo = carbonQuotaAssetsService.getCarbonAssetsTotal();
        return ApiResult.ok(vo);
    }
}

