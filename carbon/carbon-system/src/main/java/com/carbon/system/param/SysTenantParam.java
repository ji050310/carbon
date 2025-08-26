package com.carbon.system.param;

import cn.hutool.core.date.DateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 修改企业参数
 * </p>
 *
 * @author Li Jun
 * @since 2021-06-18
 */
@Data
@ApiModel(value = "SysTenantParam", description = "修改企业参数")
public class SysTenantParam implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "企业id")
    private Long id;

    @ApiModelProperty(value = "企业名称",required = true)
    private String tenantName;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "电话")
    private String telephone;

    @ApiModelProperty(value = "传真")
    private String faxNumber;

//    @ApiModelProperty(value = "联系人姓名")
//    private String contactsName;
//
//    @ApiModelProperty(value = "联系人职位")
//    private String contactsPosition;
//
//    @ApiModelProperty(value = "联系人手机")
//    private String contactsPhone;
//
//    @ApiModelProperty(value = "联系人电子邮件")
//    private String contactsEmail;

    @ApiModelProperty(value = "行业领域")
    private String industry;

    @ApiModelProperty(value = "开户日期")
    private Date createdTime;

    @ApiModelProperty(value = "账户有效期")
    private Date validityDate;

    @ApiModelProperty(value = "企业介绍")
    private String introduction;

}
