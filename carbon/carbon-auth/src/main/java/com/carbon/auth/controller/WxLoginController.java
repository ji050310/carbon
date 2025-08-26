package com.carbon.auth.controller;


import com.carbon.auth.entity.WxVo;
import com.carbon.auth.service.WeChatLoginService;
import com.carbon.domain.common.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author cbd
 * @since 2022-07-21
 */
@Controller
@Api(tags = "WeixinController", description = "微信登录管理")
@RequestMapping("/weixin")
public class WxLoginController {

    @Autowired
    WeChatLoginService weChatLoginService;

    @ApiOperation("微信登录")
    @PostMapping(value = "/login")
    @ResponseBody
    public ApiResult Wxlogin(@RequestBody WxVo wxVo) {
        return weChatLoginService.Wxlogin(wxVo);
    }

}

