package com.carbon.domain.mall;

import lombok.Data;

import java.io.Serializable;

/**
 * 调用微信API的附加信息
 */
@Data
public class AttachModel implements Serializable {
    // 订单类型
    String orderType;

    // 下单用户
    Long payUser;

    // 是否使用碳积分
    Boolean isUseCarbonIntegral = false;

    // 使用碳积分数量
    Integer CarbonIntegralNumber = 0;
}
