package com.carbon.auth.service.impl;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.carbon.auth.entity.WxVo;
import com.carbon.auth.service.WeChatLoginService;
import com.carbon.common.redis.RedisService;
import com.carbon.common.utils.WechatUtil;
import com.carbon.common.utils.WxJwtUtils;
import com.carbon.domain.common.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class WeChatLoginServiceImpl implements WeChatLoginService
{
    @Autowired
    private RedisService redisService;

    @Override
    public ApiResult Wxlogin(WxVo wxVo)
    {
        System.out.println("rawData:--"+wxVo.getRawData());
        // 用户非敏感信息：rawData
        // 签名：signature
        JSONObject rawDataJson = JSON.parseObject(wxVo.getRawData());
        // 1.接收小程序发送的code
        // 2.开发者服务器 登录凭证校验接口 appi + appsecret + code
        JSONObject SessionKeyOpenId = WechatUtil.getSessionKeyOrOpenId(wxVo.getCode());
        // 3.接收微信接口服务 获取返回的参数
        String openid = SessionKeyOpenId.getString("openid");
        String sessionKey = SessionKeyOpenId.getString("session_key");

        // 4.校验签名 小程序发送的签名signature与服务器端生成的签名signature2 = sha1(rawData + sessionKey)
        String signature2 = DigestUtils.sha1Hex(wxVo.getRawData() + sessionKey);
        if (!wxVo.getSignature().equals(signature2)) {
            return ApiResult.fail("微信签名校验失败");
        }

        //------执行业务-----
        //存储用户微信数据
        Map<String,String> map=new HashMap<>();
        String nickName = rawDataJson.getString("nickName");
        String avatarUrl = rawDataJson.getString("avatarUrl");
        String token= WxJwtUtils.createToken(openid);
        //登录信息存入redis
        redisService.setEx(openid,rawDataJson.toJSONString(),36000, TimeUnit.SECONDS);

        map.put("msg:","微信签名校验成功");
        map.put("nickName",nickName);
        map.put("OpenId",openid);
        map.put("sessionKey",sessionKey);
        map.put("AvatarUrl",avatarUrl);
        map.put("wxtoken",token);
        return ApiResult.ok(map);
    }
}
