package com.carbon.auth.service;

public interface SmsService {

    /**
     * 发送短信
     * @param phone 手机号
     * @param templateCode 短信模板
     * @param content 发送内容
     */
    void sendMsg(String phone, String templateCode, String content);

    /**
     * 发送注册验证码
     * @param phone 手机号
     */
    void sendRegisterCode(String phone);

    /**
     * 发送忘记密码 验证码
     * @param phone 手机号
     */
    void sendForgotPasswordCode(String phone);

    /**
     * 校验验证码
     * @param phone 手机号
     * @param code 验证码
     * @param type 验证码类型 RedisKeyName.SMS_REGISTER_KEY
     */
    void checkValidateCode(String phone, String code,String type);






}
