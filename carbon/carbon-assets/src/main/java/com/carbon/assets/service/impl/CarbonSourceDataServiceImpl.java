package com.carbon.assets.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.carbon.assets.service.CarbonAssetsService;
import com.carbon.common.exception.CommonBizException;
import com.carbon.assets.entity.CarbonAssets;
import com.carbon.assets.entity.CarbonSourceData;
import com.carbon.assets.mapper.CarbonSourceDataMapper;
import com.carbon.assets.service.CarbonSourceDataService;
import com.carbon.assets.param.CarbonSourceDataQueryParam;
import com.carbon.assets.vo.CarbonSourceDataQueryVo;
import com.carbon.common.service.BaseServiceImpl;
import com.carbon.common.api.Paging;
import com.carbon.domain.block.param.CarbonOffsetAddParam;
import com.carbon.domain.system.api.SystemServiceApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;



/**
 * <p>
 * 碳源数据 服务实现类
 * </p>
 *
 * @author Li Jun
 * @since 2021-08-07
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class CarbonSourceDataServiceImpl extends BaseServiceImpl<CarbonSourceDataMapper, CarbonSourceData> implements CarbonSourceDataService {

    @Resource
    private CarbonSourceDataMapper carbonSourceDataMapper;
    @Autowired
    private SystemServiceApi systemServiceApi;
    @Autowired
    CarbonBlockChainMsgProduce carbonBlockChainMsgProduce;
    @Autowired
    private CarbonAssetsService carbonAssetsService;

    @Override
    public CarbonSourceDataQueryVo getCarbonSourceDataById(Long id) {
        CarbonSourceDataQueryParam param = new CarbonSourceDataQueryParam();
        param.setId(id);
        List<CarbonSourceDataQueryVo> records = getCarbonSourceDataPageList(param).getRecords();
        return CollUtil.getFirst(records);
    }

    @Override
    public Paging<CarbonSourceDataQueryVo> getCarbonSourceDataPageList(CarbonSourceDataQueryParam param) {
        Page<?> page = getPage(param);
        page.addOrder(OrderItem.desc("csd.updated_time"));
        IPage<CarbonSourceDataQueryVo> iPage = carbonSourceDataMapper.getCarbonSourceDataPageList(page,param);
        return new Paging<>(iPage);
    }

    @Override
    public void addCarbonSourceData(CarbonSourceData carbonSourceData) {
        carbonSourceData.setCreatorId(getCurrentAccountId());
        carbonSourceData.setTenantId(getCurrentTenantId());
        save(carbonSourceData);
    }

    @Override
    public boolean updateCarbonSourceData(CarbonSourceData carbonSourceData) {
        return updateById(carbonSourceData);
    }

    @Override
    public void submitted(String carbonDataId) {
        CarbonSourceData carbonData = getById(carbonDataId);
        //碳减排资产上报长安链
        CarbonOffsetAddParam param = new CarbonOffsetAddParam();
        param.setTokenId(carbonDataId);
        param.setUserId("admin");
        param.setOrgName("xihu_taxi");
        param.setProjectId("CCER1000");
        param.setRegistry("CCER");
        param.setScope("Forestry & Land Use");
        param.setMethodology("VM0010");
        param.setRegion("region");
        param.setDeveloper("深圳碳普惠");
        param.setTotalIssued(carbonData.getAmount().longValue());
        param.setTotalRetired(carbonData.getAmount().longValue());
        param.setTotalRemaining(0L);
        param.setFirstProjectYear("2021");
        param.setProjectOwner("深圳碳普惠");
        param.setOffsetProjectOperator("深圳碳普惠经纪人");
        param.setVerifier("生态环境部");
        param.setVoluntaryStatus("Registered");
        param.setProjectListed("2020/9/24");
        param.setProjectRegistered("2020/9/24");
        param.setProjectType("Agriculture Forestry and Other Land Use");
        param.setDateAdded("2020/9/24");
        param.setSource("碳惠宝出行平台");
        param.setSourceType("API接入");
        param.setSign(true);
        param.setTotalIssuedFutureYears("1");
        String txId = carbonBlockChainMsgProduce.syncAddCarbonOffset(param);
        if (StrUtil.isEmpty(txId)){
            throw new CommonBizException("上报长安链失败");
        }

        //更新碳数据状态
        carbonData.setTxId(txId);
        carbonData.setStatus(2);
        carbonData.setSubmittedTime(DateUtil.date());
        updateById(carbonData);

        //保存中和资产
        CarbonAssets carbonAssets = new CarbonAssets();
        carbonAssets.setCarbonSourceDataId(carbonDataId);
        carbonAssets.setAmount(carbonData.getAmount());
        carbonAssets.setType(carbonData.getType());
        carbonAssets.setUomType(carbonData.getUomType());
        carbonAssets.setStatus(1);
        carbonAssets.setTimeScope(DateUtil.date().toString());
        carbonAssets.setMainList("x");
        carbonAssets.setMetaData("x");
        carbonAssetsService.addCarbonAssets(carbonAssets);
    }

}
