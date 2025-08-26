package com.carbon.assets.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.carbon.assets.entity.CarbonMethodology;
import com.carbon.assets.param.CarbonMethodologyQueryParam;
import com.carbon.assets.vo.CarbonMethodologyQueryVo;
import com.carbon.assets.vo.CarbonMethodologySelectVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public interface CarbonMethodologyMapper extends BaseMapper<CarbonMethodology> {

    /**
     * 根据ID获取查询对象
     * @param id 主键id
     * @return CarbonMethodologyQueryVo
     */
    CarbonMethodologyQueryVo getCarbonMethodologyById(Serializable id);

    /**
     * 根据ID获取查询对象
     * @param id 主键id
     * @return CarbonMethodologyQueryVo
     */
    CarbonMethodologyQueryVo getCarbonMethodologyByDictCode(String id);

    /**
     * 获取分页对象
     * @param page 分页参数
     * @param param 查询参数
     * @return IPage<CarbonMethodologyQueryVo>
     */
    IPage<CarbonMethodologyQueryVo> getCarbonMethodologyPageList(@Param("page") Page<?> page, @Param("param") CarbonMethodologyQueryParam param);

    /**
     * 方法学下拉选择列表
     * @return CarbonMethodologySelectVo
     */
    List<CarbonMethodologySelectVo> getCarbonMethodologyList();

    CarbonMethodology getCarbonMethodologyByName(String name);
    CarbonMethodologyQueryVo getByCode(@Param("code") String Code);
}