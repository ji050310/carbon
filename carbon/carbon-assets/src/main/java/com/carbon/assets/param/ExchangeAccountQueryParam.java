package com.carbon.assets.param;

import com.carbon.domain.common.QueryParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 交易账户  查询参数对象
 * </p>
 *
 * @author Li Jun
 * @since 2021-08-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="ExchangeAccountQueryParam对象", description="交易账户 查询参数")
public class ExchangeAccountQueryParam extends QueryParam {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "关键词(账户名)")
    private String keyWords;

    @ApiModelProperty(value = "账户状态 （参考sys_dict_item表）")
    private Integer accountStatus;

    @ApiModelProperty(value = "账户类型：0.管理员 1.普通账户")
    private Integer accountType;

    @ApiModelProperty(value = "交易经纪人ID")
    private String brokerId;

    @ApiModelProperty(value = "碳交易所ID")
    private String carbonExchangeId;
}
