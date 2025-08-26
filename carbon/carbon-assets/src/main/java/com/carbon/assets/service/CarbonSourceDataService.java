package com.carbon.assets.service;

import com.carbon.common.api.Paging;
import com.carbon.common.service.BaseService;
import com.carbon.assets.entity.CarbonSourceData;
import com.carbon.assets.param.CarbonSourceDataQueryParam;
import com.carbon.assets.vo.CarbonSourceDataQueryVo;

/**
 * <p>
 * 碳源数据 服务类
 * </p>
 *
 * @author Li Jun
 * @since 2021-08-07
 */
public interface CarbonSourceDataService extends BaseService<CarbonSourceData> {

    /**
     * 根据ID获取查询对象
     * @param id 主键id
     * @return CarbonSourceDataQueryVo
     */
    CarbonSourceDataQueryVo getCarbonSourceDataById(Long id);

    /**
     * 获取分页对象
     * @param param CarbonSourceDataQueryParam
     * @return Paging<CarbonSourceDataQueryVo>
     */
    Paging<CarbonSourceDataQueryVo> getCarbonSourceDataPageList(CarbonSourceDataQueryParam param);

    /**
     * 添加碳数据源
     * @param carbonSourceData 参数
     */
    void addCarbonSourceData(CarbonSourceData carbonSourceData);

    /**
     * 修改碳数据源
     * @param carbonSourceData 参数
     * @return boolean
     */
    boolean updateCarbonSourceData(CarbonSourceData carbonSourceData);

    /**
     * 碳数据报送长安链
     * @param carbonDataId 碳数据id
     */
    void submitted(String carbonDataId);
}
