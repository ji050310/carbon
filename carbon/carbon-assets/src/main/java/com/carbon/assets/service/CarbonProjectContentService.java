package com.carbon.assets.service;

import com.carbon.assets.entity.CarbonMethodologyContent;
import com.carbon.assets.entity.CarbonProjectContent;
import com.carbon.common.service.BaseService;
import com.carbon.assets.param.CarbonProjectContentQueryParam;
import com.carbon.assets.vo.CarbonProjectContentQueryVo;
import com.carbon.common.api.Paging;

import java.io.Serializable;

/**
 * <p>
 * 碳减排项目文档内容 服务类
 * </p>
 *
 * @author Cbd
 * @since 2022-08-10
 */
public interface CarbonProjectContentService extends BaseService<CarbonProjectContent> {

    /**
     * 根据ID获取查询对象
     * @param id 主键id
     * @return CarbonProjectContentQueryVo
     */
    CarbonProjectContentQueryVo getCarbonProjectContentById(Serializable id);

    /**
     * 获取分页对象
     * @param param CarbonProjectContentQueryParam
     * @return Paging<CarbonProjectContentQueryVo>
     */
    Paging<CarbonProjectContentQueryVo> getCarbonProjectContentPageList(CarbonProjectContentQueryParam param);
}
