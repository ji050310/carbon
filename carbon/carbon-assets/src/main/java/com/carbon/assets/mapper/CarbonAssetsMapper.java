package com.carbon.assets.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.carbon.assets.entity.CarbonAssets;
import com.carbon.assets.param.CarbonAssetsQueryParam;
import com.carbon.assets.vo.CarbonAssetsQueryVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * <p>
 * 碳中和资产 Mapper 接口
 * </p>
 *
 * @author Li Jun
 * @since 2021-07-31
 */
@Repository
public interface CarbonAssetsMapper extends BaseMapper<CarbonAssets> {


    /**
     * 获取分页对象
     * @param page 分页参数
     * @param param 查询参数
     * @return IPage<CarbonAssetsQueryVo>
     */
    IPage<CarbonAssetsQueryVo> getCarbonAssetsPageList(@Param("page") Page<?> page, @Param("param") CarbonAssetsQueryParam param);

}
