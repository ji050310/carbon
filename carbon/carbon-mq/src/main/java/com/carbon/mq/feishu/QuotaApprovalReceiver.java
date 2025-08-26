package com.carbon.mq.feishu;

import cn.hutool.json.JSONUtil;
import com.carbon.domain.common.constant.RocketMqName;
import com.carbon.domain.mq.entity.AssetUploadApproval;
import com.carbon.domain.mq.entity.QuotaApproval;
import com.carbon.domain.system.api.SystemServiceApi;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * 资产上传审批表单MQ消费者
 * @author Li Jun
 */
@Slf4j
@Component
@RocketMQMessageListener(consumerGroup = RocketMqName.QuotaApproval_MSG, topic = RocketMqName.QuotaApproval_MSG)
public class QuotaApprovalReceiver implements RocketMQListener<MessageExt> {

    @Autowired
    private SystemServiceApi systemServiceApi;

    @Override
    public void onMessage(MessageExt message) {
        log.info("!!!!!!!!!\n收到碳配额审批！！！");
        log.info("msg id: {}, payload: {}", message.getMsgId(), new String(message.getBody()));
        QuotaApproval data = JSONUtil.toBean(new String(message.getBody()), QuotaApproval.class);
        systemServiceApi.addQuotaApproval(data);
    }
}
