package com.carbon.domain.mq.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 注册开户跟进表单MQ实体类
 *
 * @author Bae
 * @version 1.0
 * @date 2022/6/5 22:11
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value="注册开户跟进表单MQ实体类", description="注册开户跟进表单MQ实体类")
public class OpenRegisterAccount {
    @ApiModelProperty(value = "账户名称")
    private String accountName;

    @ApiModelProperty(value = "开户机构名称")
    private String institutionName;

    @ApiModelProperty(value = "联系人名称")
    private String contactName;

    @ApiModelProperty(value = "联系电话")
    private String contactNumber;

    @ApiModelProperty(value = "联系邮箱")
    private String contactEmail;

    @ApiModelProperty(value = "注册时间")
    private String registrationTime;

    @ApiModelProperty(value = "账户状态")
    private String accountState;

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

    @ApiModelProperty(value = "产品版本")
    private String productVersion;

    @ApiModelProperty(value = "开户时间")
    private String openAccountTime;
}
