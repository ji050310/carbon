package com.carbon.assets.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 中和资产交易  查询结果对象
 * </p>
 *
 * @author Li Jun
 * @since 2021-09-24
 */
@Data
@ApiModel(value = "CarbonAssetsBusinessQueryVo对象", description = "中和资产交易 查询参数")
public class CarbonAssetsBusinessQueryVo implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
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

    @ApiModelProperty(value = "创建时间")
    private Date createdTime;

    @ApiModelProperty(value = "更新时间")
    private Date updatedTime;

}
