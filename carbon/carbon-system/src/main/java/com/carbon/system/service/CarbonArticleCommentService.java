package com.carbon.system.service;

import com.carbon.system.entity.CarbonArticleComment;
import com.carbon.common.service.BaseService;
import com.carbon.system.param.CarbonArticleCommentQueryParam;
import com.carbon.system.vo.CarbonArticleCommentQueryVo;
import com.carbon.common.api.Paging;

import java.io.Serializable;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Jiang zhenhua
 * @since 2022-09-15
 */
public interface CarbonArticleCommentService extends BaseService<CarbonArticleComment> {

    /**
     * 根据ID获取查询对象
     * @param id 主键id
     * @return CarbonArticleCommentQueryVo
     */
    CarbonArticleCommentQueryVo getCarbonArticleCommentById(Serializable id);

    /**
     * 获取分页对象
     * @param param CarbonArticleCommentQueryParam
     * @return Paging<CarbonArticleCommentQueryVo>
     */
    Paging<CarbonArticleCommentQueryVo> getCarbonArticleCommentPageList(CarbonArticleCommentQueryParam param);

}
