package com.carbon.mq.feishu;

import cn.hutool.json.JSONUtil;
import com.carbon.common.feishu.FeiShuAPI;
import com.carbon.domain.common.constant.RocketMqName;
import com.carbon.domain.mq.entity.AddTradingAccountApproval;
import com.carbon.domain.mq.entity.DevelopBusiness;
import com.carbon.domain.system.api.SystemServiceApi;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * 添加交易账户审批表单MQ消费者
 * @author Li Jun
 */
@Slf4j
@Component
@RocketMQMessageListener(consumerGroup = RocketMqName.AddTradingAccountApproval_MSG, topic = RocketMqName.AddTradingAccountApproval_MSG)
public class AddTradingAccountApprovalReceiver implements RocketMQListener<MessageExt> {

    @Autowired
    private SystemServiceApi systemServiceApi;

    @Override
    public void onMessage(MessageExt message) {
        log.info("msg id: {}, payload: {}", message.getMsgId(), new String(message.getBody()));
        AddTradingAccountApproval data = JSONUtil.toBean(new String(message.getBody()), AddTradingAccountApproval.class);
        systemServiceApi.addTradeAccountApproval(data);
    }
}
