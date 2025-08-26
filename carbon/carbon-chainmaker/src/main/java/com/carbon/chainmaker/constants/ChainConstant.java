package com.carbon.chainmaker.constants;

/**
 * @description: 链常量
 * @author: HAO
 * @create: 2021-08-18 19:36
 **/
public class ChainConstant {

    public static final String CHAINCODE_NAME = "xcarbon_fact1";

    // 合约基本方法
    public static final String FUNC_ADD = "save";
    public static final String FUNC_FIND = "find_by_key";
    public static final String FUNC_UPDATE = "update_by_key";
    public static final String FUNC_CHECK = "data_check";

    // 用户注册
    public static final String FUNC_REGISTER_ADMIN = "registerAdmin";
    public static final String FUNC_REGISTER_USER = "registerUser";

    // 注册成为交易员
    public static final String FUNC_REGISTER_EXCHANGEACCOUNT= "registerExchangeAccount";


    // 碳资产-配额
    public static final String FUNC_QUOTAS = "saveQuotas";

    // 碳资产-信用资产
    public static final String FUNC_CREDIT = "saveCredit";
    //
    public static final String FUNC_CARBON_METAREGISTRY = "addCarbonMetaregistry";
    //
    public static final String FUNC_OFFSET ="saveOffset";

    // 清空账户体系
    public static final String FUNC_RESET_ACCOUNT = "resetAccount";

    // 碳交易履约
    public static final String FUNC_TRADE_CONTRACT = "addTradeContract";

    // 根据tokenId查询 上链信息
    public static final String QUERY_TOKEN_INFO = "queryTokenInfo";

    //中和资产核证上链
    public static final String ASSETS_CERTIFIED = "saveCertified";

    // 中和碳资产交易
    public static final String ASSETS_BUSINESS = "saveBusiness";
}
