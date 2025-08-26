package com.carbon.assets.service;

import com.carbon.assets.entity.CarbonQuotaAssets;
import com.carbon.assets.vo.CarbonAssetsTotalVo;
import com.carbon.common.service.BaseService;
import com.carbon.assets.param.CarbonQuotaAssetsQueryParam;
import com.carbon.assets.vo.CarbonQuotaAssetsQueryVo;
import com.carbon.common.api.Paging;

import java.io.Serializable;

/**
 * <p>
 * 碳配额资产 服务类
 * </p>
 *
 * @author Li Jun
 * @since 2022-04-24
 */
public interface CarbonQuotaAssetsService extends BaseService<CarbonQuotaAssets> {

    /**
     * 根据ID获取查询对象
     * @param id 主键id
     * @return CarbonQuotaAssetsQueryVo
     */
    CarbonQuotaAssetsQueryVo getCarbonQuotaAssetsById(Serializable id);

    /**
     * 获取分页对象
     * @param param CarbonQuotaAssetsQueryParam
     * @return Paging<CarbonQuotaAssetsQueryVo>
     */
    Paging<CarbonQuotaAssetsQueryVo> getCarbonQuotaAssetsPageList(CarbonQuotaAssetsQueryParam param);

    CarbonAssetsTotalVo getCarbonAssetsTotal();

    void SendFeishuApprove(CarbonQuotaAssets carbonQuotaAssets);

}
