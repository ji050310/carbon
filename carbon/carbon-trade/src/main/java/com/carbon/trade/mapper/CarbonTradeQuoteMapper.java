package com.carbon.trade.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.carbon.trade.entity.CarbonTradeQuote;
import com.carbon.trade.param.CarbonTradeQuoteQueryParam;
import com.carbon.trade.vo.CarbonTradeQuoteQueryVo;
import com.carbon.trade.vo.MetaregistryDataVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 碳交易供需行情 Mapper 接口
 * </p>
 *
 * @author lin rizhao
 * @since 2022-05-21
 */
@Repository
public interface CarbonTradeQuoteMapper extends BaseMapper<CarbonTradeQuote> {

    /**
     * 根据ID获取查询对象
     * @param id 主键id
     * @return CarbonTradeQuoteQueryVo
     */
    CarbonTradeQuoteQueryVo getCarbonTradeQuoteById(Serializable id);

    /**
     * 获取分页对象
     * @param page 分页参数
     * @param param 查询参数
     * @return IPage<CarbonTradeQuoteQueryVo>
     */
    IPage<CarbonTradeQuoteQueryVo> getCarbonTradeQuotePageList(@Param("page") Page<?> page, @Param("param") CarbonTradeQuoteQueryParam param);

    MetaregistryDataVo getCarbonMetaregistryByCarbonMetaregistryProjectId(Long id);
}
