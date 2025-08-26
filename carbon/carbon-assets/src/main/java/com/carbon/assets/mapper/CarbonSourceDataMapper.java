package com.carbon.assets.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.carbon.assets.entity.CarbonSourceData;
import com.carbon.assets.param.CarbonSourceDataQueryParam;
import com.carbon.assets.vo.CarbonSourceDataQueryVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * <p>
 * 碳源数据 Mapper 接口
 * </p>
 *
 * @author Li Jun
 * @since 2021-08-07
 */
@Repository
public interface CarbonSourceDataMapper extends BaseMapper<CarbonSourceData> {


    /**
     * 获取分页对象
     * @param page 分页参数
     * @param param 查询参数
     * @return IPage<CarbonSourceDataQueryVo>
     */
    IPage<CarbonSourceDataQueryVo> getCarbonSourceDataPageList(@Param("page") Page<?> page, @Param("param") CarbonSourceDataQueryParam param);

}
