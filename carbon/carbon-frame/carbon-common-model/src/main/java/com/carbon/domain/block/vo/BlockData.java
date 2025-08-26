package com.carbon.domain.block.vo;

import lombok.Data;

/**
 * 长安链返回值
 * @author : Li Jun
 * @since : 2021-09-14 10:59
 **/
@Data
public class BlockData {
    private String txId;

    public String getTxId() {
        return txId;
    }

    public void setTxId(String txId) {
        this.txId = txId;
    }
}
