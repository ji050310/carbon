package com.carbon.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.carbon.common.api.Paging;
import com.carbon.common.service.BaseServiceImpl;
import com.carbon.domain.auth.api.LoginCheckApi;
import com.carbon.domain.auth.vo.SecurityData;
import com.carbon.system.controller.CarbonH5ArticleController;
import com.carbon.system.entity.*;
import com.carbon.system.mapper.CarbonArticleCommentMapper;
import com.carbon.system.mapper.CarbonArticleMapper;
import com.carbon.system.mapper.CarbonH5ArticleMapper;
import com.carbon.system.mapper.SysAccountMapper;
import com.carbon.system.param.CarbonArticleQueryParam;
import com.carbon.system.param.CarbonH5ArticleKeyWordSearchParam;
import com.carbon.system.service.CarbonH5ArticleService;
import com.carbon.system.vo.CarbonArticleCommentQueryVo;
import com.carbon.system.vo.CarbonArticleQueryVo;
import com.carbon.system.vo.SysAccountQueryVo;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: code534
 * @time: 2022/7/7 9:46
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class CarbonH5ArticleServiceImpl extends BaseServiceImpl<CarbonH5ArticleMapper, CarbonArticle>  implements CarbonH5ArticleService {
    private final String H5SearchResultIndex = "es_carbon_h5_search_result";
    private final String MethodologyIndex = "esmethodology";

    private final String ArticleIndex = "es_carbon_article";
    @Resource
    CarbonH5ArticleMapper carbonH5ArticleMapper;

    @Resource
    CarbonArticleCommentMapper commentMapper;

    @Resource
    SysAccountMapper accountMapper;

//    @Autowired
//    private RestHighLevelClient restHighLevelClient;



    @Override
    public Paging<CarbonArticleQueryVo> getCarbonArticlePageList(CarbonArticleQueryParam param) {
        param.setSortField("created_time");
        param.setAsc(false);
        IPage<CarbonArticleQueryVo> iPage = carbonH5ArticleMapper.getPageList(getPage(param),param);
        iPage.getRecords().stream().forEach(e->{
            LambdaQueryWrapper<CarbonArticleComment> commentLambdaQueryWrapper = new LambdaQueryWrapper<>();
            commentLambdaQueryWrapper.eq(CarbonArticleComment::getArticleId,e.getId());
            e.setCommentNumber( commentMapper.selectCount(commentLambdaQueryWrapper));
            e.setContent( carbonH5ArticleMapper.getContentById(e.getId()));
        });
        return new Paging<>(iPage);
    }

    @Override
    public CarbonArticleQueryVo getCarbonH5ArticleById(Serializable id) {
        return null;
    }

    @Override
    public Paging<CarbonArticleQueryVo> searchByKeyword(CarbonH5ArticleController.H5SearchDefaultParam param) {
        return null;
    }

    @Override
    public Paging<CarbonMethodologyContent> searchMethodologyByKeyword(CarbonH5ArticleController.H5SearchMethodologyParam param) {
        return null;
    }

    @Override
    public Paging<CarbonArticleQueryVo> searchArticleByKeyword(CarbonH5ArticleKeyWordSearchParam param) {
        return null;
    }

//    @Override
//    @Transactional
//    public CarbonArticleQueryVo getCarbonH5ArticleById(Serializable id) {
//        CarbonArticle byId = this.getById(id);
//        // 更新浏览量
//        byId.setBrowseNum(byId.getBrowseNum()+1);
//        this.updateById(byId);
//        // 转换为vo对象
//        CarbonArticleQueryVo vo = new CarbonArticleQueryVo();
//        BeanUtil.copyProperties(byId,vo);
//        // 获取当前文章下面的一级评论
//        LambdaQueryWrapper<CarbonArticleComment> cwrapper = new LambdaQueryWrapper<>();
//        cwrapper.eq(CarbonArticleComment::getArticleId,id).isNull(CarbonArticleComment::getParentId);
//        List<CarbonArticleCommentQueryVo> comments = commentMapper.selectList(cwrapper)
//                                                .stream().map(e->{
//                                                    cwrapper.clear();
//                                                    // 该评论是否还有子评论
//                                                    cwrapper.eq(CarbonArticleComment::getParentId,e.getId());
//                                                    Integer count = commentMapper.selectCount(cwrapper);
//                    CarbonArticleCommentQueryVo v = new CarbonArticleCommentQueryVo();
//                                                    BeanUtil.copyProperties(e,v);
//                                                    v.setHavaChild(count>0);
//                                                    return v;
//                                                }).collect(Collectors.toList());
//        // 设置文章评论数量
//        cwrapper.clear();
//        cwrapper.eq(CarbonArticleComment::getArticleId,id);
//        vo.setCommentNumber(commentMapper.selectCount(cwrapper));
//        // 获取文章用户头像
//        if (byId.getCreatorId()!=0){
//            SysAccountQueryVo accountInfo = accountMapper.getSysAccountById(byId.getCreatorId());
//            vo.setAuthorHeadPortrait(accountInfo.getAvatar());
//        }
//        vo.setCommentList(comments);
//        return vo;
//    }

//    @Override
//    public Paging searchByKeyword(CarbonH5ArticleController.H5SearchDefaultParam param) {
//        // 默认是综合，搜索资讯文章
//        return searchDefaultByKeyword(param);
//    }


//    @Override
//    public Paging<CarbonMethodologyContent> searchMethodologyByKeyword(CarbonH5ArticleController.H5SearchMethodologyParam param) {
//
//        SearchRequest searchRequest = new SearchRequest(MethodologyIndex);
//        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//        searchSourceBuilder.trackTotalHits(true);
//        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
//        //
//        if(StrUtil.isNotBlank(param.getKeyword())){
//            boolQuery.must(QueryBuilders.matchQuery("content",param.getKeyword()));
//        }
//        // 二次筛选
//        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
//        if(StrUtil.isNotBlank(param.getFieldCode())){
//            boolQueryBuilder.should(QueryBuilders.termQuery("fieldCode",param.getFieldCode()));
//        }
//        if(StrUtil.isNotBlank(param.getIndustryCode())) {
//            boolQueryBuilder.should(QueryBuilders.termQuery("industryCode",param.getIndustryCode()));
//        }
//        boolQuery.must(boolQueryBuilder);
//        // 分页
//        searchSourceBuilder.from((param.getCurrent()-1)*10);
//        searchSourceBuilder.size(param.getSize());
//        // 构建搜索语句
//        searchSourceBuilder.query(boolQuery);
//        searchRequest.source(searchSourceBuilder);
//        try {
//            SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
//            List<CarbonMethodologyContent> list = Arrays.stream(searchResponse.getHits().getHits())
//                    .map(e -> {
//                        return BeanUtil.mapToBean(e.getSourceAsMap(), CarbonMethodologyContent.class, true, CopyOptions.create());
//                    })
//                    .collect(Collectors.toList());
//            Paging paging = new Paging<>(searchResponse.getHits().getTotalHits().value,param.getCurrent(),list);
//            return paging;
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }


