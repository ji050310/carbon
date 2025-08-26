package com.carbon.system.repository;

import com.carbon.system.entity.CarbonArticle;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;

@Repository
public interface CarbonArticleRepository extends ElasticsearchRepository<CarbonArticle, Long> {
    Page<CarbonArticle> findByContent(String keyword, Pageable page);
}
