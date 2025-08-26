package com.carbon.system.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.carbon.system.entity.CarbonProjectToken;
import com.carbon.system.param.CarbonProjectTokenQueryParam;
import com.carbon.system.vo.CarbonProjectTokenQueryVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Bae
 * @since 2022-06-27
 */
@Repository
public interface CarbonProjectTokenMapper extends BaseMapper<CarbonProjectToken> {

    /**
     * 根据ID获取查询对象
     * @param id 主键id
     * @return CarbonProjectTokenQueryVo
     */
    CarbonProjectTokenQueryVo getCarbonProjectTokenById(Serializable id);

    /**
     * 获取分页对象
     * @param page 分页参数
     * @param param 查询参数
     * @return IPage<CarbonProjectTokenQueryVo>
     */
    IPage<CarbonProjectTokenQueryVo> getCarbonProjectTokenPageList(@Param("page") Page<?> page, @Param("param") CarbonProjectTokenQueryParam param);

}
