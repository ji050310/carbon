package com.carbon.assets.repository;

import com.carbon.assets.entity.CarbonMethodologyContent;
import com.carbon.assets.entity.EsCarbonMethodology;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * 方法学ES操作类
 */
@Repository
public interface MethodologyRepository extends ElasticsearchRepository<CarbonMethodologyContent, String> {
    /**
     * 搜索查询
     */
    Page<CarbonMethodologyContent> findById(String id, Pageable page);

    Page<CarbonMethodologyContent> findByName(String Name, Pageable page);

    Page<CarbonMethodologyContent> findByNameOrContentOrIndustryCodeNameOrDictCode(String content,String name,String IndustryCodeName,String dictCode,Pageable page);
//
//    Page<CarbonMethodology> findByContents(String Content, Pageable page);

}
