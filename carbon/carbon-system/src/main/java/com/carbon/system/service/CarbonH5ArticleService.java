package com.carbon.system.service;

import com.carbon.common.api.Paging;
import com.carbon.common.service.BaseService;
import com.carbon.system.controller.CarbonH5ArticleController;
import com.carbon.system.entity.CarbonArticle;
import com.carbon.system.entity.CarbonMethodologyContent;
import com.carbon.system.param.CarbonArticleQueryParam;
import com.carbon.system.param.CarbonH5ArticleKeyWordSearchParam;
import com.carbon.system.vo.CarbonArticleQueryVo;

import java.io.Serializable;

public interface CarbonH5ArticleService extends BaseService<CarbonArticle> {


    /**
    * @Description: 分页查询碳文章
    * @Param:
    * @return:
    * @Author: Code534
    * @Date:
    */
    Paging<CarbonArticleQueryVo> getCarbonArticlePageList(CarbonArticleQueryParam param);

    /**
     * 根据ID获取查询对象
     * @param id 主键id
     * @return CarbonArticleQueryVo
     */
    CarbonArticleQueryVo getCarbonH5ArticleById(Serializable id);

    /**
     * 默认搜索
     */
    Paging<CarbonArticleQueryVo> searchByKeyword(CarbonH5ArticleController.H5SearchDefaultParam param);

    /**
     * 搜索方法学
     */
    Paging<CarbonMethodologyContent> searchMethodologyByKeyword(CarbonH5ArticleController.H5SearchMethodologyParam param);

    /**
     * 搜索碳资讯文章
     */
    Paging<CarbonArticleQueryVo> searchArticleByKeyword(CarbonH5ArticleKeyWordSearchParam param);
}
