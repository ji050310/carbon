package com.carbon.trade.service.impl;

import cn.hutool.json.JSONUtil;
import com.carbon.common.api.Paging;
import com.carbon.trade.param.CarbonTradeQuoteQueryParam;
import com.carbon.trade.repository.EsCarbonTradeQuoteRepository;
import com.carbon.trade.service.CarbonTradeQuoteService;
import com.carbon.trade.vo.CarbonTradeQuoteQueryVo;
import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class CarbonTradeQuoteServiceImplTest extends TestCase {

    @Autowired
    private CarbonTradeQuoteService carbonTradeQuoteService;
    @Autowired
    private EsCarbonTradeQuoteRepository carbonTradeQuoteRepository;

    @Test
    public void testSearchByEs() {
        Paging<CarbonTradeQuoteQueryVo> nn = carbonTradeQuoteService.searchByEs("nn", 1, 10);
        log.info(JSONUtil.toJsonPrettyStr(nn));
    }

    @Test
    public void create(){
        CarbonTradeQuoteQueryParam queryParam = new CarbonTradeQuoteQueryParam();
        List<CarbonTradeQuoteQueryVo> records = carbonTradeQuoteService.getCarbonTradeQuotePageList(queryParam).getRecords();
        for (CarbonTradeQuoteQueryVo record : records) {
            CarbonTradeQuoteQueryVo save = carbonTradeQuoteRepository.save(record);
            log.info(JSONUtil.toJsonPrettyStr(save));
        }
    }
}