package com.carbon.assets;

import cn.hutool.json.JSONUtil;
import com.carbon.chainmaker.ChainMakerApplication;
import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;
import org.chainmaker.sdk.ChainClient;
import org.chainmaker.sdk.ResponseInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.HashMap;
import java.util.Map;


@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ChainMakerApplication.class)
public class CarbonChainMakerTest extends TestCase {

    private static final String CONTRACT_NAME = "xcarbon_fact";

    @Autowired
    private ChainClient chainClient;


    @Test
    public void test() {
        ResponseInfo responseInfo;

        Map<String, String> params = new HashMap<>();
        params.put("userId", "777");
        params.put("orgName", "xcarbonchain1");
        params.put("affiliation", "abc");

        try {
            responseInfo = chainClient.invokeContract(CONTRACT_NAME, "test2", params, 10000, 10000);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        log.info(JSONUtil.toJsonPrettyStr(responseInfo));
    }

}