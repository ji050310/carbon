package com.carbon.assets.service;

import com.carbon.assets.entity.CarbonProjectMonitoringData;
import com.carbon.common.service.BaseService;
import com.carbon.assets.param.CarbonProjectMonitoringDataQueryParam;
import com.carbon.assets.vo.CarbonProjectMonitoringDataQueryVo;
import com.carbon.common.api.Paging;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 碳减排项目监测数据 服务类
 * </p>
 *
 * @author Li Jun
 * @since 2022-05-11
 */
public interface CarbonProjectMonitoringDataService extends BaseService<CarbonProjectMonitoringData> {

    /**
     * 根据ID获取查询对象
     * @param id 主键id
     * @return CarbonProjectMonitoringDataQueryVo
     */
    CarbonProjectMonitoringDataQueryVo getCarbonProjectMonitoringDataById(Serializable id);

    /**
     * 获取分页对象
     * @param param CarbonProjectMonitoringDataQueryParam
     * @return Paging<CarbonProjectMonitoringDataQueryVo>
     */
    Paging<CarbonProjectMonitoringDataQueryVo> getCarbonProjectMonitoringDataPageList(CarbonProjectMonitoringDataQueryParam param);

    /**
     * 查询项目监测数据列表
     * @param projectId 项目id
     * @return List<CarbonProjectMonitoringDataQueryVo>
     */
    List<CarbonProjectMonitoringDataQueryVo> getListByProjectId(Long projectId);
}
