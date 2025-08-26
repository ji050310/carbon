package com.carbon.assets.service.impl;

import com.carbon.assets.entity.CarbonMetaregistryProject;
import com.carbon.assets.mapper.CarbonMetaregistryProjectMapper;
import com.carbon.assets.service.CarbonMetaregistryProjectService;
import com.carbon.assets.param.CarbonMetaregistryProjectQueryParam;
import com.carbon.assets.vo.CarbonMetaregistryProjectQueryVo;
import com.carbon.common.service.BaseServiceImpl;
import com.carbon.common.api.Paging;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.io.Serializable;


/**
 * <p>
 * 租户碳减排项目 服务实现类
 * </p>
 *
 * @author ChengJX
 * @since 2022-08-22
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class CarbonMetaregistryProjectServiceImpl extends BaseServiceImpl<CarbonMetaregistryProjectMapper, CarbonMetaregistryProject> implements CarbonMetaregistryProjectService {

    @Resource
    private CarbonMetaregistryProjectMapper carbonMetaregistryProjectMapper;

    @Override
    public CarbonMetaregistryProjectQueryVo getCarbonMetaregistryProjectById(Serializable id) {
        return carbonMetaregistryProjectMapper.getCarbonMetaregistryProjectById(id);
    }

    @Override
    public Paging<CarbonMetaregistryProjectQueryVo> getCarbonMetaregistryProjectPageList(CarbonMetaregistryProjectQueryParam param) {
        IPage<CarbonMetaregistryProjectQueryVo> iPage = carbonMetaregistryProjectMapper.getCarbonMetaregistryProjectPageList(getPage(param),param);
        return new Paging<>(iPage);
    }

}
