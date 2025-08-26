package com.carbon.chainmaker.controller;


import com.carbon.chainmaker.common.BaseController;
import com.carbon.chainmaker.common.ChainMakerSdkManger;
import com.carbon.chainmaker.constants.ChainConstant;
import com.carbon.domain.chainmaker.vo.TransactionRtVO;
import com.carbon.domain.common.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
@RequestMapping("/chainQuery")
@Api(value = "区块链查询模块", tags = {"区块链查询模块"})
public class ChianQueryController extends BaseController {

    @Autowired
    private ChainMakerSdkManger chainMakerSdkManger;

    @PostMapping("/queryInfoByMethod")
    @ApiOperation(value = "查询合约接口",notes = "根据TxId查询交易信息")
    public ApiResult<String> queryInfoByMethod(@RequestParam("method") String method, @RequestBody Map<String,String> map) {
        return chainMakerSdkManger.query(ChainConstant.CHAINCODE_NAME,method,map );
    }


    @PostMapping("/queryTxByTxId")
    @ApiOperation(value = "根据TxId查询交易信息",notes = "根据TxId查询交易信息")
    public ApiResult<TransactionRtVO> queryTxByTxId(@RequestParam(value = "txId",required = true) String txId) {
        return chainMakerSdkManger.getTxByTxId(txId);
    }
}
