package com.carbon.trade.vo;

import com.carbon.domain.common.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class MetaregistryDataVo extends BaseEntity {
    private String projectScope;

    private String projectScopeCode;

    @ApiModelProperty(value = "项目类型")
    private String projectScopeType;

    private String projectScopeTypeCode;
}
