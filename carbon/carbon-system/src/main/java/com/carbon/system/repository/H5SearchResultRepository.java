package com.carbon.system.repository;


import com.carbon.system.entity.CarbonArticle;
import com.carbon.system.entity.CarbonH5SearchResult;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface H5SearchResultRepository extends ElasticsearchRepository<CarbonH5SearchResult, Long> {
}
