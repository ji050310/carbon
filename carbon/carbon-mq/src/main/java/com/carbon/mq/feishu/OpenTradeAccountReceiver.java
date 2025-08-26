package com.carbon.mq.feishu;

import cn.hutool.json.JSONUtil;
import com.carbon.common.feishu.FeiShuAPI;
import com.carbon.domain.common.constant.RocketMqName;
import com.carbon.domain.mq.entity.OpenTradeAccount;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;


/**
 * 交易开户跟进表单MQ消费者
 * @author Li Jun
 */
@Slf4j
@Component
@RocketMQMessageListener(consumerGroup = RocketMqName.OpenTradeAccount_MSG, topic = RocketMqName.OpenTradeAccount_MSG)
public class OpenTradeAccountReceiver implements RocketMQListener<MessageExt> {

    @Override
    public void onMessage(MessageExt message) {
        log.info("msg id: {}, payload: {}", message.getMsgId(), new String(message.getBody()));
        OpenTradeAccount data = JSONUtil.toBean(new String(message.getBody()), OpenTradeAccount.class);
        FeiShuAPI.updateForm("shtcnwqyEqtaFuSch4ZsE4E6RMS","nJRBMl",data.getTradeAccount(),data,"J");
    }
}
