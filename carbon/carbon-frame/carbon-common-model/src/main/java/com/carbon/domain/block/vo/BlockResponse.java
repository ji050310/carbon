package com.carbon.domain.block.vo;

import com.carbon.domain.common.ApiResult;
import lombok.Data;

/**
 * 长安链返回值
 * @author : Li Jun
 * @since : 2021-09-14 10:59
 **/
@Data
public class BlockResponse<T> {
    private int code;
    private String message;
    private T data;

}
