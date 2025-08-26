//package com.carbon.system.vo;
//
//import cn.hutool.http.Header;
//import cn.hutool.http.HttpUtil;
//import cn.hutool.http.Method;
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
//import com.carbon.system.service.impl.CarbonArticleServiceImpl;
//import com.carbon.system.util.CommonUtil;
//import org.apache.http.HttpEntity;
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClientBuilder;
//import org.apache.http.util.EntityUtils;
//
//import java.util.List;
//import java.util.Map;
//
//public class Test
//{
//    public static void main(String[] args) throws Exception{
//        String tenantToken = CarbonArticleServiceImpl.getTenantToken();
//        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
////        System.out.println(tenantToken);
////        String url="https://open.feishu.cn/open-apis/docx/v1/documents/doxcnPITgRLjBhIHv459q12QgFg";
//        String url="https://open.feishu.cn/open-apis/drive/v1/files";
////        String url="https://open.feishu.cn/open-apis/docx/v1/documents/doxcnpwe92Absk4DE59AiGuejvt/raw_content";
////        String url="https://open.feishu.cn/open-apis/doc/v2/doccnUfRamxOgqeMpW0o176py7u/raw_content";
////        String url="https://open.feishu.cn/open-apis/drive/v1/files?folder_token=fldcnDJ2u1Lh5F6eVEauSZouVsh";
//
//        HttpGet httpPost=new HttpGet(url);
//        httpPost.setHeader("Authorization",tenantToken);
//        CloseableHttpResponse response = httpClient.execute(httpPost);
//        HttpEntity responseEntity = response.getEntity();
//        String res=EntityUtils.toString(responseEntity);
//        System.out.println(res);
//        JSONObject object2= JSON.parseObject(res);
//        JSONObject data = JSONObject.parseObject(object2.getString("data"));
//        Map<String,String> m=JSONArray.parseObject(data.get("document").toString(),Map.class);
//        System.out.println(m.get("title"));
//        if (httpClient != null) {
//
//            httpClient.close();
//
//        }
//
//        if (response != null) {
//
//            response.close();
//        }
////        String result2 = HttpUtil.createRequest(Method.GET, url).
////                    header(Header.AUTHORIZATION, tenantToken).execute().body();
////        JSONObject object2= JSON.parseObject(result2);
////        JSONObject data = JSONObject.parseObject(object2.getString("data"));
////
////        Map<String,String> m=JSONArray.parseObject(data.get("document").toString(),Map.class);
////        System.out.println(m.get("title"));
//
//        //第二种方式
////        List<Map<String,String>> listObjectSec = JSONArray.parseObject(data.get("files").toString(),List.class);
////        System.out.println("利用JSONArray中的parseObject方法并指定返回类型来解析json数组字符串");
////        for(Map<String,String> mapList : listObjectSec){
////            for (Map.Entry entry : mapList.entrySet()){
//////                System.out.println( entry.getKey() + "  " +entry.getValue());
////                System.out.println(mapList.get("url"));
////            }
////        }
//
//
//    }
//}
