package com.carbon.trade.controller;

import com.carbon.trade.repository.EsCarbonTradeQuoteRepository;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * ES 搜索行情
 * @author : LiJun
 * @date : 2022-06-22 16:34
 **/
@Slf4j
@RestController
@RequestMapping("/esTradeQuote")
@Api(value = "ES 行情搜索", tags = {"ES 行情搜索"})
public class CarbonTradeSearchController {

    @Resource
    private EsCarbonTradeQuoteRepository carbonTradeQuoteRepository;

//    @Autowired
//    private ElasticsearchRestTemplate restTemplate;


    /**
     * 分页查询
     *
     * @param pageNum  页码,从0开始
     * @param pageSize 分页大小
     * @return 查询结果
     */
//    @GetMapping("/search")
//    public Paging<CarbonTradeQuoteQueryVo> queryPage(@RequestParam String keywords, @RequestParam int pageNum, @RequestParam int pageSize) {
//        Pageable pageable = PageRequest.of(pageNum, pageSize);
//        Page<CarbonTradeQuoteQueryVo> pages = carbonTradeQuoteRepository.searchByKeywords(keywords, pageable);
//
//        return new Paging<>(pages.getTotalElements(),pageNum,pages.getContent());
//    }

}
