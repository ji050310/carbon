package com.carbon.mq.Wechat;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.carbon.domain.common.constant.RocketMqName;
import com.carbon.domain.mall.api.CmallApi;
import com.carbon.domain.mq.entity.AssetUploadApproval;
import com.carbon.domain.system.api.SystemServiceApi;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * 商城下单MQ消费者
 * @author cbd
 */
@Slf4j
@Component
@RocketMQMessageListener(consumerGroup = RocketMqName.WECHAT_MEMBER_ORDER_MSG, topic = RocketMqName.WECHAT_MEMBER_ORDER_MSG)
public class MemberOrderReceiver implements RocketMQListener<MessageExt> {

    @Autowired
    CmallApi cmallApi;

    @Override
    public void onMessage(MessageExt message) {
        String data = JSONUtil.toBean(new String(message.getBody()), String.class);
        log.info("-------------------接收开通会员回调消息："+data);
        JSONObject jsonObject=new JSONObject(data);
        JSONObject rawData=(JSONObject)jsonObject.get("rawData");
        JSONObject result=(JSONObject)jsonObject.get("result");

        log.info("-------------------执行支付成功后的业务");
        //执行后续业务
//        cmallApi.openVip();
    }
}
