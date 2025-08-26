package com.carbon.mq.feishu;

import cn.hutool.json.JSONUtil;
import com.carbon.domain.assets.api.AssetsServiceApi;
import com.carbon.domain.common.constant.RocketMqName;
import com.carbon.domain.mq.entity.CarbonProjectAddParam;
import com.carbon.domain.mq.entity.ProjectApproval;
import com.carbon.domain.system.api.SystemServiceApi;
import com.carbon.mq.mapper.CarbonProjectInstanceMapper;
import com.carbon.mq.mapper.SysDictItemMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * 项目立项审批表单MQ消费者
 * @author cbd
 */
@Slf4j
@Component
@RocketMQMessageListener(consumerGroup = RocketMqName.ProjectAdd_MSG, topic = RocketMqName.ProjectAdd_MSG)
public class ProjectAddReceiver implements RocketMQListener<MessageExt> {

    @Autowired
    AssetsServiceApi api;

    @Override
    public void onMessage(MessageExt message) {
        log.info("add message receive");
        CarbonProjectAddParam data = JSONUtil.toBean(new String(message.getBody()), CarbonProjectAddParam.class);
        api.addFeishuProject(data);
        System.out.println("----------------data:"+data);
    }
}
