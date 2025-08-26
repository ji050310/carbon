package com.carbon.mq.canal;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.carbon.domain.common.ApiResult;
import com.carbon.domain.common.constant.RocketMqName;
import com.carbon.domain.common.constant.RocketMqTagName;
import com.carbon.domain.system.api.SystemServiceApi;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
@RocketMQMessageListener(
        consumerGroup = "ods_news", topic = "ods_news")
public class OdsNewsReceiver implements RocketMQListener<MessageExt>, RocketMQPushConsumerLifecycleListener {
    @Autowired
    SystemServiceApi systemServiceApi;
    @Override
    public void onMessage(MessageExt message) {
        String body = new String(message.getBody());
        if (!JSONUtil.isJson(body)) {
            return;
        }
        JSONObject msgJsonObject = JSONUtil.parseObj(body);
        log.info(" ===> msgBody:{}, msgId:{}, topic:{} , queueId:{}",
                msgJsonObject,message.getMsgId(),message.getTopic(),message.getQueueId());
        String sqlType = msgJsonObject.getStr("type");
        switch (sqlType) {
            case "UPDATE":
            case "INSERT":
            case "DELETE":
                break;
            default:
                log.info("不同步的消息类型：{}" , sqlType);
                return;
        }
        String table = msgJsonObject.getStr("table");  // 获取表名
        JSONArray data = msgJsonObject.getJSONArray("data");
        JSONArray pkNames = msgJsonObject.getJSONArray("pkNames");
        log.info("表名为:{} ,sql类型为：{}" ,table,sqlType);
        /**
         * 如果是插入
         */
        if("INSERT".equals(sqlType)){
            JSONObject tableData = data.getJSONObject(0);
            String title = tableData.getStr("title");
            String categoryId = tableData.getStr("category_id");
            String status = tableData.getStr("status");
            String author = tableData.getStr("author");
            String source = tableData.getStr("source");
            String content = tableData.getStr("content");
            Date createdTime = tableData.getDate("date");
            Date updatedTime = tableData.getDate("date");

            JSONObject article = new JSONObject();
            article.set("title",title);
            article.set("description",title);
            article.set("content",content);
            article.set("author",author);
            article.set("categoryId",categoryId);
            article.set("status",status);
            article.set("copyFrom",source);
            article.set("createdTime",createdTime);
            article.set("updatedTime",updatedTime);

            ApiResult result = systemServiceApi.pushArticle(article);

            log.info("===> call result: {}",result);

        }


    }

    @Override
    public void prepareStart(DefaultMQPushConsumer consumer) {
        consumer.setInstanceName("ods_news");
    }
}
