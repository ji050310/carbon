package com.carbon.mq.feishu;

import cn.hutool.json.JSONUtil;
import com.carbon.common.feishu.FeiShuAPI;
import com.carbon.domain.common.constant.RocketMqName;
import com.carbon.domain.mq.entity.OpenRegisterAccount;
import com.carbon.domain.mq.entity.OpenTradeAccount;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;


/**
 * 注册开户跟进表单MQ消费者
 * @author Li Jun
 */
@Slf4j
@Component
@RocketMQMessageListener(consumerGroup = RocketMqName.OpenRegisterAccount_MSG, topic = RocketMqName.OpenRegisterAccount_MSG)
public class OpenRegisterAccountReceiver implements RocketMQListener<MessageExt> {

    @Override
    public void onMessage(MessageExt message) {
        log.info("msg id: {}, payload: {}", message.getMsgId(), new String(message.getBody()));
        OpenRegisterAccount data = JSONUtil.toBean(new String(message.getBody()), OpenRegisterAccount.class);
        FeiShuAPI.updateForm("shtcnwqyEqtaFuSch4ZsE4E6RMS","0Fk4AY",data.getAccountName(),data,"N");
    }
}
