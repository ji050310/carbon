package com.carbon.mq.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 碳区块链交易详情
 * </p>
 *
 * {
 *     "code": 200,
 *     "message": "执行成功",
 *     "data": {
 *         "txid": "1dc248bc542bc9d184b24012847ef84ca7a7a942987dac2a3bd38d0285c9671d",
 *         "blockHeight": 158,
 *         "blockHash": "a1840c9104205057607275dc804b7ccd13a46f443232372cd3e4d6f0d3517aa0",
 *         "timestamp": 1628660639,
 *         "txInfo": "contract_name: \"XXX\"\nmethod: \"XXX\"\nparameters {\n  key: \"userId\"\n  value: \"dealer_user1\"\n}\nparameters {\n  key: \"orgName\"\n  value: \"xihu_taxi\"\n}\nparameters {\n  key: \"affiliation\"\n  value: \"xihu_taxi.dealer_department\"\n}\n"
 *     }
 * }
 *
 * @author Li Jun
 * @since 2021-08-16
 */
@Data
@ApiModel(value = "碳区块链交易详情", description = "碳区块链交易详情")
public class CarbonBlockExchangeInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "交易ID")
    private String txid;
    @ApiModelProperty(value = "区块高度")
    private String blockHeight;
    @ApiModelProperty(value = "区块hash")
    private String blockHash;
    @ApiModelProperty(value = "时间戳")
    private String timestamp;
    @ApiModelProperty(value = "详情")
    private String txInfo;

}
