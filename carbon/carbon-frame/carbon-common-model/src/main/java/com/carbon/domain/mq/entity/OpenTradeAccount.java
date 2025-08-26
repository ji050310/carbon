package com.carbon.domain.mq.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 交易开户跟进表单MQ实体类
 *
 * @author Bae
 * @version 1.0
 * @date 2022/6/5 22:11
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value="交易开户跟进表单MQ实体类", description="交易开户跟进表单MQ实体类")
public class OpenTradeAccount {
    @ApiModelProperty(value = "交易账户")
    private String tradeAccount;

    @ApiModelProperty(value = "开户机构名称")
    private String institutionName;

    @ApiModelProperty(value = "开户交易所名称")
    private String exchangeName;

    @ApiModelProperty(value = "联系人名称")
    private String contactName;

    @ApiModelProperty(value = "联系电话")
    private String contactNumber;

    @ApiModelProperty(value = "进展状态")
    private String progressStatus;

    @ApiModelProperty(value = "赢单率")
    private String winRate;

    @ApiModelProperty(value = "跟进人")
    private String followPeople;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "签约付费情况")
    private String payState;
}
