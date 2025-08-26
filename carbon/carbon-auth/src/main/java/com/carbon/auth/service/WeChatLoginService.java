package com.carbon.auth.service;

import com.carbon.auth.entity.WxVo;
import com.carbon.domain.common.ApiResult;


public interface WeChatLoginService
{
    ApiResult Wxlogin(WxVo wxVo);
}
