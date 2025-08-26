package com.carbon.assets.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.carbon.assets.entity.CarbonExchange;
import com.carbon.assets.param.CarbonExchangeQueryParam;
import com.carbon.domain.assets.vo.CarbonExchangeQueryVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * <p>
 * 碳交易所  Mapper 接口
 * </p>
 *
 * @author Li Jun
 * @since 2021-08-16
 */
@Repository
public interface CarbonExchangeMapper extends BaseMapper<CarbonExchange> {

    CarbonExchangeQueryVo getCarbonExchangeById(String id);

    IPage<CarbonExchangeQueryVo> getCarbonExchangePageList(@Param("page") Page<?> page,@Param("param") CarbonExchangeQueryParam param);
}
