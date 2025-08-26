package com.carbon.assets.service;

import com.carbon.assets.entity.CarbonMethodology;
import com.carbon.assets.entity.CarbonMethodologyContent;
import com.carbon.common.service.BaseService;
import com.carbon.assets.param.CarbonMethodologyContentQueryParam;
import com.carbon.assets.vo.CarbonMethodologyContentQueryVo;
import com.carbon.common.api.Paging;

import java.io.Serializable;

/**
 * <p>
 * 碳减排方法学文档 服务类
 * </p>
 *
 * @author Cbd
 * @since 2022-08-09
 */
public interface CarbonMethodologyContentService extends BaseService<CarbonMethodologyContent> {

    /**
     * 根据ID获取查询对象
     * @param id 主键id
     * @return CarbonMethodologyContentQueryVo
     */
    CarbonMethodologyContentQueryVo getCarbonMethodologyContentById(Serializable id);

    /**
     * 获取分页对象
     * @param param CarbonMethodologyContentQueryParam
     * @return Paging<CarbonMethodologyContentQueryVo>
     */
    Paging<CarbonMethodologyContentQueryVo> getCarbonMethodologyContentPageList(CarbonMethodologyContentQueryParam param);

    CarbonMethodologyContent getCarbonMethodologyById(String code);
}
