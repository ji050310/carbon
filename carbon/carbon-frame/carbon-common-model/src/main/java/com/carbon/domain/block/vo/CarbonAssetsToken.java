package com.carbon.domain.block.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 机构 中和资产信息
 * @author : Li Jun
 * @since : 2021-09-28 14:27
 *     "orgName": "xxxx", //机构名称
 *             "carbonNeutralValue": "10", //碳中和净值
 *             "carbonNeutralSymbol": "+", //碳中和净值符号 +正， -负
 *             "tokenItems": "[{
 *             "tokenId":"xxxx",// 长安链生成的token
 *             "orgName":"xxxx",//机构名称
 *             "assetType":"1",//资产类型：0碳减排，1碳排放，2碳配额
 *             "amount":"xxxx",//数量
 *             "uom":"xxxx",//单位
 *             "status":"xxx”,//交易状态，1已交易，0未交易
 *             "fromTokenId":"xxxx",//来源tokenid
 *             "availableTokenId":"xxxx",//可交易tokenid
 *             "sellTokenId":"xxxx",//已出售tokenid
 *             "status":"0"//交易状态：0未交易，1已交易
 *     }]", //账户下的token资产列表
 **/
@Data
@ApiModel(value = "CarbonAssetsToken",description = "长安链中和资产vo")
public class CarbonAssetsToken implements Serializable {

    @ApiModelProperty("机构名称")
    private String orgName;
    @ApiModelProperty("碳中和净值")
    private String carbonNeutralValue;
    @ApiModelProperty("碳中和净值符号 +正， -负")
    private String carbonNeutralSymbol;

    @ApiModelProperty("机构名称")
    private List<CarbonAssetsTokenInfo> tokenItems;
}
