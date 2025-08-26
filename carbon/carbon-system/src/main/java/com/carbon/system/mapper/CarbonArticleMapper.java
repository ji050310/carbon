package com.carbon.system.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.carbon.system.entity.CarbonArticle;
import com.carbon.system.param.CarbonArticleQueryParam;
import com.carbon.system.vo.CarbonArticleQueryVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 碳文章 Mapper 接口
 * </p>
 *
 * @author Li Jun
 * @since 2021-08-01
 */
@Repository
public interface CarbonArticleMapper extends BaseMapper<CarbonArticle> {
    /**
     * 获取分页对象
     * @param page 分页参数
     * @param param 查询参数
     * @return IPage<CarbonArticleQueryVo>
     */
    IPage<CarbonArticleQueryVo> getCarbonArticlePageList(@Param("page") Page<?> page, @Param("param") CarbonArticleQueryParam param);

    /*
     * 飞书同步文章
     */
    void SyncArticle(@Param("id") Long id, @Param("title") String title,@Param("author") String author,
                     @Param("content") String content,@Param("status") String status,@Param("category_id") String category_id,@Param("updated_time") String updated_time);

    IPage<CarbonArticleQueryVo> getCarbonArticlePageList3(@Param("page") Page<?> page, @Param("param") CarbonArticleQueryParam param);

}
