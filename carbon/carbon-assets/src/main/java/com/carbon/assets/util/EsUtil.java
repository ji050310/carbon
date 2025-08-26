package com.carbon.assets.util;

import com.carbon.assets.entity.CarbonMethodologyContent;
import com.carbon.assets.param.CarbonMetaregistryDocQueryParam;
import com.carbon.assets.repository.MethodologyRepository;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.collapse.CollapseBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class EsUtil
{
    public Map matchRes3(RestHighLevelClient restHighLevelClient, CarbonMetaregistryDocQueryParam param) throws Exception
    {
        SearchRequest searchRequest = new SearchRequest("esmetaregistry");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.trackTotalHits(true);
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();


        String CertifiedStandardCode=param.getCertifiedStandardCode();
        String projectScopeCode=param.getProjectScopeCode();
        String industryCode=param.getProjectIndustryCode();
        String projectStatusCode=param.getProjectStatusCode();
        String projectTypeCode=param.getProjectTypeCode();
        String refDateStart=param.getRefDateStart();
        String refDateEnd=param.getRefDateEnd();
        String issueDateStart=param.getIssueDateStart();
        String issueDateEnd=param.getIssueDateEnd();
        SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd");

        BoolQueryBuilder shouldQ= QueryBuilders.boolQuery();
        if(param.getSearchKey()!=""&&param.getSearchKey()!=null)
        {
            shouldQ.should(QueryBuilders.matchQuery("projectName",param.getSearchKey()).minimumShouldMatch("90%"));
            shouldQ.should(QueryBuilders.matchQuery("title",param.getSearchKey()));
            shouldQ.should(QueryBuilders.termsQuery("content",param.getSearchKey()));
        }
        else
        {
            searchSourceBuilder.sort("id", SortOrder.ASC);
        }
        boolQuery.must(shouldQ);

        List<String> methodCodeList=param.getMethodCodeList();
        if(methodCodeList!=null)
        {
            boolQuery.must(QueryBuilders.termsQuery("methodologyCode",methodCodeList));
        }
        if(CertifiedStandardCode!=null)
        {
            boolQuery.must(QueryBuilders.matchQuery("certifiedStandardCode",CertifiedStandardCode));
        }
        if(projectScopeCode!=null)
        {
            boolQuery.must(QueryBuilders.matchQuery("projectScopeCode",projectScopeCode));
        }
        if(industryCode!=null)
        {
            boolQuery.must(QueryBuilders.matchQuery("industryCode",industryCode));
        }
        if(projectStatusCode!=null)
        {
            boolQuery.must(QueryBuilders.matchQuery("projectStatusCode",projectStatusCode));
        }
        if(projectTypeCode!=null)
        {
            boolQuery.must(QueryBuilders.matchQuery("projectScopeTypeCode",projectTypeCode));
        }
        if(refDateStart!=null&&refDateEnd!=null)
        {
            Long from=format.parse(refDateStart).getTime();
            Long to=format.parse(refDateEnd).getTime();
            boolQuery.must(QueryBuilders.rangeQuery("recordFilingTime").from(from).to(to));
        }
        if(issueDateStart!=null&&issueDateEnd!=null)
        {
            Long from=format.parse(issueDateStart).getTime();
            Long to=format.parse(issueDateEnd).getTime();
            boolQuery.must(QueryBuilders.rangeQuery("issuingTime").from(from).to(to));
        }

        //设置字段高亮
//        HighlightBuilder highlightBuilder = new HighlightBuilder();
//        HighlightBuilder.Field highlightTitle =
//                new HighlightBuilder.Field("projectName");
//        highlightBuilder.field(highlightTitle);
//        searchSourceBuilder.highlighter(highlightBuilder);


        // 需要去重的字段
        CollapseBuilder collapseBuilder = new CollapseBuilder("id");
        searchSourceBuilder.collapse(collapseBuilder);
        searchSourceBuilder.from((param.getCurrent()-1)*10);
        searchSourceBuilder.size(param.getSize());
        searchSourceBuilder.sort("_score");
        searchSourceBuilder.fetchSource(new String[]{},new String[]{"content"});
        searchSourceBuilder.query(boolQuery);
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

        List list =  Arrays.stream(searchResponse.getHits().getHits())
                .map(e->e.getSourceAsMap())
                .collect(Collectors.toList());
        Map res=new HashMap();
        res.put("records",list);
        res.put("current",param.getCurrent());
        res.put("total",searchResponse.getHits().getTotalHits().value);
        return res;
    }

//    public List matchRes(RestHighLevelClient restHighLevelClient) throws Exception
//    {
//        //指定查询索引
//        SearchRequest searchRequest = new SearchRequest("esmetaregistry");
//        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//        //构建查询对象
//        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
//        //模糊匹配
//        boolQuery.should(QueryBuilders.matchQuery("key","value"));
//        //精确匹配
//        boolQuery.must(QueryBuilders.matchQuery("key","value"));
//        //从第几条开始查
//        searchSourceBuilder.from(0);
//        //一次查询几条
//        searchSourceBuilder.size(10);
//        searchSourceBuilder.query(boolQuery);
//        searchRequest.source(searchSourceBuilder);
//        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
//
//        List list =  Arrays.stream(searchResponse.getHits().getHits())
//                .map(e->e.getSourceAsMap())
//                .collect(Collectors.toList());
//        return list;
//    }

    public static String UpdateMethod(String json,int id) throws Exception{
        String url="http://119.3.66.173:9200/esmethodology/_update/"+id;
        System.out.println("post请求发送url："+url);
        StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);//设置消息头 Content-Type application/json; charset=UTF-8
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(entity);
        CloseableHttpResponse response2 = HttpClients.createDefault().execute(httpPost);

        try {

            response2.getStatusLine().getStatusCode();//HttpStatus.SC_OK

            HttpEntity entity2 = response2.getEntity();
            String response= EntityUtils.toString(entity2,"utf-8");//返回报文
            System.out.println(response);
            EntityUtils.consume(entity2);//关闭资源
            return response;
        } finally {
            response2.close();//关闭资源
        }
    }
}
