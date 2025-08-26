package com.carbon.assets.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.carbon.domain.block.param.CarbonBusinessParam;
import com.carbon.domain.common.BaseEntity;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.NumberDeserializers;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

import static com.fasterxml.jackson.databind.deser.std.NumberDeserializers.*;

/**
 * <p>
 * 中和资产交易
 * </p>
 *
 * @author Li Jun
 * @since 2021-09-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "CarbonAssetsBusiness对象", description = "中和资产交易 ")
public class CarbonAssetsBusiness extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键",hidden = true)
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "长安链交易id")
    private String txId;

    @ApiModelProperty(value = "卖方用户账户id(长安链账户id)")
    private String userIdFrom;

    @ApiModelProperty(value = "卖方机构名称")
    private String orgNameFrom;

    @ApiModelProperty(value = "卖方tokenid(中和资产tokenId)")
    private String tokenFrom;

    @ApiModelProperty(value = "买方机构名称")
    private String orgNameTo;

    @ApiModelProperty(value = "交易量")
    private BigDecimal transferAmount;

    @ApiModelProperty(value = "交易类型，0单一交易，1批次交易")
    private Integer transferType;

    @ApiModelProperty(value = "备注")
    private String remarks;


    public static CarbonBusinessParam buildCarbonBusinessParam(CarbonAssetsBusiness assetsBusiness) {
        CarbonBusinessParam param = new CarbonBusinessParam();
        param.setUserIdFrom(assetsBusiness.getUserIdFrom());
        param.setOrgNameFrom(assetsBusiness.getOrgNameFrom());
        param.setTokenFrom(assetsBusiness.getTokenFrom());
        param.setOrgNameTo(assetsBusiness.getOrgNameTo());
        param.setTransferAmount(assetsBusiness.getTransferAmount().longValue());
        param.setTransferType(assetsBusiness.getTransferType()+"");
        param.setSign(true);
        return param;
    }
}
