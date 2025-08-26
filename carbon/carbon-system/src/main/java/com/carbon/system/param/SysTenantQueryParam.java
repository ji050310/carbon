package com.carbon.system.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import com.carbon.domain.common.QueryParam;

/**
 * <p>
 * 租户  查询参数对象
 * </p>
 *
 * @author ChengJX
 * @since 2022-08-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="SysTenantQueryParam对象", description="租户 查询参数")
public class SysTenantQueryParam extends QueryParam {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "搜索Key")
    private String searchKey;

    @ApiModelProperty(value = "租户名称")
    private String tenantName;

    @ApiModelProperty(value = "租户机构唯一编码")
    private String orgName;

    @ApiModelProperty(value = "状态")
    private String tenantStatus;

    @ApiModelProperty(value = "类型")
    private String type;

    @ApiModelProperty(value = "行业")
    private String industry;


//    @ApiModelProperty(value = "统一社会信用代码")
//    private String socialCreditCode;
//
//    @ApiModelProperty(value = "营业执照")
//    private String businessLicense;

}
