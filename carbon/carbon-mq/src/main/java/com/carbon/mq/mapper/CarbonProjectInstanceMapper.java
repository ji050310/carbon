package com.carbon.mq.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.carbon.mq.entity.CarbonProjectInstance;
import com.carbon.mq.param.CarbonProjectInstanceQueryParam;
import com.carbon.mq.vo.CarbonProjectInstanceQueryVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author JinRui Zhang
 * @since 2022-06-21
 */
@Repository
public interface CarbonProjectInstanceMapper extends BaseMapper<CarbonProjectInstance> {


    /**
     * 根据ID获取查询对象
     * @param id 主键id
     * @return CarbonProjectInstanceQueryVo
     */
    CarbonProjectInstanceQueryVo getCarbonProjectInstanceById(Serializable id);

    /**
     * 获取分页对象
     * @param page 分页参数
     * @param param 查询参数
     * @return IPage<CarbonProjectInstanceQueryVo>
     */
    IPage<CarbonProjectInstanceQueryVo> getCarbonProjectInstancePageList(@Param("page") Page<?> page, @Param("param") CarbonProjectInstanceQueryParam param);

}
