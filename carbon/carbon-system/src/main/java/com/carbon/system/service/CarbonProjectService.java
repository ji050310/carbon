package com.carbon.system.service;

import com.carbon.common.api.Paging;
import com.carbon.common.service.BaseService;
import com.carbon.system.entity.CarbonArticle;
import com.carbon.system.entity.CarbonProject;
import com.carbon.system.param.CarbonArticleQueryParam;
import com.carbon.system.param.CarbonArticleStatuParam;
import com.carbon.system.vo.CarbonArticleAddVo;
import com.carbon.system.vo.CarbonArticleQueryVo;

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
public interface CarbonProjectService extends BaseService<CarbonProject> {
    void updateInitiationDate(String date,Long id);
}
