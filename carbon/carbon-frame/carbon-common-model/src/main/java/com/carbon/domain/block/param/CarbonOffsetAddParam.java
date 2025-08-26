package com.carbon.domain.block.param;

import lombok.Data;

/**
 * 碳减排资产上链 参数
 * @author : Li Jun
 * @since : 2021-09-10 16:30
 **/
@Data
public class CarbonOffsetAddParam {
    /**业务数据id */
    String tokenId;
    /**用户账户id */
    String userId;
    /**填写机构唯一编码值： 如：xihu_taxi*/
    String orgName;
    /**碳减排项目ID：CCER1000 */
    String projectId;
    /**项目注册登记机构：CCER */
    String registry;
    /**范围：Forestry & Land Use */
    String scope;
    /**方法：VM0010 */
    String methodology;
    /**项目所在国家或地区： China*/
    String region;
    /**开发者：深圳碳普惠 */
    String developer;
    /**发布总量 */
    long totalIssued;
    /**抵消总量 */
    long totalRetired;
    /** 剩余总量*/
    long totalRemaining;
    /**项目开始年：2020*/
    String firstProjectYear;
    /**发布总量年限 */
    String totalIssuedFutureYears;
    /**抵消总量年限 */
    String totalRetiredUnknownYears;
    /**项目拥有者： 深圳碳普惠*/
    String projectOwner;
    /**抵消项目操作员：深圳碳普惠经纪人 */
    String offsetProjectOperator;
    /**项目授权指定人 */
    String authorizedProjectDesignee;
    /**审核者 ：生态环境部*/
    String verifier;
    /**状态 */
    String voluntaryStatus;
    /**项目登记日期*/
    String projectListed;
    /**项目注册日期 ：2020/6/22*/
    String projectRegistered;
    /**项目类型 */
    String projectType;
    /**备注 */
    String notes;
    /**数据添加日期 ：2021/4/14*/
    String dateAdded;
    /**来源*/
    String source;
    /**来源类型 :Api 接入*/
    String sourceType;
    /**同步异步标志,true同步，false异步，默认false*/
    boolean sign;
}
