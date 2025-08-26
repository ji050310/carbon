package com.carbon.system.vo;

import cn.hutool.core.date.DateBetween;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.carbon.domain.common.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Optional;

/**
 * <p>
 * 租户  查询结果对象
 * </p>
 *
 * @author ChengJX
 * @since 2022-08-24
 */
@Data
@ApiModel(value="SysTenantQueryVo对象", description="租户 查询参数")
public class SysTenantQueryVo extends BaseEntity implements Serializable{
    private static final long serialVersionUID = 1L;

    /**
     *  状态 返回状态名
     *  类型的话 返回类型名
     *  开户日期 其实就是 createTime
     *  租户有效期 其实就是 开户日期+ 有效期
     */

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "租户名称")
    private String tenantName;

    @ApiModelProperty(value = "租户机构唯一编码")
    private String orgName;

    @ApiModelProperty(value = "状态")
    private String tenantStatus;

    @ApiModelProperty(value = "类型")
    private String type;

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


    @ApiModelProperty(value = "开户日期")
    private Date createdTime;

    @ApiModelProperty(value = "有效期")
    private Date validityTime;



    public  Integer getValidityDayNum(Date date){
            DateBetween dateBetween = new DateBetween(new Date(),date);
        return (int)dateBetween.between(DateUnit.DAY);
    }

}
