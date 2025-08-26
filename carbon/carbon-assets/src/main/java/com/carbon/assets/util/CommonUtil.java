package com.carbon.assets.util;

import cn.hutool.http.Header;
import cn.hutool.http.HttpUtil;
import cn.hutool.http.Method;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.carbon.assets.entity.CarbonMethodologyContent;
import org.elasticsearch.client.HeapBufferedAsyncResponseConsumer;
import org.elasticsearch.client.HttpAsyncResponseConsumerFactory;
import org.elasticsearch.client.RequestOptions;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
* 通用工具类
* author：cbd
*/
public class CommonUtil
{
    public static void main(String[] args) {
        List<Map<String,String>> l=geturl("fldcnX1FU4Du2olA0iUWYuiT4vR","");
        String title=l.get(0).get("name");
        System.out.println(l.get(0).get("name"));
        System.out.println(getType(title));
        System.out.println(getRef(title));
    }
    /*时间工具*/
    public static String getTime(Long timestamp)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
        Date time = new Date(timestamp * 1000L);
        String res = sdf.format(time);
        return res.replace(" 000","");
    }
    /*飞书文章url处理工具--获取飞书文章token*/
    public static String getArticleToken(String s)
    {
        return s.replace("https://carbonmsger.feishu.cn/docs/","")
                .replace("https://carbonmsger.feishu.cn/file/","")
                .replace("https://carbonmsger.feishu.cn/docx/","");
    }

    /*获取飞书文章url*/
    public static List<Map<String,String>> geturl(String token,String nextPageToken)
    {
        String tenantToken = getTenantToken();
        String url="https://open.feishu.cn/open-apis/drive/v1/files?" +
                "page_size=200&folder_token="+token;
        if(nextPageToken!="")
        {
            url+="&page_token="+nextPageToken;
        }

        String result2 = HttpUtil.createRequest(Method.GET, url).
                header(Header.AUTHORIZATION, tenantToken).execute().body();
        JSONObject object2= JSON.parseObject(result2);
        JSONObject data = JSONObject.parseObject(object2.getString("data"));

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

    /*获取飞书文章标题*/
    public static String getTitle(String docToken)
    {
        String tenantToken = getTenantToken();
        String url="https://open.feishu.cn/open-apis/docx/v1/documents/"+docToken;
        String result2 = HttpUtil.createRequest(Method.GET, url).
                header(Header.AUTHORIZATION, tenantToken).execute().body();
        JSONObject object2= JSON.parseObject(result2);
        JSONObject data = JSONObject.parseObject(object2.getString("data"));

        Map<String,String> m=JSONArray.parseObject(data.get("document").toString(),Map.class);
        return m.get("title");
    }

    /*获取飞书文章标题*/
    public static String getContent(String docToken)
    {
        String tenantToken = getTenantToken();
        String url= "https://open.feishu.cn/open-apis/docx/v1/documents/"+docToken+"/raw_content";
        String result2 = HttpUtil.createRequest(Method.GET, url).
                header(Header.AUTHORIZATION, tenantToken).execute().body();
        JSONObject object2= JSON.parseObject(result2);
        JSONObject data = JSONObject.parseObject(object2.getString("data"));

//        Map<String,String> m=JSONArray.parseObject(data.get("content").toString(),Map.class);
        return data.get("content").toString();
    }

    //获取项目文档备案号
    public static String getRef(String title)
    {
        String ref=Integer.parseInt(title.replace("-1","")
        .replace("-2","")
        .replace("-3","")
        .replace("-4","")
        .replace(".pdf","")
        )+"";
        return ref;
    }

    //获取项目文档类型编码
    public static String getType(String title)
    {
        title=title.replace(".pdf","");
        String type="021000000"+title.charAt(title.length()-1);
        return type;
    }

    public static boolean matchDate(String date,String start,String end)
    {
        Integer cur=Integer.parseInt(date.substring(0,9).replace("-",""));
        Integer s=Integer.parseInt(start.substring(0,9).replace("-",""));
        Integer e=Integer.parseInt(end.substring(0,9).replace("-",""));
        System.out.println(cur+"   "+s+"   "+e);
        if(cur>=s&&cur<=e)
        {
            return true;
        }
        return false;
    }
    /*飞书文件夹分页token*/
    public static String getNextToken(String token,String nextToken)
    {
        String tenantToken = getTenantToken();
        String url="https://open.feishu.cn/open-apis/drive/v1/files?" +
                "page_size=200&folder_token="+token;
        if(nextToken!="")
        {
            url+="&page_token="+nextToken;
        }

        String result2 = HttpUtil.createRequest(Method.GET, url).
                header(Header.AUTHORIZATION, tenantToken).execute().body();
        JSONObject object2= JSON.parseObject(result2);
        JSONObject data = JSONObject.parseObject(object2.getString("data"));
        Object resObj=data.get("next_page_token");
        if(resObj==null)
        {
            return "";
        }
        else
        {
            return resObj.toString();
        }
    }


    public static void setEsSize() throws Exception
    {
        //设置es查询buffer大小
        RequestOptions requestOptions = RequestOptions.DEFAULT;
        Class<? extends RequestOptions> reqClass = requestOptions.getClass();
        Field reqField = reqClass.getDeclaredField("httpAsyncResponseConsumerFactory");
        reqField.setAccessible(true);
        //去除final
        Field modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        modifiersField.setInt(reqField, reqField.getModifiers() & ~Modifier.FINAL);

        //设置默认的工厂
        reqField.set(requestOptions, (HttpAsyncResponseConsumerFactory) () -> {
            //500MB
            return new HeapBufferedAsyncResponseConsumer(5 * 100 * 1024 * 1024);
        });
    }

    public static void setEsSize2() throws Exception
    {
        RequestOptions.Builder builder = RequestOptions.DEFAULT.toBuilder();
        builder.setHttpAsyncResponseConsumerFactory(
                new HttpAsyncResponseConsumerFactory
                        //修改为500MB
                        .HeapBufferedResponseConsumerFactory(500 * 1024 * 1024));
    }
}
