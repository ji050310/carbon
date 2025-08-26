package com.carbon.trade.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.carbon.common.exception.CommonBizException;
import com.carbon.domain.common.constant.RocketDelayLevelConstant;
import com.carbon.domain.common.constant.RocketMqName;
import com.carbon.domain.trade.vo.MqCarbonTradeQuote;
import com.carbon.trade.common.enums.TradeRoleEnum;
import com.carbon.trade.common.enums.TradeStatusEnum;
import com.carbon.trade.entity.CarbonTradePrice;
import com.carbon.trade.entity.CarbonTradeQuote;
import com.carbon.trade.mapper.CarbonTradeQuoteMapper;
import com.carbon.trade.param.StartTradingParam;
import com.carbon.trade.repository.EsCarbonTradeQuoteRepository;
import com.carbon.trade.service.CarbonTradePriceService;
import com.carbon.trade.service.CarbonTradeQuoteService;
import com.carbon.trade.param.CarbonTradeQuoteQueryParam;
import com.carbon.trade.vo.CarbonTradeQuoteQueryVo;
import com.carbon.common.service.BaseServiceImpl;
import com.carbon.common.api.Paging;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.elasticsearch.common.lucene.search.function.FunctionScoreQuery;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


/**
 * <p>
 * 碳交易供需行情 服务实现类
 * </p>
 *
 * @author lin rizhao
 * @since 2022-05-21
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class CarbonTradeQuoteServiceImpl extends BaseServiceImpl<CarbonTradeQuoteMapper, CarbonTradeQuote> implements CarbonTradeQuoteService {

    @Resource
    private CarbonTradeQuoteMapper carbonTradeQuoteMapper;

    @Autowired
    private CarbonTradePriceService carbonTradePriceService;
    @Autowired
    private RocketMQTemplate mqTemplate;
    @Autowired
    private EsCarbonTradeQuoteRepository carbonTradeQuoteRepository;
    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Override
    public void addTradeQuote(CarbonTradeQuote tradeQuote) {
        // TODO 这里是从 项目库里面拿出 项目类型和项目领域信息
        Long projectId = tradeQuote.getProjectId();
        Optional.ofNullable(projectId).ifPresent(e->{
            com.carbon.trade.vo.MetaregistryDataVo map =  carbonTradeQuoteMapper.getCarbonMetaregistryByCarbonMetaregistryProjectId(e);
            if(map==null)
            {
                return;
            }
            tradeQuote.setProjectType("00".concat(map.getProjectScopeTypeCode()));//数据转换丢位 添加两个0
            tradeQuote.setProjectScope(map.getProjectScope());
            tradeQuote.setProjectScopeCode(map.getProjectScopeCode());
            log.info("{}",JSONUtil.toJsonStr(map));
        });

        tradeQuote.setPublisherId(getCurrentTenantId());
        tradeQuote.setStatus(TradeStatusEnum.OFFER.getStatus());
        this.save(tradeQuote);

        //发送Mq 消息
        Message<MqCarbonTradeQuote> message = MessageBuilder
                .withPayload(BeanUtil.copyProperties(tradeQuote, MqCarbonTradeQuote.class)).build();
        mqTemplate.syncSend(RocketMqName.FS_TEST,message,3000,RocketDelayLevelConstant.SECOND10);
    }

    @Override
    public CarbonTradeQuoteQueryVo getCarbonTradeQuoteById(Serializable id) {
        return carbonTradeQuoteMapper.getCarbonTradeQuoteById(id);
    }

    @Override
    public Paging<CarbonTradeQuoteQueryVo> getCarbonTradeQuotePageList(CarbonTradeQuoteQueryParam param) {
        //分页列表按更新时间降序
        Page<?> page = getPage(param);
        page.addOrder(OrderItem.desc("ctq.updated_time"));
        IPage<CarbonTradeQuoteQueryVo> iPage = carbonTradeQuoteMapper.getCarbonTradeQuotePageList(page,param);
        return new Paging<>(iPage);
    }

    @Override
    public void startTrading(StartTradingParam param) {
        Long tenantId = getCurrentTenantId();

        CarbonTradeQuote tradeQuote = this.getById(param.getTradeQuoteId());
        if (tradeQuote == null){
            throw new CommonBizException("交易行情不存在");
        }
        if (tradeQuote.getPublisherId().equals(tenantId)){
            throw new CommonBizException("无法询报价自己发布的行情");
        }

        tradeQuote.setStatus(TradeStatusEnum.INTENDED_TRADE.getStatus());
        updateById(tradeQuote);

        //生成询报价信息
        CarbonTradePrice tradePrice = new CarbonTradePrice();
        tradePrice.setTradeQuoteId(tradeQuote.getId());
        if (TradeRoleEnum.SELLER.getStatus().equals(tradeQuote.getTradeRole())){
            //发布行情为卖方,当前人为买方
            tradePrice.setSellerId(tradeQuote.getPublisherId());
            tradePrice.setSellerTradeQuantity(tradeQuote.getTradeQuantity());
            tradePrice.setSellerUnitPrice(tradeQuote.getAssetUnitPrice());
            tradePrice.setSellerDeliveryExchange(tradeQuote.getDeliveryExchange());
            tradePrice.setSellerDeliveryMethod(tradeQuote.getDeliveryMethod());
            tradePrice.setSellerDeliveryTime(tradeQuote.getDeliveryTime());

            tradePrice.setBuyerId(tenantId);
            tradePrice.setProjectScope(param.getProjectScope());
            tradePrice.setProjectScopeCode(param.getProjectScopeCode());
            tradePrice.setBuyerTradeQuantity(param.getTradeQuantity());
            tradePrice.setBuyerUnitPrice(param.getAssetUnitPrice());
            tradePrice.setBuyerDeliveryExchange(param.getDeliveryExchange());
            tradePrice.setBuyerDeliveryMethod(param.getDeliveryMethod());
            tradePrice.setBuyerDeliveryTime(param.getDeliveryTime());
        }else if (TradeRoleEnum.BUYER.getStatus().equals(tradeQuote.getTradeRole())){
            //发布行情为买方,当前人为卖
            tradePrice.setSellerId(tenantId);
            tradePrice.setSellerTradeQuantity(param.getTradeQuantity());
            tradePrice.setSellerUnitPrice(param.getAssetUnitPrice());
            tradePrice.setSellerDeliveryExchange(param.getDeliveryExchange());
            tradePrice.setSellerDeliveryMethod(param.getDeliveryMethod());
            tradePrice.setSellerDeliveryTime(param.getDeliveryTime());

            tradePrice.setBuyerId(tradeQuote.getPublisherId());
            tradePrice.setBuyerTradeQuantity(tradeQuote.getTradeQuantity());
            tradePrice.setBuyerUnitPrice(tradeQuote.getAssetUnitPrice());
            tradePrice.setBuyerDeliveryExchange(tradeQuote.getDeliveryExchange());
            tradePrice.setBuyerDeliveryMethod(tradeQuote.getDeliveryMethod());
            tradePrice.setBuyerDeliveryTime(tradeQuote.getDeliveryTime());
        }else {
            throw new CommonBizException("交易行情，交易角色不匹配");
        }
        carbonTradePriceService.addTradePrice(tradePrice);
    }

    @Override
    public Paging<CarbonTradeQuoteQueryVo> searchByEs(String keyword, Integer pageNum, Integer pageSize) {

        Pageable pageable = PageRequest.of(pageNum, pageSize);
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        //分页
        nativeSearchQueryBuilder.withPageable(pageable);
        if (StringUtils.isEmpty(keyword)) {
            nativeSearchQueryBuilder.withQuery(QueryBuilders.matchAllQuery());
        } else {
            List<FunctionScoreQueryBuilder.FilterFunctionBuilder> filterFunctionBuilders = new ArrayList<>();
            filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.matchQuery("institutionName", keyword),
                    ScoreFunctionBuilders.weightFactorFunction(10)));
            filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.matchQuery("status", keyword),
                    ScoreFunctionBuilders.weightFactorFunction(5)));
            filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.matchQuery("projectType", keyword),
                    ScoreFunctionBuilders.weightFactorFunction(2)));
            FunctionScoreQueryBuilder.FilterFunctionBuilder[] builders = new FunctionScoreQueryBuilder.FilterFunctionBuilder[filterFunctionBuilders.size()];
            filterFunctionBuilders.toArray(builders);
            FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery(builders)
                    .scoreMode(FunctionScoreQuery.ScoreMode.SUM)
                    .setMinScore(2);

            nativeSearchQueryBuilder.withQuery(functionScoreQueryBuilder);


            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery()
                    .must(QueryBuilders.fuzzyQuery("institutionName",keyword))
                    .must(QueryBuilders.fuzzyQuery("institutionName",keyword))
                    .must(QueryBuilders.fuzzyQuery("institutionName",keyword));

        }

        NativeSearchQuery searchQuery = nativeSearchQueryBuilder.build();
        SearchHits<CarbonTradeQuoteQueryVo> searchHits = elasticsearchRestTemplate.search(searchQuery, CarbonTradeQuoteQueryVo.class);


        if(searchHits.getTotalHits()<=0){
            return new Paging<>(0,pageNum,new ArrayList<CarbonTradeQuoteQueryVo>());
        }

        List<CarbonTradeQuoteQueryVo> searchProductList = searchHits.stream().map(SearchHit::getContent).collect(Collectors.toList());
        return new Paging<>(searchHits.getTotalHits(),pageNum,searchProductList);
    }

    @Override
    public Integer importAll() {
        CarbonTradeQuoteQueryParam queryParam = new CarbonTradeQuoteQueryParam();
        queryParam.setSize(Integer.MAX_VALUE);
        List<CarbonTradeQuoteQueryVo> records = this.getCarbonTradeQuotePageList(queryParam).getRecords();

        //保存到ES
        carbonTradeQuoteRepository.saveAll(records);
        return records.size();
    }

}
