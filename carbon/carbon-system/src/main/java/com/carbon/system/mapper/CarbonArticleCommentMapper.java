package com.carbon.system.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.carbon.system.entity.CarbonArticleComment;
import com.carbon.system.param.CarbonArticleCommentQueryParam;
import com.carbon.system.vo.CarbonArticleCommentQueryVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Jiang zhenhua
 * @since 2022-09-15
 */
@Repository
public interface CarbonArticleCommentMapper extends BaseMapper<CarbonArticleComment> {

    /**
     * 根据ID获取查询对象
     * @param id 主键id
     * @return CarbonArticleCommentQueryVo
     */
    CarbonArticleCommentQueryVo getCarbonArticleCommentById(Serializable id);

    /**
     * 获取分页对象
     * @param page 分页参数
     * @param param 查询参数
     * @return IPage<CarbonArticleCommentQueryVo>
     */
    IPage<CarbonArticleCommentQueryVo> getCarbonArticleCommentPageList(@Param("page") Page<?> page, @Param("param") CarbonArticleCommentQueryParam param);

}
