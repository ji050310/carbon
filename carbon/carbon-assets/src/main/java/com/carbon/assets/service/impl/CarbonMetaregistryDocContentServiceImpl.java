package com.carbon.assets.service.impl;

import com.carbon.assets.entity.CarbonMetaregistryDocContent;
import com.carbon.assets.mapper.CarbonMetaregistryDocContentMapper;
import com.carbon.assets.service.CarbonMetaregistryDocContentService;
import com.carbon.assets.param.CarbonMetaregistryDocContentQueryParam;
import com.carbon.assets.vo.CarbonMetaregistryDocContentEsVo;
import com.carbon.assets.vo.CarbonMetaregistryDocContentQueryVo;
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
 * 项目元注册表内容 服务实现类
 * </p>
 *
 * @author Cbd
 * @since 2022-08-15
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class CarbonMetaregistryDocContentServiceImpl extends BaseServiceImpl<CarbonMetaregistryDocContentMapper, CarbonMetaregistryDocContent> implements CarbonMetaregistryDocContentService {

    @Resource
    private CarbonMetaregistryDocContentMapper carbonMetaregistryDocContentMapper;

    @Override
    public CarbonMetaregistryDocContentQueryVo getCarbonMetaregistryDocContentById(Serializable id) {
        return carbonMetaregistryDocContentMapper.getCarbonMetaregistryDocContentById(id);
    }

    @Override
    public Paging<CarbonMetaregistryDocContentQueryVo> getCarbonMetaregistryDocContentPageList(CarbonMetaregistryDocContentQueryParam param) {
        IPage<CarbonMetaregistryDocContentQueryVo> iPage = carbonMetaregistryDocContentMapper.getCarbonMetaregistryDocContentPageList(getPage(param),param);
        return new Paging<>(iPage);
    }




//    @Override
//    public CarbonMetaregistryDocContentEsVo getMetaregistryContentByRefId(String refId,String typeCode) {
//        return carbonMetaregistryDocContentMapper.getMetaregistryContentByRefId(refId,typeCode);
//    }

    @Override
    public CarbonMetaregistryDocContentEsVo getMetaregistryContentByRefIdEs(String refId,String typeCode) {
        return carbonMetaregistryDocContentMapper.getMetaregistryContentByRefIdEs(refId,typeCode);
    }

    @Override
    public CarbonMetaregistryDocContentEsVo getMetaregistryContentByRefId(String refId, String typeCode) {
        return null;
    }
}
