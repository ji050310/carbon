package com.carbon.mq.service;

import com.carbon.mq.entity.CarbonProjectInstance;
import com.carbon.common.service.BaseService;
import com.carbon.mq.param.CarbonProjectInstanceQueryParam;
import com.carbon.mq.vo.CarbonProjectInstanceQueryVo;
import com.carbon.common.api.Paging;

import java.io.Serializable;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author JinRui Zhang
 * @since 2022-06-21
 */
public interface CarbonProjectInstanceService extends BaseService<CarbonProjectInstance> {

    /**
     * 根据ID获取查询对象
     * @param id 主键id
     * @return CarbonProjectInstanceQueryVo
     */
    CarbonProjectInstanceQueryVo getCarbonProjectInstanceById(Serializable id);

    /**
     * 获取分页对象
     * @param param CarbonProjectInstanceQueryParam
     * @return Paging<CarbonProjectInstanceQueryVo>
     */
    Paging<CarbonProjectInstanceQueryVo> getCarbonProjectInstancePageList(CarbonProjectInstanceQueryParam param);

}
