package com.carbon.trade.repository;

import com.carbon.trade.vo.CarbonTradeQuoteQueryVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * 搜索行情ES操作类
 */
public interface EsCarbonTradeQuoteRepository extends ElasticsearchRepository<CarbonTradeQuoteQueryVo, Long> {
    /**
     * 搜索查询
     */
    Page<CarbonTradeQuoteQueryVo> findByInstitutionName(String institutionName, Pageable page);

}
