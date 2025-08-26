package com.carbon.assets.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.carbon.assets.entity.CarbonAssetsBusiness;
import com.carbon.assets.param.CarbonAssetsBusinessQueryParam;
import com.carbon.assets.vo.CarbonAssetsBusinessQueryVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * <p>
 * 中和资产交易  Mapper 接口
 * </p>
 *
 * @author Li Jun
 * @since 2021-09-24
 */
@Repository
public interface CarbonAssetsBusinessMapper extends BaseMapper<CarbonAssetsBusiness> {

    /**
     * 获取分页对象
     * @param page 分页参数
     * @param param 查询参数
     * @return IPage<CarbonAssetsBusinessQueryVo>
     */
    IPage<CarbonAssetsBusinessQueryVo> getCarbonAssetsBusinessPageList(@Param("page") Page<?> page, @Param("param") CarbonAssetsBusinessQueryParam param);

}
