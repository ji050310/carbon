package com.carbon.common.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Bae
 * @version 1.0
 * @date 2022/5/18 20:54
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    // 当前登录用户的open id，要确保与生成 signature 使用的 user_access_token 相对应
    @ApiModelProperty(value = "当前登录用户的open id，要确保与生成 signature 使用的 user_access_token 相对应.")
    public String openId;

    // 签名
    @ApiModelProperty(value = "签名")
    public String signature;

    // 应用 appId
    @ApiModelProperty(value = "应用 appId")
    public String appId;

    // 时间戳（毫秒）
    @ApiModelProperty(value = "时间戳（毫秒）")
    public String timestamp;

    // 随机字符串
    @ApiModelProperty(value = "随机字符串")
    public String nonceStr;

    // 参与加密计算的url
    @ApiModelProperty(value = "参与加密计算的url")
    public String url;

    // 指定要使用的组件列表，请根据对应组件的开发文档填写。如云文档组件，填写['DocsComponent']
    @ApiModelProperty(value = "指定要使用的组件列表，请根据对应组件的开发文档填写。如云文档组件，填写['DocsComponent']")
    public String jsApiList;

    // 指定组件的国际化语言：en-US-英文、zh-CN-中文、ja-JP-日文
    @ApiModelProperty(value = "指定组件的国际化语言：en-US-英文、zh-CN-中文、ja-JP-日文")
    public String locale;

    @ApiModelProperty(value = "文档url")
    public String textUrl;

    @ApiModelProperty(value = "文档token")
    public String textToken;

    @ApiModelProperty(value = "刷新token")
    public String refreshToken;


}
