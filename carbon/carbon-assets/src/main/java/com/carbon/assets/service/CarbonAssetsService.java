package com.carbon.assets.service;

import com.carbon.common.api.Paging;
import com.carbon.common.service.BaseService;
import com.carbon.assets.entity.CarbonAssets;
import com.carbon.assets.param.CarbonAssetsQueryParam;
import com.carbon.assets.vo.CarbonAssetsQueryVo;

/**
 * <p>
 * 碳中和资产 服务类
 * </p>
 *
 * @author Li Jun
 * @since 2021-07-31
 */
public interface CarbonAssetsService extends BaseService<CarbonAssets> {

    /**
     * 根据ID获取查询对象
     * @param id 主键id
     * @return CarbonAssetsQueryVo
     */
    CarbonAssetsQueryVo getCarbonAssetsById(Long id);

    /**
     * 获取分页对象
     * @param param CarbonAssetsQueryParam
     * @return Paging<CarbonAssetsQueryVo>
     */
    Paging<CarbonAssetsQueryVo> getCarbonAssetsPageList(CarbonAssetsQueryParam param);

    /**
     * 添加中和资产
     * @param carbonAssets 参数
     */
    void addCarbonAssets(CarbonAssets carbonAssets);

    /**
     * 编辑中和资产
     * @param carbonAssets 参数
     * @return boolean
     */
    boolean updateCarbonAssets(CarbonAssets carbonAssets);

    /**
     * 中和资产核证
     * @param assetsId 中和资产id
     */
    void certified(String assetsId);
}
