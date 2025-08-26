package com.carbon.assets.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.carbon.assets.common.enums.AssetsStatus;
import com.carbon.assets.common.enums.AssetsTradeStatus;
import com.carbon.assets.entity.CarbonCreditAssets;
import com.carbon.assets.entity.CarbonMetaregistry;
import com.carbon.assets.entity.CarbonMetaregistryProject;
import com.carbon.assets.entity.CarbonProject;
import com.carbon.assets.mapper.*;
import com.carbon.assets.param.CarbonCreditAssetsAddParam;
import com.carbon.assets.param.CarbonProjectAddParam;
import com.carbon.assets.service.CarbonCreditAssetsService;
import com.carbon.assets.param.CarbonCreditAssetsQueryParam;
import com.carbon.assets.service.CarbonProjectService;
import com.carbon.assets.vo.CarbonAssetsTotalVo;
import com.carbon.assets.vo.CarbonCreditAssetsQueryVo;
import com.carbon.assets.vo.CarbonMetaregistryQueryVo;
import com.carbon.common.service.BaseServiceImpl;
import com.carbon.common.api.Paging;
import com.carbon.domain.auth.vo.SecurityData;
import com.carbon.domain.chainmaker.api.ChainMakerServiceApi;
import com.carbon.domain.chainmaker.param.CarbonCreditAssetsParam;
import com.carbon.domain.common.constant.RocketDelayLevelConstant;
import com.carbon.domain.common.constant.RocketMqName;
import com.carbon.domain.mq.entity.AssetUploadApproval;
import com.carbon.domain.system.api.SystemServiceApi;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * <p>
 * 碳信用资产 服务实现类
 * </p>
 *
 * @author Li Jun
 * @since 2022-04-24
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class CarbonCreditAssetsServiceImpl extends BaseServiceImpl<CarbonCreditAssetsMapper, CarbonCreditAssets> implements CarbonCreditAssetsService {

    @Resource
    private CarbonCreditAssetsMapper carbonCreditAssetsMapper;
    @Resource
    private CarbonMetaregistryProjectMapper carbonMetaregistryProjectMapper;
    @Resource
    private CarbonMetaregistryMapper carbonMetaregistryMapper;

    @Resource
    private RocketMQTemplate mqTemplate;

    @Autowired
    private SystemServiceApi systemServiceApi;

    @Autowired
    private ChainMakerServiceApi chainMakerServiceApi;


    @Override
    public CarbonCreditAssetsQueryVo getCarbonCreditAssetsById(Serializable id) {
        CarbonCreditAssetsQueryVo vo = carbonCreditAssetsMapper.getCarbonCreditAssetsById(id);
        CarbonMetaregistry carbonMetaregistry = carbonMetaregistryMapper.selectById(vo.getCarbonProjectId());

        if(carbonMetaregistry!=null)
        {
            String certifiedStandard=carbonMetaregistry.getCertifiedStandard();
            if(certifiedStandard!=null&&certifiedStandard!="")
            {
                vo.setCertificationCriteria(certifiedStandard);
                if(certifiedStandard.equals("CCER"))
                {
                    vo.setIssuingAgency("生态环境部");
                }
                else if(certifiedStandard.equals("VCS"))
                {
                    vo.setIssuingAgency("verra");
                }
                else if(certifiedStandard.equals("goldstandard"))
                {
                    vo.setIssuingAgency(certifiedStandard);
                }
            }

            vo.setCarbonMethodologyName(carbonMetaregistry.getMethodologyCode());
            vo.setProjectScopeTypeCode(carbonMetaregistry.getProjectScopeTypeCode());
            vo.setCertifiedAgency(carbonMetaregistry.getProjectVerifier());
            vo.setIssuingDate(carbonMetaregistry.getIssuingDate());
            vo.setTenantName(carbonMetaregistry.getProjectOwner());
        }
        return vo;
    }

    @Override
    public Paging<CarbonCreditAssetsQueryVo> getCarbonCreditAssetsPageList(CarbonCreditAssetsQueryParam param) {
        Page<?> page = getPage(param);
        page.addOrder(OrderItem.desc("cca.updated_time"));
        IPage<CarbonCreditAssetsQueryVo> iPage = carbonCreditAssetsMapper.getCarbonCreditAssetsPageList(page,param);
        List<CarbonCreditAssetsQueryVo> records = iPage.getRecords();
        ArrayList<CarbonCreditAssetsQueryVo> creditAssetsQueryVos = new ArrayList<>();
        return new Paging<>(iPage);
    }

    @Override
    public void addCarbonCreditAssets(CarbonCreditAssetsAddParam param) {

        CarbonCreditAssets carbonCreditAssets = new CarbonCreditAssets();
        BeanUtil.copyProperties(param,carbonCreditAssets,"id","projectId");
        // 查询
        CarbonMetaregistryQueryVo cm = carbonMetaregistryMapper.getCarbonMetaregistryById(param.getCarbonProjectId());
        log.info("this carbonMetaregistry.object : {}", JSONUtil.toJsonStr(cm));
        // 插入一个 CarbonMetaRegistryProject
        CarbonMetaregistryProject project = new CarbonMetaregistryProject();
        project.setMetaregistryId(cm.getId());
        project.setTenantId(getCurrentTenantId());
        // 插入
        carbonMetaregistryProjectMapper.insert(project);
        log.info("projectId : {}",project.getId());
        // 保存
        carbonCreditAssets.setCarbonProjectId(param.getCarbonProjectId());
        //上传碳信用项目
        carbonCreditAssets.setTenantId(getCurrentTenantId());
        carbonCreditAssets.setAssetsStatus(AssetsStatus.WAIT.getStatus());
        carbonCreditAssets.setTransactionStatus(AssetsTradeStatus.OFFER.getStatus());
        carbonCreditAssets.setAvailableAmount(carbonCreditAssets.getTotal());
        carbonCreditAssets.setValuation(carbonCreditAssets.getTotal().multiply(BigDecimal.valueOf(50)));
        carbonCreditAssets.setCertifiedAgency(cm.getCertifiedStandard());
        this.save(carbonCreditAssets);

        SecurityData data = getLoginInfoVo().getSecurityData();
        //飞书审批
        AssetUploadApproval approval = AssetUploadApproval.builder()
                .id(carbonCreditAssets.getId())
                .userName(handleNull(data.getAccountName()))
                .agenciesName(handleNull(data.getTenantName()))
                .contactNumber(handleNull(data.getPhone()))
                .assetType(handleNull("碳信用"))
                .primaryMarketHoldingInstitutions(handleNull(null))
                .shareholding(handleNull(carbonCreditAssets.getTotal().toString()))
                .proofOfPosition(handleNull(carbonCreditAssets.getIssuingCertificates()))
                .issuingAgency(handleNull(carbonCreditAssets.getIssuingAgency()))
                .build();
        Message<AssetUploadApproval> msg= MessageBuilder.withPayload(approval).build();
        mqTemplate.syncSend(RocketMqName.AssetUploadApproval_MSG,msg,3000, RocketDelayLevelConstant.SECOND5);

        //异步发送消息
        try {
            //区块链
            chainMakerServiceApi.addCreditAssets(BeanUtil.copyProperties(carbonCreditAssets,CarbonCreditAssetsParam.class));
        }catch (Exception e)
        {
            log.error("区块链消息异常！！");
        }

    }

    private String handleNull(String str){
        return str == null ? "-" : str;
    }

    @Override
    public CarbonAssetsTotalVo getCarbonAssetsTotal() {
        return carbonCreditAssetsMapper.getCarbonAssetsTotal();
    }

    @Override
    public boolean removeById(Serializable id) {
        Long projectId =  this.getBaseMapper().getCarbonCreditAssetsById(id).getCarbonProjectId();
        boolean res =  SqlHelper.retBool(this.getBaseMapper().deleteById(id));
        if(res){
            Optional.ofNullable(projectId).ifPresent(e->{
                carbonMetaregistryProjectMapper.deleteById(projectId);
            });
        }
        return res;
    }
}