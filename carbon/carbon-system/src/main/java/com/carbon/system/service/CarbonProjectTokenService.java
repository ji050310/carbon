package com.carbon.system.service;

import com.carbon.system.entity.CarbonProjectToken;
import com.carbon.common.service.BaseService;
import com.carbon.system.param.CarbonProjectTokenQueryParam;
import com.carbon.system.vo.CarbonProjectTokenQueryVo;
import com.carbon.common.api.Paging;

import java.io.Serializable;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Bae
 * @since 2022-06-27
 */
public interface CarbonProjectTokenService extends BaseService<CarbonProjectToken> {

    /**
     * 根据ID获取查询对象
     * @param id 主键id
     * @return CarbonProjectTokenQueryVo
     */
    CarbonProjectTokenQueryVo getCarbonProjectTokenById(Serializable id);

    /**
     * 获取分页对象
     * @param param CarbonProjectTokenQueryParam
     * @return Paging<CarbonProjectTokenQueryVo>
     */
    Paging<CarbonProjectTokenQueryVo> getCarbonProjectTokenPageList(CarbonProjectTokenQueryParam param);


    Long getProjectFileCode(String projectId,String projectName);

}
