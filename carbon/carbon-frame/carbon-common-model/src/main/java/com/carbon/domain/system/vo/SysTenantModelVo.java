package com.carbon.domain.system.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.carbon.domain.common.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 租户
 * </p>
 *
 * @author Li Jun
 * @since 2022-04-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "SysTenant对象", description = "租户 ")
public class SysTenantModelVo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "租户名称")
    private String tenantName;

    @ApiModelProperty(value = "租户机构唯一编码")
    private String orgName;

    @ApiModelProperty(value = "状态")
    private Integer tenantStatus;

    @ApiModelProperty(value = "类型")
    private Integer type;

    @ApiModelProperty(value = "法人代表")
    private String legalRepresentative;

    @ApiModelProperty(value = "统一社会信用代码")
    private String socialCreditCode;

    @ApiModelProperty(value = "营业执照")
    private String businessLicense;

    @ApiModelProperty(value = "绿度积分")
    private Integer greennessIntegral;

    @ApiModelProperty(value = "绿度等级")
    private Integer greennessLevel;

    @ApiModelProperty(value = "行业")
    private String industry;

    @ApiModelProperty(value = "有效天数")
    private Integer validityDayNum;

    @ApiModelProperty(value = "电话")
    private String telephone;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "传真号")
    private String faxNumber;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "联系人姓名")
    private String contactsName;

    @ApiModelProperty(value = "联系人职位")
    private String contactsPosition;

    @ApiModelProperty(value = "联系人手机")
    private String contactsPhone;

    @ApiModelProperty(value = "联系人邮箱")
    private String contactsEmail;

    @ApiModelProperty(value = "企业介绍")
    private String introduction;

}
