package com.carbon.mq.feishu;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.carbon.common.feishu.FeiShuAPI;
import com.carbon.domain.common.constant.RocketMqName;
import com.carbon.domain.mq.entity.ProjectApproval;
import com.carbon.domain.system.api.SystemServiceApi;
import com.carbon.mq.entity.CarbonProjectInstance;
import com.carbon.mq.entity.SysDictItem;
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
 * @author Li Jun
 */
@Slf4j
@Component
@RocketMQMessageListener(consumerGroup = RocketMqName.ProjectApproval_MSG, topic = RocketMqName.ProjectApproval_MSG)
public class ProjectApprovalReceiver implements RocketMQListener<MessageExt> {
    @Autowired
    CarbonProjectInstanceMapper instanceMapper;
    @Autowired
    SysDictItemMapper dictItemMapper;

    @Autowired
    private SystemServiceApi systemServiceApi;

    @Override
    public void onMessage(MessageExt message) {
        log.info("msg id: {}, payload: {}", message.getMsgId(), new String(message.getBody()));
        ProjectApproval data = JSONUtil.toBean(new String(message.getBody()), ProjectApproval.class);
        System.out.println("data:"+data);
        systemServiceApi.addProjectApproval(data);
    }
}
