package com.carbon.assets.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.carbon.assets.entity.CarbonProjectMonitoringData;
import com.carbon.assets.mapper.CarbonProjectMonitoringDataMapper;
import com.carbon.assets.service.CarbonProjectMonitoringDataService;
import com.carbon.assets.param.CarbonProjectMonitoringDataQueryParam;
import com.carbon.assets.vo.CarbonProjectMonitoringDataQueryVo;
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
 * 碳减排项目监测数据 服务实现类
 * </p>
 *
 * @author Li Jun
 * @since 2022-05-11
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class CarbonProjectMonitoringDataServiceImpl extends BaseServiceImpl<CarbonProjectMonitoringDataMapper, CarbonProjectMonitoringData> implements CarbonProjectMonitoringDataService {

    @Resource
    private CarbonProjectMonitoringDataMapper carbonProjectMonitoringDataMapper;

    @Override
    public CarbonProjectMonitoringDataQueryVo getCarbonProjectMonitoringDataById(Serializable id) {
        return carbonProjectMonitoringDataMapper.getCarbonProjectMonitoringDataById(id);
    }

    @Override
    public Paging<CarbonProjectMonitoringDataQueryVo> getCarbonProjectMonitoringDataPageList(CarbonProjectMonitoringDataQueryParam param) {
        IPage<CarbonProjectMonitoringDataQueryVo> iPage = carbonProjectMonitoringDataMapper.getCarbonProjectMonitoringDataPageList(getPage(param),param);
        return new Paging<>(iPage);
    }

    @Override
    public List<CarbonProjectMonitoringDataQueryVo> getListByProjectId(Long projectId) {
        List<CarbonProjectMonitoringData> list = this.lambdaQuery().eq(CarbonProjectMonitoringData::getCarbonProjectId, projectId).list();
        return BeanUtil.copyToList(list, CarbonProjectMonitoringDataQueryVo.class);
    }

}
