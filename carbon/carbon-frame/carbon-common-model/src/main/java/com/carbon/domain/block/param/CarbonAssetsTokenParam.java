package com.carbon.domain.block.param;

import lombok.Data;

/**
 * 中和资产列表查询 参数
 * @author : Li Jun
 * @since : 2021-09-10 16:30
 **/
@Data
public class CarbonAssetsTokenParam {

    /**用户账户id*/
    String userId;
    /**填写机构唯一编码值*/
    String orgName;

    /**中和资产tokenId*/
    String tokenId;
}
