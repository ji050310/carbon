package com.carbon.assets.repository;

import cn.hutool.json.JSONUtil;
import com.carbon.assets.vo.CarbonMetaregistryDocContentEsVo;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.elasticsearch.index.query.QueryBuilders.matchAllQuery;

@Slf4j
@Service
public class CarbonMetaregistryRepositoryImpl {
    @Autowired
    private  ElasticsearchOperations elasticsearchOperations;

    public List<CarbonMetaregistryDocContentEsVo> findAll() {
        log.info("开始执行 CarbonMetaregistryRepositoryImpl.findAll");
        String all = "{   \"_source\":{\"excludes\":[\"content\"]},\n" +
                "    \"from\":0,\n" +
                "    \"size\":10000,\n" +
                "    \"query\":{\n" +
                "        \"bool\":{\n" +
                "            \"should\":[\n" +
                "                {\n" +
                "                    \"exists\":{\n" +
                "                        \"field\":\"title\",\n" +
                "                        \"boost\":1\n" +
                "                    }\n" +
                "                },\n" +
                "                {\n" +
                "                    \"exists\":{\n" +
                "                        \"field\":\"projectName\",\n" +
                "                        \"boost\":1\n" +
                "                    }\n" +
                "                },\n" +
                "                {\n" +
                "                    \"exists\":{\n" +
                "                        \"field\":\"content\",\n" +
                "                        \"boost\":1\n" +
                "                    }\n" +
                "                },\n" +
                "                {\n" +
                "                    \"exists\":{\n" +
                "                        \"field\":\"type\",\n" +
                "                        \"boost\":1\n" +
                "                    }\n" +
                "                }\n" +
                "            ],\n" +
                "            \"adjust_pure_negative\":true,\n" +
                "            \"boost\":1\n" +
                "        }\n" +
                "    },\n" +
                "    \"post_filter\":{\n" +
                "        \"bool\":{\n" +
                "            \"must\":[\n" +
                "                {\n" +
                "                    \"bool\":{\n" +
                "                        \"adjust_pure_negative\":true,\n" +
                "                        \"boost\":1\n" +
                "                    }\n" +
                "                },\n" +
                "                {\n" +
                "                    \"bool\":{\n" +
                "                        \"adjust_pure_negative\":true,\n" +
                "                        \"boost\":1\n" +
                "                    }\n" +
                "                },\n" +
                "                {\n" +
                "                    \"bool\":{\n" +
                "                        \"adjust_pure_negative\":true,\n" +
                "                        \"boost\":1\n" +
                "                    }\n" +
                "                }\n" +
                "            ],\n" +
                "            \"adjust_pure_negative\":true,\n" +
                "            \"boost\":1\n" +
                "        }\n" +
                "    },\n" +
                "    \"version\":true\n" +
                "}";



        StringQuery query = new StringQuery(all);
        SearchHits<CarbonMetaregistryDocContentEsVo> list =  elasticsearchOperations.search(query,CarbonMetaregistryDocContentEsVo.class);
        log.info("{}", JSONUtil.toJsonStr(list));
        List<CarbonMetaregistryDocContentEsVo> l =  list.
                                                    stream().collect(Collectors.toList()).
                                                    stream().map(e->e.getContent()).collect(Collectors.toList());
        log.info("{}", JSONUtil.toJsonStr(l));
        return l;
    }
}
