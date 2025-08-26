package com.carbon.assets.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.carbon.assets.entity.CarbonMetaregistryDoc;
import com.carbon.assets.mapper.CarbonMetaregistryDocMapper;
import com.carbon.assets.service.CarbonMetaregistryDocService;
import com.carbon.assets.param.CarbonMetaregistryDocQueryParam;
import com.carbon.assets.vo.CarbonMetaregistryDocQueryVo;
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
 *  服务实现类
 * </p>
 *
 * @author ChengJX
 * @since 2022-08-08
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class CarbonMetaregistryDocServiceImpl extends BaseServiceImpl<CarbonMetaregistryDocMapper, CarbonMetaregistryDoc> implements CarbonMetaregistryDocService {

    @Resource
    private CarbonMetaregistryDocMapper carbonMetaregistryDocMapper;

    @Override
    public CarbonMetaregistryDocQueryVo getCarbonMetaregistryDocById(Serializable id) {
        return carbonMetaregistryDocMapper.getCarbonMetaregistryDocById(id);
    }

    @Override
    public void uploadCarbonMetaRegistryDoc() {

    }

    @Override
    public void isDocRegistration() {

    }

    @Override
    public Paging<CarbonMetaregistryDocQueryVo> getCarbonMetaregistryDocPageList(CarbonMetaregistryDocQueryParam param) {
        IPage<CarbonMetaregistryDocQueryVo> iPage = carbonMetaregistryDocMapper.getCarbonMetaregistryDocPageList(getPage(param),param);
        return new Paging<>(iPage);
    }

    @Override
    public int updateByref(String url, String ref, String typeCode) {
        return carbonMetaregistryDocMapper.updateByref(url,ref,typeCode);
    }

}
