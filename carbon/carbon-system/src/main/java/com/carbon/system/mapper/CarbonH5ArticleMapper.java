package com.carbon.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.carbon.system.entity.CarbonArticle;
import com.carbon.system.param.CarbonArticleQueryParam;
import com.carbon.system.vo.CarbonArticleQueryVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CarbonH5ArticleMapper extends BaseMapper<CarbonArticle> {

    /**
    * @Description: 根据页数和大小查询资讯
    * @Author: Code534
    */
    IPage<CarbonArticleQueryVo> getPageList(@Param("page") Page<?> page, @Param("param") CarbonArticleQueryParam param);


    String getContentById(@Param("id") Long id);
    Map getMethodologyByPrimaryKey(@Param("id") Long id);

    List<Long> getAllMethodology();
}
