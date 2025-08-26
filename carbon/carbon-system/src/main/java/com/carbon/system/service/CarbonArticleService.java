package com.carbon.system.service;

import com.carbon.system.entity.CarbonArticle;
import com.carbon.common.service.BaseService;
import com.carbon.system.param.CarbonArticleQueryParam;
import com.carbon.system.param.CarbonArticleStatuParam;
import com.carbon.system.vo.CarbonArticleAddVo;
import com.carbon.system.vo.CarbonArticleQueryVo;
import com.carbon.common.api.Paging;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 碳文章 服务类
 * </p>
 *
 * @author Li Jun
 * @since 2021-08-01
 */
public interface CarbonArticleService extends BaseService<CarbonArticle> {
    /**
     * 根据ID获取查询对象
     * @param id 主键id
     * @return CarbonArticleQueryVo
     */
    CarbonArticleQueryVo getCarbonArticleById(Serializable id);

    /**
     * 获取分页对象
     * @param param CarbonArticleQueryParam
     * @return Paging<CarbonArticleQueryVo>
     */
    Paging<CarbonArticleQueryVo> getCarbonArticlePageList(CarbonArticleQueryParam param);


    Paging<CarbonArticleQueryVo> getCarbonArticlePageList2(CarbonArticleQueryParam param);

    Paging<CarbonArticleQueryVo> getCarbonArticlePageList3(CarbonArticleQueryParam param);

    /**
     * 根据文章id修改文章的状态
     * @param param CarbonArticleStatuParam
     * @return boolean
     */
    boolean updateArticleStatu(CarbonArticleStatuParam param);

    CarbonArticleAddVo testFeishu();

    CarbonArticleAddVo testFeishu2();


    CarbonArticleAddVo pushFeishu(CarbonArticle article);


    public CarbonArticleAddVo updateFeishu(CarbonArticle article);
    /*
     *   同步文章
     */
    void SyncArticle(List<Map<String, Object>> articleUrlList);
}
