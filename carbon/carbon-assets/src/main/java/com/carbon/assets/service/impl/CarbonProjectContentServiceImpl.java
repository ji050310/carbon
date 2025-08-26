package com.carbon.assets.service.impl;

import com.carbon.assets.entity.CarbonProjectContent;
import com.carbon.assets.mapper.CarbonProjectContentMapper;
import com.carbon.assets.service.CarbonProjectContentService;
import com.carbon.assets.param.CarbonProjectContentQueryParam;
import com.carbon.assets.vo.CarbonProjectContentQueryVo;
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
 * 碳减排项目文档内容 服务实现类
 * </p>
 *
 * @author Cbd
 * @since 2022-08-10
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class CarbonProjectContentServiceImpl extends BaseServiceImpl<CarbonProjectContentMapper, CarbonProjectContent> implements CarbonProjectContentService {

    @Resource
    private CarbonProjectContentMapper carbonProjectContentMapper;

    @Override
    public CarbonProjectContentQueryVo getCarbonProjectContentById(Serializable id) {
        return carbonProjectContentMapper.getCarbonProjectContentById(id);
    }

    @Override
    public Paging<CarbonProjectContentQueryVo> getCarbonProjectContentPageList(CarbonProjectContentQueryParam param) {
        IPage<CarbonProjectContentQueryVo> iPage = carbonProjectContentMapper.getCarbonProjectContentPageList(getPage(param),param);
        return new Paging<>(iPage);
    }

}
