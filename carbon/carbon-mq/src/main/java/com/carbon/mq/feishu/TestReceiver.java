package com.carbon.mq.feishu;

import cn.hutool.json.JSONUtil;
import com.carbon.domain.common.constant.RocketMqName;
import com.carbon.domain.trade.vo.MqCarbonTradeQuote;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;


/**
 * 测试Mq消息
 * @author Li Jun
 */
@Slf4j
@Component
@RocketMQMessageListener(consumerGroup = RocketMqName.FS_TEST, topic = RocketMqName.FS_TEST)
public class TestReceiver implements RocketMQListener<MessageExt> {

    @Override
    public void onMessage(MessageExt message) {
        log.info("msg id: {}, payload: {}", message.getMsgId(), new String(message.getBody()));
        MqCarbonTradeQuote vo = JSONUtil.toBean(new String(message.getBody()), MqCarbonTradeQuote.class);
    }
}
