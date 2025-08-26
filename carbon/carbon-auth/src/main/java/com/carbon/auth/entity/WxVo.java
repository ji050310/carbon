package com.carbon.auth.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class WxVo
{
    @ApiModelProperty(value = "前端wx.login方法返回的code")
    String code;
    @ApiModelProperty(value = "前端wx.getUserInfo方法返回rawData参数")
    String rawData;
    @ApiModelProperty(value = "前端wx.getUserInfo方法返回signature参数")
    String signature;
}
