package com.carbon.assets.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.carbon.common.exception.CommonBizException;
import com.carbon.assets.entity.CarbonAssets;
import com.carbon.assets.mapper.CarbonAssetsMapper;
import com.carbon.assets.service.CarbonAssetsService;
import com.carbon.assets.param.CarbonAssetsQueryParam;
import com.carbon.assets.vo.CarbonAssetsQueryVo;
import com.carbon.common.service.BaseServiceImpl;
import com.carbon.common.api.Paging;


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
 * 碳中和资产 服务实现类
 * </p>
 *
 * @author Li Jun
 * @since 2021-07-31
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class CarbonAssetsServiceImpl extends BaseServiceImpl<CarbonAssetsMapper, CarbonAssets> implements CarbonAssetsService {

    @Resource
    private CarbonAssetsMapper carbonAssetsMapper;
    @Autowired
    private CarbonBlockChainMsgProduce carbonBlockChainMsgProduce;
    @Autowired
    private SystemServiceApi systemServiceApi;

    @Override
    public CarbonAssetsQueryVo getCarbonAssetsById(Long id) {
        CarbonAssetsQueryParam queryParam = new CarbonAssetsQueryParam();
        queryParam.setId(id);
        List<CarbonAssetsQueryVo> records = getCarbonAssetsPageList(queryParam).getRecords();
        return CollUtil.getFirst(records);
    }

    @Override
    public Paging<CarbonAssetsQueryVo> getCarbonAssetsPageList(CarbonAssetsQueryParam param) {
        Page<?> page = getPage(param);
        page.addOrder(OrderItem.desc("ca.updated_time"));
        IPage<CarbonAssetsQueryVo> iPage = carbonAssetsMapper.getCarbonAssetsPageList(page,param);
        return new Paging<>(iPage);
    }

    @Override
    public void addCarbonAssets(CarbonAssets carbonAssets) {
        carbonAssets.setReceivingId(getCurrentTenantId());
        carbonAssets.setPublishId(getCurrentTenantId());
        save(carbonAssets);
    }

    @Override
    public boolean updateCarbonAssets(CarbonAssets carbonAssets) {
        CarbonAssets assets = getById(carbonAssets.getId());
        if (assets == null){
            throw new CommonBizException("中和资产不存在");
        }
        return updateById(carbonAssets);
    }

    @Override
    public void certified(String assetsId) {
        carbonBlockChainMsgProduce.assetsCertified(assetsId);
    }

}
