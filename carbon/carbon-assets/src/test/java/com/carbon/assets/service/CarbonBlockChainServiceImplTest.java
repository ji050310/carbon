//package com.carbon.assets.service;
//
//import cn.hutool.http.HttpRequest;
//import cn.hutool.http.HttpResponse;
//import cn.hutool.http.HttpUtil;
//import cn.hutool.json.JSONUtil;
//import com.alibaba.fastjson.JSONObject;
////import com.carbon.assets.service.CarbonBlockChainService;
//import com.carbon.assets.vo.block.CarbonBlockExchangeInfo;
//import com.carbon.domain.block.param.AccountRegisterParam;
//import com.carbon.domain.block.param.CarbonAssetsTokenParam;
//import com.carbon.domain.block.param.CarbonQuotasAddParam;
//import com.carbon.domain.block.vo.BlockResponse;
//import com.carbon.domain.block.vo.CarbonAssetsToken;
//import com.carbon.domain.block.vo.CarbonAssetsTokenInfo;
//import com.google.gson.reflect.TypeToken;
//import lombok.Data;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.List;
//
//
//@Slf4j
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class CarbonBlockChainServiceImplTest {
//
//    @Autowired
//    private CarbonBlockChainService carbonBlockChainService;
//
//    @Test
//    public void registerAdmin(){
//        AccountRegisterParam registerParam = new AccountRegisterParam();
//        registerParam.setOrgName("xihu_taxi");
//        registerParam.setUserId("admin");
//        registerParam.setSign(true);
//        carbonBlockChainService.registerAdmin(registerParam);
//    }
//
//    @Test
//    public void addCarbonQuotas(){
//        CarbonQuotasAddParam param = new CarbonQuotasAddParam();
//        param.setTokenId("1");
//        param.setUserId("admin");
//        param.setOrgName("xihu_taxi");
//        param.setUtilityId("TANHUIBAO_CHINA_11208");
//        param.setPartyId("123");
//        param.setFromDate("2021-09-16 10:00:00");
//        param.setThruDate("2022-09-16 10:00:00");
//        param.setQuotaIssuedAmount(10);
//        param.setQuotaIssuedUom("MtCO2e");
//        param.setEmissionsDoc("a1840c9104205057607275dc804b7");
//        param.setSign(true);
//        carbonBlockChainService.addCarbonQuotas(param);
//    }
//
//    @Test
//    public void queryAssetsTokenList(){
//        CarbonAssetsTokenParam param = new CarbonAssetsTokenParam();
//        param.setUserId("admin");
//        param.setOrgName("xihu_taxi");
//
//        CarbonAssetsToken carbonAssetsToken = carbonBlockChainService.queryAssetsTokenList(param);
//        log.info("===={}",JSONUtil.toJsonPrettyStr(carbonAssetsToken));
//    }
//
//    @Test
//    public void queryAssetsTokenInfo(){
//        CarbonAssetsTokenParam param = new CarbonAssetsTokenParam();
//        param.setUserId("admin");
//        param.setOrgName("xihu_taxi");
//        param.setTokenId("634871d6ff7fa8c5a049109b32850c5c");
//
//        CarbonAssetsTokenInfo info = carbonBlockChainService.queryAssetsTokenInfo(param);
//        log.info("===={}",JSONUtil.toJsonPrettyStr(info));
//    }
//
//    @Test
//    public void getExchangeInfo(){
//        CarbonBlockExchangeInfo exchangeInfo = carbonBlockChainService.getExchangeInfo("2945a2edc85fa8148414490622895497e5e937efdc1ae7cb48721274e1c621af");
//        log.info("exchangeInfo==={}",JSONUtil.toJsonPrettyStr(exchangeInfo));
//    }
//
//}
