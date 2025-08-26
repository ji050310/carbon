package com.carbon.assets.service;

import com.carbon.assets.entity.ExchangeAccount;
import com.carbon.assets.param.ExchangeAccountBindingParam;
import com.carbon.common.service.BaseService;
import com.carbon.assets.param.ExchangeAccountQueryParam;
import com.carbon.assets.vo.ExchangeAccountInfo;
import com.carbon.assets.vo.ExchangeAccountQueryVo;
import com.carbon.common.api.Paging;
import com.carbon.assets.vo.CarbonExchangeAccountVo;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 交易账户  服务类
 * </p>
 *
 * @author Li Jun
 * @since 2021-08-16
 */
public interface ExchangeAccountService extends BaseService<ExchangeAccount> {

    /**
     * 根据ID获取查询对象
     * @param id 主键id
     * @return ExchangeAccountQueryVo
     */
    ExchangeAccountQueryVo getExchangeAccountById(Serializable id);

    /**
     * 获取分页对象
     * @param param ExchangeAccountQueryParam
     * @return Paging<ExchangeAccountQueryVo>
     */
    Paging<ExchangeAccountQueryVo> getExchangeAccountPageList(ExchangeAccountQueryParam param);

    /**
     * 添加碳交易账户
     * @param exchangeAccount 账户信息
     */
    void addExchangeAccount(ExchangeAccount exchangeAccount);

    /**
     * 根据交易所ID 获取账户
     * @param exchangeIds 交易所
     * @return List<ExchangeAccountVo>
     */
    List<ExchangeAccountInfo> getListByExchangeId(List<Long> exchangeIds);

    /**
     * 绑定账户
     * @param param 绑定参数
     */
    void binding(ExchangeAccountBindingParam param);

    /**
     * 解绑账户
     * @param id 账户ID
     */
    void unbind(Long id);
}