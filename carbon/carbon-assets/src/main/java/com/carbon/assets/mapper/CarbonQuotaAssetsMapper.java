package com.carbon.assets.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.carbon.assets.entity.CarbonQuotaAssets;
import com.carbon.assets.param.CarbonQuotaAssetsQueryParam;
import com.carbon.assets.vo.CarbonAssetsTotalVo;
import com.carbon.assets.vo.CarbonQuotaAssetsQueryVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * <p>
 * 碳配额资产 Mapper 接口
 * </p>
 *
 * @author Li Jun
 * @since 2022-04-24
 */
@Repository
public interface CarbonQuotaAssetsMapper extends BaseMapper<CarbonQuotaAssets> {

    /**
     * 根据ID获取查询对象
     * @param id 主键id
     * @return CarbonQuotaAssetsQueryVo
     */
    CarbonQuotaAssetsQueryVo getCarbonQuotaAssetsById(Serializable id);

    /**
     * 获取分页对象
     * @param page 分页参数
     * @param param 查询参数
     * @return IPage<CarbonQuotaAssetsQueryVo>
     */
    IPage<CarbonQuotaAssetsQueryVo> getCarbonQuotaAssetsPageList(@Param("page") Page<?> page, @Param("param") CarbonQuotaAssetsQueryParam param);

    CarbonAssetsTotalVo getCarbonAssetsTotal();
}