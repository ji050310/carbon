package com.carbon.assets.service.impl;

import com.carbon.assets.entity.CarbonMethodology;
import com.carbon.assets.entity.CarbonMethodologyContent;
import com.carbon.assets.mapper.CarbonMethodologyContentMapper;
import com.carbon.assets.service.CarbonMethodologyContentService;
import com.carbon.assets.param.CarbonMethodologyContentQueryParam;
import com.carbon.assets.vo.CarbonMethodologyContentQueryVo;
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
 * 碳减排方法学文档 服务实现类
 * </p>
 *
 * @author Cbd
 * @since 2022-08-09
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class CarbonMethodologyContentServiceImpl extends BaseServiceImpl<CarbonMethodologyContentMapper, CarbonMethodologyContent> implements CarbonMethodologyContentService {

    @Resource
    private CarbonMethodologyContentMapper carbonMethodologyContentMapper;

    @Override
    public CarbonMethodologyContentQueryVo getCarbonMethodologyContentById(Serializable id) {
        return carbonMethodologyContentMapper.getCarbonMethodologyContentById(id);
    }

    @Override
    public Paging<CarbonMethodologyContentQueryVo> getCarbonMethodologyContentPageList(CarbonMethodologyContentQueryParam param) {
        IPage<CarbonMethodologyContentQueryVo> iPage = carbonMethodologyContentMapper.getCarbonMethodologyContentPageList(getPage(param),param);
        return new Paging<>(iPage);
    }

    @Override
    public CarbonMethodologyContent getCarbonMethodologyById(String code) {
        try {
            return carbonMethodologyContentMapper.getCarbonMethodologyById(code);
        }
        catch (Exception e)
        {
            return null;
        }
    }


}
