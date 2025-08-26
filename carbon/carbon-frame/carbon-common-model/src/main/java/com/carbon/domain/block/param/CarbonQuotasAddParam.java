package com.carbon.domain.block.param;

import lombok.Data;

/**
 * 碳配额资产上链 参数
 * @author : Li Jun
 * @since : 2021-09-10 16:30
 **/
@Data
public class CarbonQuotasAddParam {

    /**业务数据id*/
    String tokenId;
    /**用户账户id*/
    String userId;
    /**填写机构唯一编码值*/
    String orgName;
    /**碳资产上链渠道ID*/
    String utilityId;
    /**买方id*/
    String partyId;
    /**开始时间*/
    String fromDate;
    /**结束时间*/
    String thruDate;
    /**数量*/
    int quotaIssuedAmount;
    /**单位*/
    String quotaIssuedUom;
    /**文档文件,文件的hash*/
    String emissionsDoc;
    /**同步异步标志,true同步，false异步，默认false*/
    boolean sign;
}
