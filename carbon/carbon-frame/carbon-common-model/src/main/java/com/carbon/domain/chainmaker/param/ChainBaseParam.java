package com.carbon.domain.chainmaker.param;

import com.carbon.domain.common.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ChainBaseParam extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "上传长安链的 业务id")
    private String tokenId;

    @ApiModelProperty(value = "区块链普通账户id, 默认为123")
    private String userId = "123";

    @ApiModelProperty(value = "区块链机构名称, 默认为")
    private String orgName = "xcarbonchain1";

}
