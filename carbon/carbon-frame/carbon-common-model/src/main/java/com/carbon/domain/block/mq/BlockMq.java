package com.carbon.domain.block.mq;

import lombok.Data;

/**
 * 放在redis里面的值
 */
@Data
public class BlockMq<T> {

    private String msgId;

    private String status;

    private T  result;

    private Class<T> typeName;
}
