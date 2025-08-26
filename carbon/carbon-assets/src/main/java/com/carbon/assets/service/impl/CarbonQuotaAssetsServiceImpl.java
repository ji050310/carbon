package com.carbon.assets.service.impl;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.carbon.assets.entity.CarbonQuotaAssets;
import com.carbon.assets.mapper.CarbonQuotaAssetsMapper;
import com.carbon.assets.service.CarbonQuotaAssetsService;
import com.carbon.assets.param.CarbonQuotaAssetsQueryParam;
import com.carbon.assets.vo.CarbonAssetsTotalVo;
import com.carbon.assets.vo.CarbonQuotaAssetsQueryVo;
import com.carbon.common.service.BaseServiceImpl;
import com.carbon.common.api.Paging;
import com.carbon.domain.auth.vo.SecurityData;
import com.carbon.domain.common.constant.RocketDelayLevelConstant;
import com.carbon.domain.common.constant.RocketMqName;
import com.carbon.domain.mq.entity.AssetUploadApproval;
import com.carbon.domain.mq.entity.QuotaApproval;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.io.Serializable;


/**
 * <p>
 * 碳配额资产 服务实现类
 * </p>
 *
 * @author Li Jun
 * @since 2022-04-24
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class CarbonQuotaAssetsServiceImpl extends BaseServiceImpl<CarbonQuotaAssetsMapper, CarbonQuotaAssets> implements CarbonQuotaAssetsService {

    @Resource
    private CarbonQuotaAssetsMapper carbonQuotaAssetsMapper;

    @Resource
    private RocketMQTemplate mqTemplate;

    @Override
    public CarbonQuotaAssetsQueryVo getCarbonQuotaAssetsById(Serializable id) {
        CarbonQuotaAssetsQueryVo vo = carbonQuotaAssetsMapper.getCarbonQuotaAssetsById(id);
        return vo;
    }

    @Override
    public Paging<CarbonQuotaAssetsQueryVo> getCarbonQuotaAssetsPageList(CarbonQuotaAssetsQueryParam param) {
        Page<?> page = getPage(param);
        page.addOrder(OrderItem.desc("cqa.updated_time"));
        IPage<CarbonQuotaAssetsQueryVo> iPage = carbonQuotaAssetsMapper.getCarbonQuotaAssetsPageList(page,param);
        return new Paging<>(iPage);
    }

    @Override
    public CarbonAssetsTotalVo getCarbonAssetsTotal() {
        return carbonQuotaAssetsMapper.getCarbonAssetsTotal();
    }

    @Override
    public void SendFeishuApprove(CarbonQuotaAssets carbonQuotaAssets) {
        SecurityData data = getLoginInfoVo().getSecurityData();
        //飞书审批
        QuotaApproval approval = QuotaApproval.builder()
                .id(carbonQuotaAssets.getId())
                .userName(handleNull(data.getAccountName()))
                .agenciesName(handleNull(data.getTenantName()))
                .contactNumber(handleNull(data.getPhone()))
                .assetType(handleNull("碳配额"))
                .primaryMarketHoldingInstitutions(handleNull(null))
                .shareholding(handleNull(carbonQuotaAssets.getTotal().toString()))
                .proofOfPosition(handleNull(carbonQuotaAssets.getIssuingCertificates()))
                .issuingAgency(handleNull(carbonQuotaAssets.getIssuingAgency()))
                .build();
        Message<QuotaApproval> msg= MessageBuilder.withPayload(approval).build();
        mqTemplate.syncSend(RocketMqName.QuotaApproval_MSG,msg,3000, RocketDelayLevelConstant.SECOND5);
    }


    private String handleNull(String str){
        return str == null ? "-" : str;
    }
}