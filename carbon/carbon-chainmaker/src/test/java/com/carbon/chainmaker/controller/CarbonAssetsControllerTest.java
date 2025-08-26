package com.carbon.chainmaker.controller;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import com.carbon.chainmaker.ChainMakerApplication;
import com.carbon.chainmaker.common.ChainMakerSdkManger;
import com.carbon.chainmaker.constants.ChainConstant;
import com.carbon.chainmaker.utils.CommUtils;
import com.carbon.domain.chainmaker.param.CarbonCreditAssetsParam;
import com.carbon.domain.chainmaker.param.CarbonQuotaAssetsParam;
import com.carbon.domain.chainmaker.param.CarbonTradeContractParam;
import com.carbon.domain.chainmaker.param.ChainBaseParam;
import com.carbon.domain.chainmaker.vo.ChainMakerRtVO;
import com.carbon.domain.common.ApiResult;
import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ChainMakerApplication.class)
public class CarbonAssetsControllerTest extends TestCase {
    @Autowired
    private ChainMakerSdkManger chainMakerSdkManger;
    @Test
    public void testAddQuotaAssets() {
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



        String userId  = "123";
        String org_name = "xcarbonchain1";
        String utility_id = "utility_id";
        String party_id = "party_id";
        String from_date = DateUtil.now() ;
        String thru_date = DateUtil.now() ;
        String quota_issued_amount = "10";
        String quota_issued_uom = "1";

        Map<String,String> map = new HashMap<>();
        map.put("tokenId",UUID.randomUUID().toString());
        map.put("userId",userId);
        map.put("orgName",org_name);
        map.put("utilityId",utility_id);
        map.put("partyId",party_id);
        map.put("fromDate",from_date);
        map.put("thruDate",thru_date);
        map.put("quotaIssuedAmount",quota_issued_amount);
        map.put("quotaIssuedUom",quota_issued_uom);

        ApiResult<ChainMakerRtVO> invoke = chainMakerSdkManger.invoke(ChainConstant.CHAINCODE_NAME, ChainConstant.FUNC_QUOTAS, map, false);
        log.info("===> {}", JSONObject.toJSONString(invoke));
    }

    public void testAddCreditAssets() {
    }


    /**
     *
     */
    @Test
    public void queryTokenInfo() {
        ChainBaseParam param = new ChainBaseParam();
        param.setTokenId("acf0fc50-63aa-4bec-bcd9-82f1c2f1ae0d");
        param.setUserId("123");
        param.setOrgName("xcarbonchain1");
        ApiResult<ChainMakerRtVO> invoke = chainMakerSdkManger.invoke(ChainConstant.CHAINCODE_NAME,ChainConstant.QUERY_TOKEN_INFO , CommUtils.beanToMap(param), true);


    }


    @Test
    public void queryContract() {
        ChainBaseParam param = new ChainBaseParam();
//        param.setTokenId("acf0fc50-63aa-4bec-bcd9-82f1c2f1ae0d");
//        param.setUserId("123");
//        param.setOrgName("xcarbonchain1");
        ApiResult<String> query = chainMakerSdkManger.query(ChainConstant.CHAINCODE_NAME, ChainConstant.QUERY_TOKEN_INFO,CommUtils.beanToMap(param));
    }


    @Test
    public void queryByTxId() {
//        ChainBaseParam param = new ChainBaseParam();
//        param.setTokenId("acf0fc50-63aa-4bec-bcd9-82f1c2f1ae0d");
//        param.setUserId("123");
//        param.setOrgName("xcarbonchain1");
        ApiResult query = chainMakerSdkManger.getTxByTxId("fa4847cdcd714b707cbeafcc3d75e8cbfaf47e2b4348f7c3a286a4218872c07a");
    }


    /**
     *
     */
    @Test
    public void regiterAdmin() {
        ChainBaseParam param = new ChainBaseParam();
//        param.setTokenId("0fd38a2c-4745-41bd-b0ae-49b6f6a1635c");
        param.setUserId("admin");
        param.setOrgName("carbon");
        ApiResult<ChainMakerRtVO> invoke = chainMakerSdkManger.invoke(ChainConstant.CHAINCODE_NAME,"registerAdmin" , CommUtils.beanToMap(param), false);

    }

    /**
     *
     */
    @Test
    public void testAddCredit(){
        CarbonCreditAssetsParam param = new CarbonCreditAssetsParam();
        param.setCarbonProjectId(110L);
        Map<String, String> map = CommUtils.beanToMap(param);
        map.put("tokenId", UUID.randomUUID().toString());
        map.put("userId",param.getUserId());
        map.put("orgName",param.getOrgName());
        ApiResult<ChainMakerRtVO> invoke = chainMakerSdkManger.invoke(ChainConstant.CHAINCODE_NAME, ChainConstant.FUNC_CREDIT, map, false);

    }

    /**
     *
     */
    @Test
    public void addContract(){
        CarbonTradeContractParam param = new CarbonTradeContractParam();
        param.setBuyerEmail("1600356021@qq.com");
        ApiResult<ChainMakerRtVO> invoke = chainMakerSdkManger.invoke(ChainConstant.CHAINCODE_NAME, ChainConstant.FUNC_TRADE_CONTRACT, CommUtils.beanToMap(param), false);

    }
}