package com.carbon.assets.repository;

import com.carbon.assets.entity.CarbonMethodologyContent;
import com.carbon.assets.entity.CarbonProjectContent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * 方法学ES操作类
 */
@Repository
public interface CarbonProjectRepository extends ElasticsearchRepository<CarbonProjectContent, String> {
    /**
     * 搜索查询
     */
//    Page<CarbonProjectContent> findById(String id, Pageable page);
//
//    Page<CarbonProjectContent> findByName(String Name, Pageable page);

    Page<CarbonProjectContent> findByProjectContentOrProjectNameOrProjectScope(String projectContent,String projectName,String ProjectScope, Pageable page);

//    Page<CarbonProjectContent> findByContents(String Content, Pageable page);

}
