package com.carbon.system.util;

import cn.hutool.http.Header;
import cn.hutool.http.HttpUtil;
import cn.hutool.http.Method;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/*
* 通用工具类
* author：cbd
*/
public class CommonUtil


{
    //飞书地址前缀 替换地址获取id
    public static String URL="https://zfx2bso66l.feishu.cn/";
    public static String docsURL=URL+"docs";
    public static String fileURL=URL+"file";
    public static String docxURL=URL+"docx";
    public static void main(String[] args) throws Exception{
        String res=form("https://open.feishu.cn/open-apis/sheets/v2/spreadsheets/shtcniQ1veTlPFtisfnOKTPVPdg/values_append",
                "{\n" +
                        "  \"valueRange\": {\n" +
                        "    \"range\": \"34372d!A15:F15\",\n" +
                        "    \"values\": [\n" +
                        "      [\n" +
                        "        \"string\",\n" +
                        "        1,\n" +
                        "        \"https://www.xxx.com\"\n" +
                        "      ]\n" +
                        "    ]\n" +
                        "  }\n" +
                        "}"
                );
        System.out.println(res);
    }
    /*时间工具*/
    public static String getTime(Long timestamp)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
        Date time = new Date(timestamp * 1000L);
        String res = sdf.format(time);
        return res.replace(" 000","");
    }
    /*飞书文章url处理工具*/
    public static String getArticleToken(String s)
    {
        return s.replace("https://carbonmsger.feishu.cn/docs/","")
                .replace("https://carbonmsger.feishu.cn/file/","")
                .replace("https://carbonmsger.feishu.cn/docx/","")
                .replace(docsURL,"")
                .replace(fileURL,"")
                .replace(docxURL,"");
    }

    //shtcniQ1veTlPFtisfnOKTPVPdg
    //sheetId:34372d
    public static void form()
    {
        String tenantToken = getTenantToken();
        System.out.println("tenantToken:"+tenantToken);
        String url="https://open.feishu.cn/open-apis/sheets/v2/spreadsheets/shtcniQ1veTlPFtisfnOKTPVPdg/values_append";
        String result2 = HttpUtil.createRequest(Method.POST, url)
                .body("valueRange","{\n" +
                        "    \"range\": \"34372d!A15:F15\",\n" +
                        "    \"values\": [\n" +
                        "      [\n" +
                        "        \"string\",\n" +
                        "        1,\n" +
                        "        \"https://www.xxx.com\"\n" +
                        "      ]\n" +
                        "    ]\n" +
                        "  }\n" +
                        "}")
                .header(Header.AUTHORIZATION, tenantToken)
                .execute().body();
        JSONObject object2= JSON.parseObject(result2);
        JSONObject data = JSONObject.parseObject(object2.getString("data"));
        System.out.println(object2);
    }

    public static String form(String url,String json) throws Exception{

        StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);//设置消息头 Content-Type application/json; charset=UTF-8
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Authorization",getTenantToken());
        httpPost.setEntity(entity);
        CloseableHttpResponse response2 = HttpClients.createDefault().execute(httpPost);

        try {

            response2.getStatusLine().getStatusCode();//HttpStatus.SC_OK

            HttpEntity entity2 = response2.getEntity();
            String response= EntityUtils.toString(entity2,"utf-8");//返回报文

            EntityUtils.consume(entity2);//关闭资源
            return response;
        } finally {
            response2.close();//关闭资源
        }
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

    public static String getTitle()
    {
        String tenantToken = getTenantToken();
        System.out.println(tenantToken);
        String url="https://open.feishu.cn/open-apis/docx/v1/documents/doxcnPITgRLjBhIHv459q12QgFg";

        String result2 = HttpUtil.createRequest(Method.GET, url).
                header(Header.AUTHORIZATION, tenantToken).execute().body();
        JSONObject object2= JSON.parseObject(result2);
        JSONObject data = JSONObject.parseObject(object2.getString("data"));

        Map<String,String> m=JSONArray.parseObject(data.get("document").toString(),Map.class);
        return m.get("title");
    }
}
