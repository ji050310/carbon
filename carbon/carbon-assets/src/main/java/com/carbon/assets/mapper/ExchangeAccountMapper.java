package com.carbon.assets.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.carbon.assets.entity.ExchangeAccount;
import com.carbon.assets.param.ExchangeAccountQueryParam;
import com.carbon.assets.vo.ExchangeAccountInfo;
import com.carbon.assets.vo.ExchangeAccountQueryVo;
import com.carbon.assets.vo.CarbonExchangeAccountVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 交易账户  Mapper 接口
 * </p>
 *
 * @author Li Jun
 * @since 2021-08-16
 */
@Repository
public interface ExchangeAccountMapper extends BaseMapper<ExchangeAccount> {

    /**
     * 根据ID获取查询对象
     * @param id 主键id
     * @return ExchangeAccountQueryVo
     */
    ExchangeAccountQueryVo getExchangeAccountById(Serializable id);

    /**
     * 获取分页对象
     * @param page 分页参数
     * @param param 查询参数
     * @return IPage<ExchangeAccountQueryVo>
     */
    IPage<ExchangeAccountQueryVo> getExchangeAccountPageList(@Param("page") Page<?> page, @Param("param") ExchangeAccountQueryParam param);


    List<ExchangeAccountInfo> getListByExchangeId(@Param("exchangeIds") List<Long> exchangeIds);

    /**
     * 解绑
     * @param id 账户ID
     * @return boolean
     */
    boolean unbind(Long id);

    int getExchangeAccountByName(ExchangeAccount exchangeAccount);

}
