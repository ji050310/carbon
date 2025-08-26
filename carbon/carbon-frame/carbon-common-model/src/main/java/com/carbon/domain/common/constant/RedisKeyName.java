package com.carbon.domain.common.constant;

/**
 * Date: 2022-03-26
 * Time: 11:38
 *
 * @author li jun
 */
public class RedisKeyName {

    /**
     * 字典类型列表
     */
    public static final String SYS_DICT_KEY = "sys:dict:";

    /**
     * 字典类型列表
     */
    public static final String ACCOUNT_NAME_CACHE = "account:name:";

    /**
     * 注册发送短信
     */
    public static final String SMS_REGISTER_KEY = "sms:register:";

    /**
     * 忘记密码发送短信
     */
    public static final String SMS_FORGOT_PASSWORD_KEY = "sms:forgotPassword:";

    /**
     * 修改手机号发送短信
     *
     */
    public static final String SMS_UPDATE_PHONE_KEY = "sms:updatePhone:";
    /**
     * 碳文章列表
     *
     */
    public static final String CA_ARTICLE_LIST_KEY = "ca:articleList:";

}
