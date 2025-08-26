package com.carbon.trade.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.carbon.trade.entity.CarbonTradePrice;
import com.carbon.trade.param.CarbonTradePriceQueryParam;
import com.carbon.trade.vo.CarbonTradePriceQueryVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * <p>
 * 碳交易询报价 Mapper 接口
 * </p>
 *
 * @author lin rizhao
 * @since 2022-05-22
 */
@Repository
public interface CarbonTradePriceMapper extends BaseMapper<CarbonTradePrice> {

    /**
     * 根据ID获取查询对象
     * @param id 主键id
     * @return CarbonTradePriceQueryVo
     */
    CarbonTradePriceQueryVo getCarbonTradePriceById(Serializable id);

    /**
     * 获取分页对象
     * @param page 分页参数
     * @param param 查询参数
     * @return IPage<CarbonTradePriceQueryVo>
     */
    IPage<CarbonTradePriceQueryVo> getCarbonTradePricePageList(@Param("page") Page<?> page, @Param("param") CarbonTradePriceQueryParam param);

}