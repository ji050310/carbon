package com.carbon.domain.mq.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 添加交易账户审批表单MQ实体类
 *
 * @author Bae
 * @version 1.0
 * @date 2022/6/5 22:11
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value="添加交易账户审批表单MQ实体类", description="添加交易账户审批表单MQ实体类")
public class AddTradingAccountApproval {

    @ApiModelProperty(value = "交易账户ID")
    private Long id;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "机构名称")
    private String agenciesName;

    @ApiModelProperty(value = "联系电话")
    private String contactNumber;

    @ApiModelProperty(value = "交易所")
    private String exchangeName;

    @ApiModelProperty(value = "交易账户")
    private String tradeAccount;

    @ApiModelProperty(value = "账户凭证")
    private String accountProof;

    @ApiModelProperty(value = "审批人")
    private String approver;

    @ApiModelProperty(value = "审批状态")
    private String approvalState;

    @ApiModelProperty(value = "备注")
    private String remark;

}
