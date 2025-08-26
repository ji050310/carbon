package com.carbon.domain.mall.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 返回给前端的提供给前端进行调起支付
 */
@Data
@Accessors(chain = true)
@ApiModel(value="JsapiResultVo对象", description="预付单信息")
public  class JsapiResultVo implements Serializable {
    private static final long serialVersionUID = 4465376277943307271L;
    /**
     * appid
     */
    @ApiModelProperty(value = "appid")
    private String appId;
    /**
     * 随机时间戳
     */
    @ApiModelProperty(value = "随机时间戳")
    private String timeStamp;
    /***
     * 随机字符串
     */
    @ApiModelProperty(value = "随机字符串随机字符串")
    private String nonceStr;
    /**
     * prepay_id
     */
    @ApiModelProperty(value = "prepay_id 也就是订单id")
    private String packageValue;
    /**
     * 签名类型
     */
    @ApiModelProperty(value = "签名方式 默认是RSA")
    private String signType;
    /**
     * 签名信息
     */
    @ApiModelProperty(value = "签名")
    private String paySign;

    private String getSignStr() {
        return String.format("%s\n%s\n%s\n%s\n", appId, timeStamp, nonceStr, packageValue);
    }
}
