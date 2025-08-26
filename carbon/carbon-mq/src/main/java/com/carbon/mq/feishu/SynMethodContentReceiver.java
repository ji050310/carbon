package com.carbon.mq.feishu;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.carbon.common.feishu.FeiShuAPI;
import com.carbon.domain.assets.api.AssetsServiceApi;
import com.carbon.domain.assets.vo.MethodologyUploadParam;
import com.carbon.domain.common.constant.RocketMqName;
import com.carbon.domain.mq.entity.DevelopBusiness;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * 同步方法学文档内容
 * @author Li Jun
 */
@Slf4j
@Component
@RocketMQMessageListener(consumerGroup = RocketMqName.SYN_METHOD_CONTENT_MSG, topic = RocketMqName.SYN_METHOD_CONTENT_MSG)
public class SynMethodContentReceiver implements RocketMQListener<MessageExt> {

    @Autowired
    private AssetsServiceApi api;

    @Override
    public void onMessage(MessageExt message) {
        System.out.println("recive message :"+message.getBody());
        MethodologyUploadParam param = JSONUtil.toBean(new String(message.getBody()), MethodologyUploadParam.class);
        api.uploadContent(param);
    }
}
