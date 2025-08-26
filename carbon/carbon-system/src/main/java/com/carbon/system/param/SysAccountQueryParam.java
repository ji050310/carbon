package com.carbon.system.param;

import com.carbon.domain.common.QueryParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 帐号  查询参数对象
 * </p>
 *
 * @author Li Jun
 * @since 2021-06-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="SysAccountQueryParam对象", description="帐号 查询参数")
public class SysAccountQueryParam extends QueryParam {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "账户类型")
    private String accountType;

    @ApiModelProperty(value = "账户状态")
    private String accountStatus;

    @ApiModelProperty(value = "产品版本")
    private String productVersion;

    @ApiModelProperty(value = "关键字")
    private String keywords;

}
