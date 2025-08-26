package com.carbon.domain.block.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 中和资产交易 参数
 * @author : Li Jun
 * @since : 2021-09-10 16:30
 **/
@Data
public class CarbonBusinessParam {

    /**卖方用户账户id(长安链账户id)**/
    private String userIdFrom;
    /**卖方机构名称**/
    private String orgNameFrom;
    /**卖方tokenid(中和资产tokenId)**/
    private String tokenFrom;
    /**买方机构名称**/
    private String orgNameTo;
    /**交易量**/
    private long transferAmount;
    /**交易类型，0单一交易，1批次交易**/
    private String transferType;
    /**同步异步标志,true同步，false异步，默认false*/
    boolean sign;

}
