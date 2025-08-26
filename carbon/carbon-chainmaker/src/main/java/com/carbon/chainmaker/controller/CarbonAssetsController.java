package com.carbon.chainmaker.controller;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import com.carbon.chainmaker.common.BaseController;
import com.carbon.chainmaker.common.ChainMakerSdkManger;
import com.carbon.chainmaker.constants.ChainConstant;
import com.carbon.chainmaker.utils.CommUtils;
import com.carbon.domain.block.param.CarbonBusinessParam;
import com.carbon.domain.block.param.CarbonCertifiedParam;
import com.carbon.domain.block.param.CarbonOffsetAddParam;
import com.carbon.domain.chainmaker.param.CarbonCreditAssetsParam;
import com.carbon.domain.chainmaker.param.CarbonQuotaAssetsParam;
import com.carbon.domain.chainmaker.vo.ChainMakerRtVO;
import com.carbon.domain.common.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

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
@RequestMapping("/carbonAssets")
@Api(value = "碳交易履约模块", tags = {"碳交易履约模块"})
public class CarbonAssetsController extends BaseController {

    @Autowired
    private ChainMakerSdkManger chainMakerSdkManger;

    @PostMapping("/addQuotaAssets")
    @ApiOperation(value = "碳配额资产上链",notes = "碳配额资产上链")
    public ApiResult<ChainMakerRtVO> addQuotaAssets(@Valid @RequestBody(required = false) CarbonQuotaAssetsParam param) {
        /**
         *     let token_id = ctx.arg_default_blank("tokenId");//业务数据id
         *     let user_id = ctx.arg_default_blank("userId");//用户账户id
         *     let org_name = ctx.arg_default_blank("orgName");//机构名称
         *     let utility_id = ctx.arg_default_blank("utilityId");//碳资产上链渠道ID
         *     let party_id = ctx.arg_default_blank("partyId");//买方id
         *     let from_date = ctx.arg_default_blank("fromDate");//开始时间
         *     let thru_date = ctx.arg_default_blank("thruDate");//结束时间
         *     let quota_issued_amount = ctx.arg_default_blank("quotaIssuedAmount");//数量
         *     let quota_issued_uom = ctx.arg_default_blank("quotaIssuedUom");//单位
         *     let emissions_doc = ctx.arg_default_blank("emissionsDoc");//文档文件hash
         */
        param.setTokenId(UUID.randomUUID().toString());
        log.info(" ==> param:{}", JSONObject.toJSONString(param));
        Map<String, String> map = CommUtils.beanToMap(param);
        map.put("utilityId",param.getCarbonExchangeId()==null ? "utilityId":param.getCarbonExchangeId().toString() );
        map.put("partyId", param.getTenantId()==null ? "partyId" :param.getTenantId().toString());
        map.put("fromDate",DateUtil.formatDate(param.getBuyDate()== null ? new Date() : param.getBuyDate()) );
        map.put("thruDate",DateUtil.formatDate(param.getExpiryDate()==null ? new Date() : param.getExpiryDate() ));

        if(param.getBuyTotalPrice() != null && param.getBuyUnitPrice()!=null){
            map.put("quotaIssuedAmount",String.valueOf (param.getBuyTotalPrice().intValue()/ param.getBuyUnitPrice().intValue()) );
        }else {
            map.put("quotaIssuedAmount","quotaIssuedAmount");
        }
        map.put("quotaIssuedUom", param.getBuyUnitPrice()==null?"quotaIssuedUom": param.getBuyUnitPrice().toString());
        return chainMakerSdkManger.invoke(ChainConstant.CHAINCODE_NAME,ChainConstant.FUNC_QUOTAS,map,false);
    }

    @PostMapping("/addCreditAssets")
    @ApiOperation(value = "碳信用资产上链",notes = "碳信用资产上链")
    public ApiResult<ChainMakerRtVO> addCreditAssets(@Valid @RequestBody(required = false) CarbonCreditAssetsParam param) {
        param.setTokenId(UUID.randomUUID().toString());
        log.info("===> param: {}",JSONObject.toJSONString(param));
        return chainMakerSdkManger.invoke(ChainConstant.CHAINCODE_NAME,ChainConstant.FUNC_CREDIT,CommUtils.beanToMap(param),false);
    }

    @PostMapping("/addCarbonOffset")
    @ApiOperation(value = "碳减排资产上链",notes = "碳减排资产上链")
    public ApiResult<ChainMakerRtVO> AddCarbonOffset( @RequestBody Map<String,String>  param) {
        log.info("===> param: {}",JSONObject.toJSONString(param));
        return chainMakerSdkManger.invoke(ChainConstant.CHAINCODE_NAME,ChainConstant.FUNC_OFFSET,param,false);
    }


    @PostMapping("/addAssetsCertified")
    @ApiOperation(value = "中和资产核证上链",notes = "中和资产核证上链")
    public ApiResult<ChainMakerRtVO> addAssetsCertified(@Valid @RequestBody(required = false) CarbonCertifiedParam param) {
        return chainMakerSdkManger.invoke(ChainConstant.CHAINCODE_NAME,ChainConstant.ASSETS_CERTIFIED,CommUtils.beanToMap(param),false);
    }


    @PostMapping("/assetsBusiness")
    @ApiOperation(value = "中和资产交易上链",notes = "中和资产交易上链")
    public ApiResult<ChainMakerRtVO> addAssetsBusiness(@Valid @RequestBody(required = false) CarbonBusinessParam param) {
        return chainMakerSdkManger.invoke(ChainConstant.CHAINCODE_NAME,ChainConstant.ASSETS_BUSINESS,CommUtils.beanToMap(param),false);
    }

}

