package com.carbon.assets.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.carbon.domain.common.BaseEntity;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * <p>
 * 碳交易所
 * </p>
 *
 * @author Li Jun
 * @since 2022-04-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="CarbonExchange对象", description="碳交易所 ")
public class CarbonExchange extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "交易所名称")
    private String name;

    @ApiModelProperty(value = "交易所名称字典（字典：017）")
    private String dict;

    @ApiModelProperty(value = "状态 1 启用 2 禁用")
    private Integer status;

    @ApiModelProperty(value = "交易所类型")
    private Integer type;

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

    @ApiModelProperty(value = "官网")
    private String website;

    @ApiModelProperty(value = "交易所详情文档url")
    private String detailUrl;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "电话")
    private String phone;

}
