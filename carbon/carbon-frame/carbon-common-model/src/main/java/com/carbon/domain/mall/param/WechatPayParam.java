package com.carbon.domain.mall.param;


import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <pre>
 * 统一下单请求参数对象.
 * 参考文档：https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter3_1_1.shtml
 * https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter3_2_1.shtml
 * https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter3_3_1.shtml
 * https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter3_4_1.shtml
 * https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter3_5_1.shtml
 * </pre>
 *
 * @author thinsstar
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@ApiModel(value="WechatPayParam对象", description="微信支付参数 具体看微信官方文档 https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter3_2_1.shtml")
public class WechatPayParam implements Serializable {
    private static final long serialVersionUID = 1L;    /**
     * <pre>
     * 字段名：商品描述
     * 变量名：description
     * 是否必填：是
     * 类型：string[1,127]
     * 描述：
     *  商品描述
     *  示例值：Image形象店-深圳腾大-QQ公仔
     * </pre>
     */
    @SerializedName(value = "description")
    protected String description;
    /**
     * <pre>
     * 字段名：商户订单号
     * 变量名：out_trade_no
     * 是否必填：是
     * 类型：string[6,32]
     * 描述：
     *  商户系统内部订单号，只能是数字、大小写字母_-*且在同一个商户号下唯一
     *  示例值：1217752501201407033233368018
     * </pre>
     */
    @SerializedName(value = "out_trade_no")
    protected String outTradeNo;
    /**
     * <pre>
     * 字段名：交易结束时间
     * 变量名：time_expire
     * 是否必填：是
     * 类型：string[1,64]
     * 描述：
     *  订单失效时间，遵循rfc3339标准格式，格式为YYYY-MM-DDTHH:mm:ss+TIMEZONE，YYYY-MM-DD表示年月日，T出现在字符串中，表示time元素的开头，HH:mm:ss表示时分秒，TIMEZONE表示时区（+08:00表示东八区时间，领先UTC 8小时，即北京时间）。例如：2015-05-20T13:29:35+08:00表示，北京时间2015年5月20日 13点29分35秒。
     *  示例值：2018-06-08T10:34:56+08:00
     * </pre>
     */
    @SerializedName(value = "time_expire")
    protected String timeExpire;
    /**
     * <pre>
     * 字段名：附加数据
     * 变量名：attach
     * 是否必填：否
     * 类型：string[1,128]
     * 描述：
     *  附加数据，在查询API和支付通知中原样返回，可作为自定义参数使用
     *  示例值：自定义数据
     * </pre>
     */
    @SerializedName(value = "attach")
    protected String attach;

    /**
     * <pre>
     * 字段名：订单优惠标记
     * 变量名：goods_tag
     * 是否必填：否
     * 类型：string[1,256]
     * 描述：
     *  订单优惠标记
     *  示例值：WXG
     * </pre>
     */
    @SerializedName(value = "goods_tag")
    private String goodsTag;
    /**
     * <pre>
     * 字段名：订单金额
     * 变量名：amount
     * 是否必填：是
     * 类型：object
     * 描述：
     *  订单金额信息
     * </pre>
     */
    @SerializedName(value = "amount")
    private Amount amount;
    /**
     * <pre>
     * 字段名：支付者
     * 变量名：payer
     * 是否必填：是
     * 类型：object
     * 描述：
     *  支付者信息
     * </pre>
     */
    @SerializedName(value = "payer")
    private Payer payer;


    @Data
    @NoArgsConstructor
    public static class Amount implements Serializable {
        private static final long serialVersionUID = 1L;
        /**
         * <pre>
         * 字段名：总金额
         * 变量名：total
         * 是否必填：是
         * 类型：int
         * 描述：
         *  订单总金额，单位为分。
         *  示例值：100
         * </pre>
         */
        @SerializedName(value = "total")
        private Integer total;
        /**
         * <pre>
         * 字段名：币类型
         * 变量名：currency
         * 是否必填：否
         * 类型：string[1,16]
         * 描述：
         *  CNY：人民币，境内商户号仅支持人民币。
         *  示例值：CNY
         * </pre>
         */
        @SerializedName(value = "currency")
        private String currency;
    }

    @Data
    @NoArgsConstructor
    public static class Payer implements Serializable {
        private static final long serialVersionUID = -1L;
        /**
         * <pre>
         * 字段名：用户标识
         * 变量名：openid
         * 是否必填：是
         * 类型：string[1,128]
         * 描述：
         *  用户在直连商户appid下的唯一标识。
         *  示例值：oUpF8uMuAJO_M2pxb1Q9zNjWeS6o
         * </pre>
         */
        @SerializedName(value = "openid")
        private String openid;
    }

}
