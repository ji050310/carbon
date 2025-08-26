package com.carbon.chainmaker.controller;

import com.alibaba.fastjson.JSONObject;
import com.carbon.chainmaker.common.BaseController;
import com.carbon.chainmaker.common.ChainMakerSdkManger;
import com.carbon.chainmaker.constants.ChainConstant;
import com.carbon.chainmaker.utils.CommUtils;
import com.carbon.domain.chainmaker.param.CarbonTradeContractParam;
import com.carbon.domain.chainmaker.vo.ChainMakerRtVO;
import com.carbon.domain.common.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 * 碳交易履约 前端控制器
 * </p>
 *
 * @author lin rizhao
 * @since 2022-05-21
 */
@Slf4j
@RestController
@RequestMapping("/carbonTrade")
@Api(value = "碳交易履约模块", tags = {"碳交易履约模块"})
public class CarbonTradeController extends BaseController {

    @Autowired
    private ChainMakerSdkManger chainMakerSdkManger;

    @PostMapping("/addContract")
    @ApiOperation(value = "交易履约上链",notes = "交易履约上链")
    public ApiResult<ChainMakerRtVO> addContract(@Valid @RequestBody(required = false) CarbonTradeContractParam param) {
        log.info("===> param: {}", JSONObject.toJSONString(param));
        return chainMakerSdkManger.invoke(ChainConstant.CHAINCODE_NAME,ChainConstant.FUNC_TRADE_CONTRACT,CommUtils.beanToMap(param),false);
    }

}

