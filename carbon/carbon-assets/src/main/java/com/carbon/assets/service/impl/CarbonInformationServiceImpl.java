package com.carbon.assets.service.impl;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.carbon.assets.entity.CarbonInformation;
import com.carbon.assets.mapper.CarbonInformationMapper;
import com.carbon.assets.service.CarbonInformationService;
import com.carbon.assets.param.CarbonInformationQueryParam;
import com.carbon.assets.vo.CarbonInformationQueryVo;
import com.carbon.common.service.BaseServiceImpl;
import com.carbon.common.api.Paging;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.io.Serializable;
import java.util.List;


/**
 * <p>
 * 碳资讯 服务实现类
 * </p>
 *
 * @author Li Jun
 * @since 2022-01-11
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class CarbonInformationServiceImpl extends BaseServiceImpl<CarbonInformationMapper, CarbonInformation> implements CarbonInformationService {

    @Resource
    private CarbonInformationMapper carbonInformationMapper;

    @Override
    public CarbonInformationQueryVo getCarbonInformationById(Serializable id) {
        return carbonInformationMapper.getCarbonInformationById(id);
    }

    @Override
    public Paging<CarbonInformationQueryVo> getCarbonInformationPageList(CarbonInformationQueryParam param) {
        Page<?> page = getPage(param);
        page.addOrder(OrderItem.desc("updated_time"));
        IPage<CarbonInformationQueryVo> iPage = carbonInformationMapper.getCarbonInformationPageList(page,param);
        return new Paging<>(iPage);
    }

    @Override
    public List<CarbonInformationQueryVo> getRandomList(Integer num) {
        return carbonInformationMapper.getRandomList(num);
    }

}
