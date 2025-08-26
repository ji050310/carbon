package com.carbon.domain.block.param;

import lombok.Data;

/**
 * 注册机构管理员账户 参数
 * @author : Li Jun
 * @since : 2021-09-10 16:30
 **/
@Data
public class AccountRegisterParam {

    /**用户账户id*/
    String userId;
    /**填写机构唯一编码值*/
    String orgName;
    /**用户在机构内部的组织架构关系*/
    String affiliation;
    /**同步异步标志,true同步，false异步，默认false*/
    boolean sign;
}
