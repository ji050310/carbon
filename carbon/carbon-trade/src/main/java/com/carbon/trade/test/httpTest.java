package com.carbon.trade.test;

import cn.hutool.http.Header;
import cn.hutool.http.HttpUtil;
import cn.hutool.http.Method;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class httpTest
{
    public static void main(String[] args) {
        String tenantToken = getTenantToken();
        String url="https://open.feishu.cn/open-apis/doc/v2/:"+"doxcnpwe92Absk4DE59AiGuejvt"+"/raw_content";
//        String url="https://open.feishu.cn/open-apis/doc/v2/"+"doxcnpwe92Absk4DE59AiGuejvt"+"/raw_content";
        String result = HttpUtil.createRequest(Method.GET, url).header(Header.AUTHORIZATION,tenantToken)
                .execute().body();
        JSONObject object1= JSON.parseObject(result);
        //元数据结果集
        JSONObject data = JSONObject.parseObject(object1.getString("data"));
        System.out.println(result);
    }




    public static String getTenantToken(){
        Map map=new HashMap();
        map.put("app_id","cli_a205c375b139d013");
        map.put("app_secret","qFh0OBTuR36KkLY0kAl7Rf3b4OSuE7ey");
        String url="https://open.feishu.cn/open-apis/auth/v3/tenant_access_token/internal";
        String tokenStr = HttpUtil.post(url, map);
        JSONObject object= JSON.parseObject(tokenStr);
        String token = object.getString("tenant_access_token");
        return "Bearer "+token;
    }
}


