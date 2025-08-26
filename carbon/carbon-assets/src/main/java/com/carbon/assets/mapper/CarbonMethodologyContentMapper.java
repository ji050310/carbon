package com.carbon.assets.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.carbon.assets.entity.CarbonMethodologyContent;
import com.carbon.assets.param.CarbonMethodologyContentQueryParam;
import com.carbon.assets.vo.CarbonMethodologyContentQueryVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * <p>
 * 碳减排方法学文档 Mapper 接口
 * </p>
 *
 * @author Cbd
 * @since 2022-08-09
 */
@Repository
public interface CarbonMethodologyContentMapper extends BaseMapper<CarbonMethodologyContent> {

    /**
     * 根据ID获取查询对象
     * @param id 主键id
     * @return CarbonMethodologyContentQueryVo
     */
    CarbonMethodologyContentQueryVo getCarbonMethodologyContentById(Serializable id);

    /**
     * 获取分页对象
     * @param page 分页参数
     * @param param 查询参数
     * @return IPage<CarbonMethodologyContentQueryVo>
     */
    IPage<CarbonMethodologyContentQueryVo> getCarbonMethodologyContentPageList(@Param("page") Page<?> page, @Param("param") CarbonMethodologyContentQueryParam param);

    CarbonMethodologyContent getCarbonMethodologyById(String code);
}
