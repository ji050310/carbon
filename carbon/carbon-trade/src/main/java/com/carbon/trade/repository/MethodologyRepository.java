package com.carbon.trade.repository;

import com.carbon.trade.entity.EsCarbonMethodology;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * 搜索行情ES操作类
 */
@Repository
public interface MethodologyRepository extends ElasticsearchRepository<EsCarbonMethodology, String> {
    /**
     * 搜索查询
     */
    Page<EsCarbonMethodology> findById(String id, Pageable page);

    Page<EsCarbonMethodology> findByName(String Name, Pageable page);

    Page<EsCarbonMethodology> findByContentOrName(String content,String name, Pageable page);
//
//    Page<CarbonMethodology> findByContents(String Content, Pageable page);

}
