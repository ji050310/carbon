package com.carbon.domain.assets.vo;


import com.carbon.domain.assets.enums.ExchangeStatus;
import com.carbon.domain.common.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;

import java.util.Date;

/**
 * <p>
 * 碳交易所  查询结果对象
 * </p>
 *
 * @author Li Jun
 * @since 2022-04-23
 */
@Data
@ApiModel(value="CarbonExchangeQueryVo对象", description="碳交易所 查询参数")
public class CarbonExchangeQueryVo implements Serializable{
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "交易所名称")
    private String name;

    @ApiModelProperty(value = "交易所名称字典（字典：017）")
    private String dict;

    @Dict(dictCode = "030")
    @ApiModelProperty(value = "交易所类型")
    private String type;

    @Dict(enumType = ExchangeStatus.class)
    @ApiModelProperty(value = "状态")
    private Integer status;

    @ApiModelProperty(value = "城市")
    private String city;

    @ApiModelProperty(value = "简介")
    private String introduction;

    @ApiModelProperty(value = "业务区域")
    private String businessArea;

    @ApiModelProperty(value = "业务范围")
    private String businessScope;

    @ApiModelProperty(value = "法定代表人")
    private String legalPerson;

    @ApiModelProperty(value = "注册资本")
    private String registeredCapital;

    @ApiModelProperty(value = "成立时间")
    private Date establishmentTime;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "查看详情url")
    private String detailUrl;

    @ApiModelProperty(value = "开户官网")
    private String website;

    @ApiModelProperty(value = "交易官网")
    private String tradeWebsite;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "电话")
    private String phone;

    @ApiModelProperty(value = "创建时间")
    private Date createdTime;
}
