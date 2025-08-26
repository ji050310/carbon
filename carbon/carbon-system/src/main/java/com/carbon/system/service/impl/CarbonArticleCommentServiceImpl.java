package com.carbon.system.service.impl;

import com.carbon.system.entity.CarbonArticleComment;
import com.carbon.system.mapper.CarbonArticleCommentMapper;
import com.carbon.system.service.CarbonArticleCommentService;
import com.carbon.system.param.CarbonArticleCommentQueryParam;
import com.carbon.system.vo.CarbonArticleCommentQueryVo;
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
 * @author Jiang zhenhua
 * @since 2022-09-15
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class CarbonArticleCommentServiceImpl extends BaseServiceImpl<CarbonArticleCommentMapper, CarbonArticleComment> implements CarbonArticleCommentService {

    @Resource
    private CarbonArticleCommentMapper carbonArticleCommentMapper;

    @Override
    public CarbonArticleCommentQueryVo getCarbonArticleCommentById(Serializable id) {
        return carbonArticleCommentMapper.getCarbonArticleCommentById(id);
    }

    @Override
    public Paging<CarbonArticleCommentQueryVo> getCarbonArticleCommentPageList(CarbonArticleCommentQueryParam param) {
        IPage<CarbonArticleCommentQueryVo> iPage = carbonArticleCommentMapper.getCarbonArticleCommentPageList(getPage(param),param);
        return new Paging<>(iPage);
    }

}
