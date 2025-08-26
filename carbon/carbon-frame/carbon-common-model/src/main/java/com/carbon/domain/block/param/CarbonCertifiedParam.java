package com.carbon.domain.block.param;

import lombok.Data;

/**
 * 中和资产核证 参数
 * @author : Li Jun
 * @since : 2021-09-10 16:30
 **/
@Data
public class CarbonCertifiedParam {

    /**用户账户id*/
    String userId;
    /**填写机构唯一编码值*/
    String orgName;
    /**原始业务数据tokenid*/
    String emissionsRecordsToAudit;
    /**自动失效时间（过期不可交易）:2022-08-16 10:00:00*/
    String automaticRetireDate;
    /**发行者ID*/
    String publishId;
    /**收件人ID*/
    String receivingId;
    /**资产类型:0碳减排，1碳排放，2碳配额*/
    String assetType;
    /**到日期时间戳记:2022-08-16 10:00:00*/
    String timeScope;
    /**元数据*/
    String metaData;
    /**清单*/
    String mainList;
    /**同步异步标志,true同步，false异步，默认false*/
    boolean sign;
}
