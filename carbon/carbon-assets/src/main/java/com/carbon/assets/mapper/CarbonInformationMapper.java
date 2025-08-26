package com.carbon.assets.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.carbon.assets.entity.CarbonInformation;
import com.carbon.assets.param.CarbonInformationQueryParam;
import com.carbon.assets.vo.CarbonInformationQueryVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 碳资讯 Mapper 接口
 * </p>
 *
 * @author Li Jun
 * @since 2022-01-11
 */
@Repository
public interface CarbonInformationMapper extends BaseMapper<CarbonInformation> {

    /**
     * 根据ID获取查询对象
     * @param id 主键id
     * @return CarbonInformationQueryVo
     */
    CarbonInformationQueryVo getCarbonInformationById(Serializable id);

    /**
     * 获取分页对象
     * @param page 分页参数
     * @param param 查询参数
     * @return IPage<CarbonInformationQueryVo>
     */
    IPage<CarbonInformationQueryVo> getCarbonInformationPageList(@Param("page") Page<?> page, @Param("param") CarbonInformationQueryParam param);

    List<CarbonInformationQueryVo> getRandomList(@Param("num") Integer num);
}
