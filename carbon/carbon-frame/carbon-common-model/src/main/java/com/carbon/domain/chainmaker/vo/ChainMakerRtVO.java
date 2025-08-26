package com.carbon.domain.chainmaker.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * file:ChainMakerRtVO
 * <p>
 * 文件简要说明
 *
 * @author 2021-04-12 hgl 创建初始版本
 * @version V1.0  简要版本说明
 * @par 版权信息：
 * 2021 Copyright 微芯院 All Rights Reserved.
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChainMakerRtVO {
    // 交易码
    public String txId;
    // 业务数据id,由调用方自己生成
    private String tokenId;

//    private String hash;
//    private Long blockNumber;
}
