package com.carbon.assets;

import cn.hutool.http.Header;
import cn.hutool.http.HttpUtil;
import cn.hutool.http.Method;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test
{
    public static void main(String[] args) throws Exception {
        System.out.println(getTenantToken());
    }

    public static List<Map<String,String>> geturl()
    {
        String tenantToken = getTenantToken();
        String url="https://open.feishu.cn/open-apis/drive/v1/files?folder_token=fldcnDJ2u1Lh5F6eVEauSZouVsh";
        String result2 = HttpUtil.createRequest(Method.GET, url).
                header(Header.AUTHORIZATION, tenantToken).execute().body();
        JSONObject object2= JSON.parseObject(result2);
        JSONObject data = JSONObject.parseObject(object2.getString("data"));

        //获取url
        List<Map<String,String>> listObjectSec = JSONArray.parseObject(data.get("files").toString(),List.class);
        return listObjectSec;
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