//    @Override
//    public Paging<CarbonArticleQueryVo> searchArticleByKeyword(CarbonH5ArticleKeyWordSearchParam param) {
//        SearchRequest searchRequest = new SearchRequest(ArticleIndex);
//        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//        searchSourceBuilder.trackTotalHits(true);
//        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
//        //
//        if(StrUtil.isNotEmpty((param.getCategoryId()))){
//            // 分类id不为空字符串
//            boolQuery.must(QueryBuilders.termQuery("categoryId",param.getCategoryId()));
//        }
//        // 资讯搜索关键字
//        boolQuery.must(QueryBuilders.matchQuery("content",param.getKeyword()));
//
//        // 去重
//        // 分页
//        searchSourceBuilder.from((param.getCurrent()-1)*10);
//        searchSourceBuilder.size(param.getSize());
//        // 默认按照搜索相关度排序
//        // searchSourceBuilder.sort("_score");
//        // 不搜出content
//        //searchSourceBuilder.fetchSource(new String[]{},new String[]{"content"});
//        searchSourceBuilder.query(boolQuery);
//        searchRequest.source(searchSourceBuilder);
//        try {
//            SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
//            List<CarbonArticleQueryVo> list =  Arrays.stream(searchResponse.getHits().getHits())
//                    .map(e-> BeanUtil.mapToBean(e.getSourceAsMap(),CarbonArticleQueryVo.class,false, CopyOptions.create()))
//                    .collect(Collectors.toList());
//            Paging paging = new Paging<>(searchResponse.getHits().getTotalHits().value,param.getCurrent(),list);
//            return paging;
//        } catch (IOException e) {
//
//
//            throw new RuntimeException(e);
//        }
//
//    }

//    public Paging<Object> searchDefaultByKeyword(CarbonH5ArticleController.H5SearchDefaultParam param ){
//        SearchRequest searchRequest = new SearchRequest(H5SearchResultIndex);
//        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//        searchSourceBuilder.trackTotalHits(true);
//        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
//        boolQuery.must(QueryBuilders.matchQuery("content",param.getKeyword()));
//        // 分页
//        searchSourceBuilder.from((param.getCurrent()-1)*10);
//        searchSourceBuilder.size(param.getSize());
//        // 不搜出content
//        searchSourceBuilder.fetchSource(new String[]{},new String[]{"content"});
//
//        searchSourceBuilder.query(boolQuery);
//        searchRequest.source(searchSourceBuilder);
//        try {
//            SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
//            List<CarbonH5SearchResult> list =  Arrays.stream(searchResponse.getHits().getHits())
//                    .map(e-> BeanUtil.mapToBean(e.getSourceAsMap(), CarbonH5SearchResult.class,false, CopyOptions.create()))
//                    .collect(Collectors.toList());
//
//            list.parallelStream().forEach(e->{
//                if(e.getCategoryType().equals("article")){
//                    CarbonArticle carbonArticle = carbonH5ArticleMapper.selectById(e.getPrimaryKey());
//                    e.setData(carbonArticle);
//                }else if(e.getCategoryType().equals("methodology")){
//                    Map m = carbonH5ArticleMapper.getMethodologyByPrimaryKey(e.getPrimaryKey());
//                    BeanUtil.mapToBean(m,CarbonMethodologyContent.class,false, CopyOptions.create());
//                    e.setData(m);
//                }
//            });
//            Paging paging = new Paging<>(searchResponse.getHits().getTotalHits().value,param.getCurrent(),list);
//            return paging;
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//    }

}
