package com.carbon.assets.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.carbon.assets.entity.CarbonProjectMonitoringData;
import com.carbon.assets.param.CarbonProjectMonitoringDataQueryParam;
import com.carbon.assets.vo.CarbonProjectMonitoringDataQueryVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * <p>
 * 碳减排项目监测数据 Mapper 接口
 * </p>
 *
 * @author Li Jun
 * @since 2022-05-11
 */
@Repository
public interface CarbonProjectMonitoringDataMapper extends BaseMapper<CarbonProjectMonitoringData> {

    /**
     * 根据ID获取查询对象
     * @param id 主键id
     * @return CarbonProjectMonitoringDataQueryVo
     */
    CarbonProjectMonitoringDataQueryVo getCarbonProjectMonitoringDataById(Serializable id);

    /**
     * 获取分页对象
     * @param page 分页参数
     * @param param 查询参数
     * @return IPage<CarbonProjectMonitoringDataQueryVo>
     */
    IPage<CarbonProjectMonitoringDataQueryVo> getCarbonProjectMonitoringDataPageList(@Param("page") Page<?> page, @Param("param") CarbonProjectMonitoringDataQueryParam param);

}
