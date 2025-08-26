package com.carbon.assets.util;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class ProjectAddUtil
{
    public static void main(String[] args) throws Exception{
        String json="{\"valueRange\": {\"range\": \"34372d!A1:T1\",\"values\": " +
                "[["  +  "\"string\",\"222\",\"https://www.xxx.com\""  +  "]]}}";
        System.out.println(projectFormAdd(json));
    }
    public static String projectFormAdd(String json) throws Exception{
        String url="https://open.feishu.cn/open-apis/sheets/v2/spreadsheets/shtcniQ1veTlPFtisfnOKTPVPdg/values_append";
        StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);//设置消息头 Content-Type application/json; charset=UTF-8
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Authorization",CommonUtil.getTenantToken());
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
}
