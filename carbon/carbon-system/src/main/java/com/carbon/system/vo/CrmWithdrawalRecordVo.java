package com.carbon.system.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;

import java.util.Date;

/**
 * <p>
 * 提现记录 查询结果对象
 * </p>
 *
 * @author Zhang Jinrui
 * @since 2022-05-02
 */
@Data
@ApiModel(value="CrmWithdrawalRecordQueryVo对象", description="提现记录查询参数")
public class CrmWithdrawalRecordVo implements Serializable{
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户ID")
    private String userId;

    @ApiModelProperty(value = "提现金额")
    private Integer withdrawalAmount;

    @ApiModelProperty(value = "兑换积分")
    private Integer exchangeIntegral;

    @ApiModelProperty(value = "兑换日期")
    private Date exchangeTime;

}
