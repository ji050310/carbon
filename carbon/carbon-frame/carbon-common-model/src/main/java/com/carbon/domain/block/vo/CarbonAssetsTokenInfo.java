package com.carbon.domain.block.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 长安链中和资产详情
 * @author : Li Jun
 * @since : 2021-09-28 14:27
 *
 * {\"tokenId\":\"149bb3b3f36f90e001dbb0a9f723a9e3\",
 * \"userId\":\"chdtp\",
 * \"orgName\":\"aaa\",
 * \"emissionsRecordsToAudit\":\"XYHX-20210315-001-001022\",
 * \"automaticRetireDate\":\"2021-09-09 20:50:00\",
 * \"publishId\":\"12345\",
 * \"receivingId\":\"bbbbbbbb\",
 * \"assetType\":\"2\",
 * \"timeScope\":\"2022-08-16 10:00:00\",
 * \"metaData\":\"xxxxxxxxxxxx\",
 * \"mainList\":\"yyyyyyyyyyyy\",
 * \"amount\":\"10\",
 * \"uom\":\"MtCO2e\",
 * \"fromTokenId\":\"\",
 * \"availableTokenId\":\"\",
 * \"sellTokenId\":\"\",
 * \"status\":\"0\"}"
 * }
 *
 *
 **/
@Data
@ApiModel(value = "CarbonAssetsToken",description = "长安链中和资产vo")
public class CarbonAssetsTokenInfo implements Serializable {

    @ApiModelProperty("长安链生成的token")
    private String tokenId;

    @ApiModelProperty("账户id")
    private String userId;
    @ApiModelProperty("机构名称")
    private String orgName;

    @ApiModelProperty("资产类型：0碳减排，1碳排放，2碳配额")
    private String assetType;
    @ApiModelProperty("数量")
    private String amount;
    @ApiModelProperty("单位")
    private String uom;
    @ApiModelProperty("交易状态，1已交易，0未交易")
    private String status;
    @ApiModelProperty("来源tokenid")
    private String fromTokenId;
    @ApiModelProperty("可交易tokenid")
    private String availableTokenId;
    @ApiModelProperty("已出售tokenid")
    private String sellTokenId;

    @ApiModelProperty("")
    private String emissionsRecordsToAudit;
    @ApiModelProperty("")
    private String automaticRetireDate;

    @ApiModelProperty("发布人id")
    private String publishId;
    @ApiModelProperty("收件人id")
    private String receivingId;

    @ApiModelProperty("清单")
    private String mainList;
    @ApiModelProperty("元数据")
    private String metaData;
    @ApiModelProperty("时间范围")
    private String timeScope;

}
