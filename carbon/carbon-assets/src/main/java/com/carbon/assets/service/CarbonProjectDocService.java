package com.carbon.assets.service;

import com.carbon.assets.entity.CarbonProjectDoc;
import com.carbon.common.service.BaseService;
import com.carbon.assets.param.CarbonProjectDocQueryParam;
import com.carbon.assets.vo.CarbonProjectDocQueryVo;
import com.carbon.common.api.Paging;

import java.io.Serializable;

/**
 * <p>
 * 碳减排项目文档 服务类
 * </p>
 *
 * @author Li Jun
 * @since 2022-04-24
 */
public interface CarbonProjectDocService extends BaseService<CarbonProjectDoc> {

    /**
     * 根据ID获取查询对象
     * @param id 主键id
     * @return CarbonProjectDocQueryVo
     */
    CarbonProjectDocQueryVo getCarbonProjectDocById(Serializable id);

    /**
     * 获取分页对象
     * @param param CarbonProjectDocQueryParam
     * @return Paging<CarbonProjectDocQueryVo>
     */
    Paging<CarbonProjectDocQueryVo> getCarbonProjectDocPageList(CarbonProjectDocQueryParam param);

}
