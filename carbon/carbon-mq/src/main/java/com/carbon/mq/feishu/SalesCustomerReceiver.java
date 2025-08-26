package com.carbon.mq.feishu;

import cn.hutool.json.JSONUtil;
import com.carbon.common.feishu.FeiShuAPI;
import com.carbon.domain.common.constant.RocketMqName;
import com.carbon.domain.mq.entity.SalesCustomer;
import com.carbon.domain.trade.vo.MqCarbonTradeQuote;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;


/**
 * 出售客户跟进表单MQ消费者
 * @author Li Jun
 */
@Slf4j
@Component
@RocketMQMessageListener(consumerGroup = RocketMqName.SalesCustomer_MSG, topic = RocketMqName.SalesCustomer_MSG)
public class SalesCustomerReceiver implements RocketMQListener<MessageExt> {

    @Override
    public void onMessage(MessageExt message) {
        log.info("msg id: {}, payload: {}", message.getMsgId(), new String(message.getBody()));
        SalesCustomer data = JSONUtil.toBean(new String(message.getBody()), SalesCustomer.class);
        FeiShuAPI.updateForm("shtcnwqyEqtaFuSch4ZsE4E6RMS","MM9guR",data.getCustomerName(),data,"R");
    }
}
