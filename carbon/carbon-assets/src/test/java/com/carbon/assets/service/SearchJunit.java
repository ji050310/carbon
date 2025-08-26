package com.carbon.assets.service;

import com.carbon.assets.AssetsApplication;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AssetsApplication.class)
public class SearchJunit {

    @Autowired
    private RestHighLevelClient client;

    @Autowired
    private RestHighLevelClient restHighLevelClient;


    /**
     * 单条件精确查询
     * @throws IOException
     */
    @Test
    public void search0() throws IOException {
        // 创建请求
        SearchSourceBuilder builder = new SearchSourceBuilder()
                .query(QueryBuilders.termsQuery("projectName","乾安网新才字乡风电场二期工程")
                );

        //搜索
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("esmetaregistry");
        searchRequest.types("_doc");
        searchRequest.source(builder);
        // 执行请求
        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
        // 解析查询结果
        System.out.println(response.toString());
    }

    @Test
    public void testReturn() throws IOException {
        SearchRequest searchRequest = new SearchRequest("esmetaregistry");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        boolQuery.must(QueryBuilders.matchQuery("projectCode","848"));

        searchSourceBuilder.fetchSource(new String[]{},new String[]{"content"});

        searchSourceBuilder.query(boolQuery);

        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

        System.out.println("总条数：" + searchResponse.getHits().getTotalHits().value);
        System.out.println("最大得分：" + searchResponse.getHits().getMaxScore());

        SearchHit[] hits = searchResponse.getHits().getHits();
        for (SearchHit hit : hits) {
            System.out.println(hit.getSourceAsString());
        }
    }
}

//    if (date != null){
//            try {
//            boolQuery.must(QueryBuilders.rangeQuery("statistical_time").from(format.parse(date).getTime()).to(new Date().getTime()));
//            } catch (ParseException e) {
//            e.printStackTrace();
//            }
//            }