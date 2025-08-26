package com.carbon.system.vo;

import com.carbon.domain.common.annotation.Dict;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 数据面板 账户
 * </p>
 *
 * @author Li Jun
 * @since 2021-07-20
 */
@Data
@ApiModel(value="StatAccountVo", description="数据面板 账户")
public class StatAccountVo implements Serializable{
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "账户名")
    private String accountName;

    @Dict(dictCode = "040")
    @ApiModelProperty(value = "产品版本（字典：040）")
    private String productVersion;

    @Dict(dictCode = "038")
    @ApiModelProperty(value = "账户类型（字典：038）")
    private String accountType;

    @ApiModelProperty(value = "企业名称")
    private String enterpriseName;

    @ApiModelProperty(value = "碳中和比例")
    private BigDecimal carbonNeutralRatio;

    @ApiModelProperty(value = "ESG评分")
    private BigDecimal esgScore;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "账户有效期")
    private Date accountValidity;

    @ApiModelProperty(value = "账户是否过期")
    private Boolean expired;

    @ApiModelProperty(value = "头像url")
    private String avatar;

    @ApiModelProperty(value = "角色名称")
    private List<String> roleNames;
}
