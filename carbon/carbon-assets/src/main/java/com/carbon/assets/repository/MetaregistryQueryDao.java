package com.carbon.assets.repository;

import com.carbon.assets.vo.CarbonMetaregistryDocContentEsVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * 项目文档ES操作类
 */
@Repository
public interface MetaregistryQueryDao extends ElasticsearchRepository<CarbonMetaregistryDocContentEsVo, String> {
    /**
     * 搜索查询
     */
//    Page<CarbonProjectContent> findById(String id, Pageable page);
//
//    Page<CarbonProjectContent> findByName(String Name, Pageable page);

//    Page<CarbonMetaregistryDocContentEsVo> findByTitleOrProjectNameOrContentOrType(String title,String projectName,String ProjectScope,String Type,Pageable page);

//    Page<CarbonProjectContent> findByContents(String Content, Pageable page);

}
