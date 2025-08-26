package com.carbon.system.entity;

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
 * 兑换记录
 * </p>
 *
 * @author Zhang Jinrui
 * @since 2022-05-02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="CrmExchangeRecord对象", description="兑换记录")
public class CrmExchangeRecord extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户ID")
    private String userId;

    @ApiModelProperty(value = "兑换商品")
    private String exchangeProduct;

    @ApiModelProperty(value = "兑换积分")
    private Integer exchangeIntegral;

    @ApiModelProperty(value = "兑换日期")
    private Date exchangeTime;

}
