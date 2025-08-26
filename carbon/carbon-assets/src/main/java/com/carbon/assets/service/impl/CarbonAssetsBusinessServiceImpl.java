package com.carbon.assets.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.carbon.assets.entity.CarbonAssetsBusiness;
import com.carbon.assets.mapper.CarbonAssetsBusinessMapper;
import com.carbon.assets.service.CarbonAssetsBusinessService;
import com.carbon.assets.param.CarbonAssetsBusinessQueryParam;
import com.carbon.assets.vo.CarbonAssetsBusinessQueryVo;
import com.carbon.common.service.BaseServiceImpl;
import com.carbon.common.api.Paging;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;


/**
 * <p>
 * 中和资产交易  服务实现类
 * </p>
 *
 * @author Li Jun
 * @since 2021-09-24
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class CarbonAssetsBusinessServiceImpl extends BaseServiceImpl<CarbonAssetsBusinessMapper, CarbonAssetsBusiness> implements CarbonAssetsBusinessService {
    @Autowired
    private CarbonBlockChainMsgProduce carbonBlockChainMsgProduce;

    @Resource
    private CarbonAssetsBusinessMapper carbonAssetsBusinessMapper;

    @Override
    public CarbonAssetsBusinessQueryVo getCarbonAssetsBusinessById(Long id) {
        CarbonAssetsBusinessQueryParam param = new CarbonAssetsBusinessQueryParam();
        param.setId(id);
        List<CarbonAssetsBusinessQueryVo> records = getCarbonAssetsBusinessPageList(param).getRecords();
        return CollUtil.getFirst(records);
    }

    @Override
    public Paging<CarbonAssetsBusinessQueryVo> getCarbonAssetsBusinessPageList(CarbonAssetsBusinessQueryParam param) {
        Page<?> page = getPage(param);
        page.addOrder(OrderItem.desc("cab.updated_time"));
        IPage<CarbonAssetsBusinessQueryVo> iPage = carbonAssetsBusinessMapper.getCarbonAssetsBusinessPageList(getPage(param),param);
        return new Paging<>(iPage);
    }

    @Override
    public void addCarbonAssetsBusiness(CarbonAssetsBusiness assetsBusiness) {
        carbonBlockChainMsgProduce.assetsBusiness(assetsBusiness);
    }

}
