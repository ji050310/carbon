package com.carbon.assets.service;

import com.carbon.common.api.Paging;
import com.carbon.common.service.BaseService;
import com.carbon.assets.entity.CarbonAssetsBusiness;
import com.carbon.assets.param.CarbonAssetsBusinessQueryParam;
import com.carbon.assets.vo.CarbonAssetsBusinessQueryVo;

/**
 * <p>
 * 中和资产交易  服务类
 * </p>
 *
 * @author Li Jun
 * @since 2021-09-24
 */
public interface CarbonAssetsBusinessService extends BaseService<CarbonAssetsBusiness> {

    /**
     * 根据ID获取查询对象
     * @param id 主键id
     * @return CarbonAssetsBusinessQueryVo
     */
    CarbonAssetsBusinessQueryVo getCarbonAssetsBusinessById(Long id);

    /**
     * 获取分页对象
     * @param param CarbonAssetsBusinessQueryParam
     * @return Paging<CarbonAssetsBusinessQueryVo>
     */
    Paging<CarbonAssetsBusinessQueryVo> getCarbonAssetsBusinessPageList(CarbonAssetsBusinessQueryParam param);

    /**
     * 添加交易
     * @param assetsBusiness
     */
    void addCarbonAssetsBusiness(CarbonAssetsBusiness assetsBusiness);
}
