package com.carbon.mq.feishu;

import cn.hutool.json.JSONUtil;
import com.carbon.common.feishu.FeiShuAPI;
import com.carbon.domain.common.constant.RocketMqName;
import com.carbon.domain.mq.entity.TradingPerformance;
import com.carbon.domain.trade.vo.MqCarbonTradeQuote;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;


/**
 * 交易履约跟进表单MQ消费者
 * @author Li Jun
 */
@Slf4j
@Component
@RocketMQMessageListener(consumerGroup = RocketMqName.TradingPerformance_MSG, topic = RocketMqName.TradingPerformance_MSG)
public class TradingPerformanceReceiver implements RocketMQListener<MessageExt> {

    @Override
    public void onMessage(MessageExt message) {
        log.info("msg id: {}, payload: {}", message.getMsgId(), new String(message.getBody()));
        TradingPerformance data = JSONUtil.toBean(new String(message.getBody()), TradingPerformance.class);
        FeiShuAPI.updateForm("shtcnwqyEqtaFuSch4ZsE4E6RMS","8JngyC",data.getConversation(),data,"V");
    }
}
