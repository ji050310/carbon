package com.carbon.domain.chainmaker.vo;

import lombok.Data;

/**
 * @Description: TODO
 * @Package: com.chainmaker.sdk.vo
 * @ClassName: TransactionRtVO
 * @author: 吴朋
 * @email: wupeng@baec.org.cn
 * @createDate: 2021/8/6 17:05
 * @Company: 北京微芯区块链与边缘计算研究院
 * @Copyright: 2021 Copyright 微芯院 All Rights Reserved.
 * <p>
 * ---------------------------------------------------------
 * Version    Author    Status    Date
 * V1.0       吴朋      C         2021/8/6
 */
@Data
public class TransactionRtVO {
    private String txid;
    private  long  blockHeight;
    private  String  blockHash;
    private long timestamp;
    private String txInfo;
}
