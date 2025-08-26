package com.carbon.auth.service.impl;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.carbon.auth.service.SmsService;
import com.carbon.common.exception.CommonBizException;
import com.carbon.common.redis.RedisService;
import com.carbon.domain.common.constant.RedisKeyName;
import com.carbon.domain.common.constant.SmsConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class SmsServiceImpl implements SmsService {

    @Autowired
    private RedisService redisService;

    private static final String REGION_ID = "cn-hangzhou";
    private static final String SYS_DOMAIN = "dysmsapi.aliyuncs.com";
    private static final String SYS_VERSION = "2017-05-25";
    private static final String SYS_ACTION = "SendSms";

    // 签名
    @Value("${sms.sign}")
    private String smsSignContent;
    @Value("${aliyun.accesskey-id}")
    private String accesskeyId;
    @Value("${aliyun.accesskey-secret}")
    private String accesskeySecret;

    @Override
    public void sendMsg(String phone, String templateCode, String content) {
        DefaultProfile profile = DefaultProfile.getProfile(REGION_ID, accesskeyId, accesskeySecret);
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain(SYS_DOMAIN);
        request.setSysVersion(SYS_VERSION);
        request.setSysAction(SYS_ACTION);
        request.putQueryParameter("RegionId", REGION_ID);
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", smsSignContent);
        request.putQueryParameter("TemplateCode", templateCode);
        request.putQueryParameter("TemplateParam", content);
        try {
            CommonResponse response = client.getCommonResponse(request);
            log.info("调用发送短信返回结果，{}", JSONUtil.toJsonPrettyStr(response.getData()));

            JSONObject resultJson= JSON.parseObject(response.getData());
            if (!"OK".equals(resultJson.get("Message"))) {
                throw new CommonBizException("发送失败");
            }
        } catch (ClientException e) {
            e.printStackTrace();
            throw new CommonBizException("发送失败");
        }
    }

    @Override
    public void sendRegisterCode(String phone) {
        String key = RedisKeyName.SMS_REGISTER_KEY + phone;
        String validCode = "123546";
        System.out.println("已发送");
        HashMap<String, Object> param = new HashMap<>();
        param.put("code", validCode);

        redisService.setEx(key, validCode, 60, TimeUnit.SECONDS);
//        sendMsg(phone, SmsConstant.SMS_TEMPLATE_REGISTER, JSONUtil.toJsonStr(param));
    }

    @Override
    public void sendForgotPasswordCode(String phone) {
        String key = RedisKeyName.SMS_FORGOT_PASSWORD_KEY + phone;
        String validCode = "123546";
        HashMap<String, Object> param = new HashMap<>();
        param.put("code", validCode);

        redisService.setEx(key, validCode, 60000, TimeUnit.SECONDS);
//        sendMsg(phone, SmsConstant.SMS_TEMPLATE_REGISTER, JSONUtil.toJsonStr(param));
    }

    @Override
    public void checkValidateCode(String phone, String code,String type) {
        if (StrUtil.isEmpty(phone) || StrUtil.isEmpty(code)) {
            throw new CommonBizException("手机号或验证码为空");
        }
        String redisKey = type + phone;
        String redisMsgCode = redisService.get(redisKey);
        log.info("redisMsgCode：{}",redisMsgCode);
        if (StrUtil.isEmpty(redisMsgCode)) {
            throw new CommonBizException("验证码已过期");
        }
        if (!code.equals(redisMsgCode)) {
            throw new CommonBizException("请输入正确的验证码");
        }
        //验证码消费后失效
        redisService.delete(CollUtil.newArrayList(redisKey));
    }


}
