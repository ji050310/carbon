package com.carbon.assets.service.impl;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.carbon.assets.entity.CarbonMethodology;
import com.carbon.assets.mapper.CarbonMethodologyMapper;
import com.carbon.domain.assets.vo.MethodologyUploadParam;
import com.carbon.assets.service.CarbonMethodologyService;
import com.carbon.assets.param.CarbonMethodologyQueryParam;
import com.carbon.assets.vo.CarbonMethodologyQueryVo;
import com.carbon.assets.vo.CarbonMethodologySelectVo;
import com.carbon.common.service.BaseServiceImpl;
import com.carbon.common.api.Paging;
import com.carbon.domain.common.constant.RocketDelayLevelConstant;
import com.carbon.domain.common.constant.RocketMqName;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class CarbonMethodologyServiceImpl extends BaseServiceImpl<CarbonMethodologyMapper, CarbonMethodology> implements CarbonMethodologyService {

    @Resource
    private CarbonMethodologyMapper carbonMethodologyMapper;

    @Autowired
    private RocketMQTemplate mqTemplate;

    @Override
    public CarbonMethodologyQueryVo getCarbonMethodologyById(Serializable id) {
        return carbonMethodologyMapper.getCarbonMethodologyById(id);
    }

    @Override
    public Paging<CarbonMethodologyQueryVo> getCarbonMethodologyPageList(CarbonMethodologyQueryParam param) {
        Page<?> page = getPage(param);
        page.addOrder(OrderItem.desc("cm.updated_time"));
        IPage<CarbonMethodologyQueryVo> iPage = carbonMethodologyMapper.getCarbonMethodologyPageList(page,param);
        return new Paging<>(iPage);
    }

    @Override
    public void addCarbonMethodology(MethodologyUploadParam param) {
        CarbonMethodology carbonMethodology=new CarbonMethodology();
        carbonMethodology.setName(param.getName());
        carbonMethodology.setSourceFileUrl(param.getPdfUrl());
        carbonMethodology.setFieldCode(param.getFieldCode());
        carbonMethodology.setMethodCode(param.getMethodCode());
        carbonMethodology.setCertificationCriteria(param.getCertificationCriteria());
        carbonMethodology.setStatusCode("0450000003");
        carbonMethodology.setIndustryCode(param.getIndustryCode());
        carbonMethodology.setCreatorId(getCurrentAccountId());
        carbonMethodology.setCreatedTime(LocalDate.now());
        carbonMethodology.setUpdatedTime(LocalDate.now());
        carbonMethodology.setDictCode(param.getDictCode());
        save(carbonMethodology);

        //发送mq消息同步方法学内容
        if (param.getWordUrl()!=""&&param.getWordUrl()!=null)
        {
            Message<MethodologyUploadParam> msg= MessageBuilder.withPayload(param).build();
            mqTemplate.syncSend(RocketMqName.SYN_METHOD_CONTENT_MSG,msg,3000, RocketDelayLevelConstant.SECOND5);
        }
    }

    @Override
    public boolean updateCarbonMethodology(CarbonMethodology carbonMethodology) {
        return updateById(carbonMethodology);
    }

    @Override
    public List<CarbonMethodologySelectVo> getCarbonMethodologyList() {
        return carbonMethodologyMapper.getCarbonMethodologyList();
    }

    @Override
    public CarbonMethodology getCarbonMethodologyByName(String name) throws Exception{
        try {
            CarbonMethodology carbonMethodology=carbonMethodologyMapper.getCarbonMethodologyByName(name);
            return carbonMethodology;
        }
        catch (Exception e)
        {
            return null;
        }
    }
}