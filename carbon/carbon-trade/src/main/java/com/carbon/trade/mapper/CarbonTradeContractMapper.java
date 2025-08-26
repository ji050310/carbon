package com.carbon.trade.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.carbon.trade.entity.CarbonTradeContract;
import com.carbon.trade.param.CarbonTradeContractQueryParam;
import com.carbon.trade.vo.CarbonTradeContractQueryVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * <p>
 * 碳交易履约 Mapper 接口
 * </p>
 *
 * @author lin rizhao
 * @since 2022-05-21
 */
@Repository
public interface CarbonTradeContractMapper extends BaseMapper<CarbonTradeContract> {

    /**
     * 根据ID获取查询对象
     * @param id 主键id
     * @return CarbonTradeContractQueryVo
     */
    CarbonTradeContractQueryVo getCarbonTradeContractById(Serializable id);

    /**
     * 获取分页对象
     * @param page 分页参数
     * @param param 查询参数
     * @return IPage<CarbonTradeContractQueryVo>
     */
    IPage<CarbonTradeContractQueryVo> getCarbonTradeContractPageList(@Param("page") Page<?> page, @Param("param") CarbonTradeContractQueryParam param, @Param("tenantId") Long tenantId);

}
