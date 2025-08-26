package com.carbon.domain.common.constant;

import com.carbon.domain.block.param.*;
import com.carbon.domain.block.vo.CarbonAssetsToken;
import com.carbon.domain.block.vo.CarbonAssetsTokenInfo;

/**
 * <p> RocketMq 消息主题的tag名 <p/>
 * @author Cheng JX
 */
public class RocketMqTagName{
    /**
     *  消息topic (主题)是: BlockChain_MSG 下的不同标签
     */
    public static final String BlockChain_MSG_GetExchangeInfo = "GetExchangeInfo";

    public static final String BlockChain_MSG_RegisterAdmin = "RegisterAdmin";

    public static final String BlockChain_MSG_RegisterUser = "RegisterUser";

    public static final String BlockChain_MSG_AddCarbonQuotas = "AddCarbonQuotas";

    public static final String BlockChain_MSG_AddCarbonOffset = "AddCarbonOffset";

    public static final String BlockChain_MSG_AssetsCertified = "AssetsCertified";

    public static final String BlockChain_MSG_AssetsBusiness = "AssetsBusiness";

    public static final String BlockChain_MSG_QueryAssetsTokenList = "QueryAssetsTokenList";

    public static final String BlockChain_MSG_QueryAssetsTokenInfo = "QueryAssetsTokenInfo";

}
