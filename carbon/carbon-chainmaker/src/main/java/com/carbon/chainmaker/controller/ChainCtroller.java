package com.carbon.chainmaker.controller;

import com.alibaba.fastjson.JSONObject;
import com.carbon.chainmaker.common.BaseController;
import com.carbon.chainmaker.common.ChainMakerSdkManger;
import com.carbon.chainmaker.constants.ChainConstant;
import com.carbon.domain.chainmaker.vo.ChainMakerRtVO;
import com.carbon.domain.common.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 长安链交易账号 前端控制器
 * </p>
 *
 * @author lin rizhao
 * @since 2022-05-21
 */
@Slf4j
@RestController
@Api(value = "区块链查询模块", tags = {"区块链查询模块"})
public class ChainCtroller extends BaseController {


    @Autowired
    private ChainMakerSdkManger chainMakerSdkManger;

    /**
     * 项目库数据上链
     */
    @PostMapping("/addCarbonMetaregistry")
    @ApiOperation(value = "项目库数据区块链上链",notes = "项目库数据区块链上链")
    public ApiResult<ChainMakerRtVO> addCarbonMetaregistry(@RequestBody Map<String,String> map) {
        log.info("===> param: {}", JSONObject.toJSONString(map));
        return chainMakerSdkManger.invoke(ChainConstant.CHAINCODE_NAME,ChainConstant.FUNC_CARBON_METAREGISTRY,map ,false);
    }


    /**
     * 注册管理员
     */
    @PostMapping("/registerAdmin")
    @ApiOperation(value = "注册成为管理员",notes = "注册成为管理员")
    public ApiResult<ChainMakerRtVO> registerAdmin(@RequestBody Map<String,String> map) {
        log.info("===> param: {}", JSONObject.toJSONString(map));
        return chainMakerSdkManger.invoke(ChainConstant.CHAINCODE_NAME,ChainConstant.FUNC_REGISTER_ADMIN,map ,false);
    }

    /**
     * 注册普通用户
     */
    @PostMapping("/registerExchangeAccount")
    @ApiOperation(value = "注册成为交易员",notes = "注册成为交易员")
    public ApiResult<ChainMakerRtVO> queryInfoByMethod(@RequestBody Map<String,String> map) {
        log.info("===> param: {}", JSONObject.toJSONString(map));
        return chainMakerSdkManger.invoke(ChainConstant.CHAINCODE_NAME,ChainConstant.FUNC_REGISTER_EXCHANGEACCOUNT,map ,false);
    }
}
