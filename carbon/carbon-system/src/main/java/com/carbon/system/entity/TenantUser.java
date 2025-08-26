package com.carbon.system.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.carbon.domain.common.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * <p>
 * 租户-用户 
 * </p>
 *
 * @author Zhang Jinrui
 * @since 2022-05-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="TenantUser对象", description="租户-用户 ")
public class TenantUser extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "碳用户外部ID")
    private String carbonUserId;

    @ApiModelProperty(value = "姓名")
    private String userName;

    @ApiModelProperty(value = "性别 1.男性，2.女性，3.未知")
    private Integer sex;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "用户类型")
    private Integer userType;

    @ApiModelProperty(value = "状态")
    private Integer userStatus;

    @ApiModelProperty(value = "绿度积分")
    private Integer greennessIntegral;

    @ApiModelProperty(value = "绿度等级")
    private Integer greennessLevel;

    @ApiModelProperty(value = "来源渠道")
    private String sourceChannel;

    @ApiModelProperty(value = "返现余额")
    private BigDecimal balance;

    @ApiModelProperty(value = "碳减排量")
    private BigDecimal carbonReduction;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "所属租户")
    private Long tenantId;

    @ApiModelProperty(value = "低碳等级")
    private String carbonLevel;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "碳信分")
    private Integer carbonCredit;

    @ApiModelProperty(value = "分组")
    private String sectionalization;

}
