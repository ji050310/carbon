package com.carbon.assets.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.carbon.domain.common.annotation.Dict;
import com.carbon.assets.common.enums.ExchangeAccountStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 交易账户  查询结果对象
 * </p>
 *
 * @author Li Jun
 * @since 2021-08-16
 */
@Data
@ApiModel(value = "ExchangeAccountQueryVo对象", description = "交易账户 查询参数")
public class ExchangeAccountQueryVo implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "账户名")
    private String accountName;

    @ApiModelProperty(value = "碳交易所名称")
    private String carbonExchangeName;

    @Dict(dictCode = "043")
    @ApiModelProperty(value = "开户状态")
    private String accountStatus;

    @ApiModelProperty(value = "开户时间")
    private Date createdTime;

    @ApiModelProperty(value = "业务范围")
    private String businessScope;

    @ApiModelProperty(value = "该交易所url")
    private String url;

    @ApiModelProperty(value = "更新时间")
    private Date updatedTime;

    @ApiModelProperty(value = "cookie")
    private String cookie;
}
