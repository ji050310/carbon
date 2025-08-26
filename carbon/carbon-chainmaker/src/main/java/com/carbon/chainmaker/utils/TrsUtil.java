package com.carbon.chainmaker.utils;

import com.carbon.common.exception.CommonBizException;
import com.carbon.domain.chainmaker.vo.ChainMakerRtVO;
import com.carbon.domain.common.ApiCode;
import com.carbon.domain.common.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.chainmaker.pb.common.ChainmakerTransaction;
import org.chainmaker.pb.common.ResultOuterClass;
import org.chainmaker.sdk.ResponseInfo;
import java.util.Map;


/**
 * file:StringUtil
 * <p>
 * 文件简要说明
 *
 * @author 2021-04-09 hgl 创建初始版本
 * @version V1.0  简要版本说明
 * @par 版权信息：
 * 2021 Copyright 微芯院 All Rights Reserved.
 */
@Slf4j
public class TrsUtil {

    private static final String CALL_OK = "OK";
    private static final String CALL_OK_Q = "SUCCESS";

    /**
     * 将Object对象里面的属性和值转化成Map对象

     */
    public static Map<String, String> objectToMap(Object obj) throws CommonBizException {
        Map<String, String> map;
        try {
            map = BeanUtils.describe(obj);
        } catch (Exception e) {
            throw new CommonBizException("map 转换异常");
        }
        return map;
    }


    /**
     * 转换 调用合约 返回结果
     * @param responseInfo
     * @param fun
     * @param sync 是否同步
     * @return
     */
    public static ApiResult<ChainMakerRtVO> trs(ResponseInfo responseInfo, String fun, boolean sync) {
        ChainmakerTransaction.TransactionInfo resultTxInfo = responseInfo.getSyncResultTxInfo();
        ChainMakerRtVO rtVo = new ChainMakerRtVO();
        rtVo.setTxId(responseInfo.getTxId());

        if (responseInfo.getTxResponse() == null) {
            return ApiResult.fail("返回数据为空，可能请求超时");
        }
        if (!CALL_OK.equals(responseInfo.getTxResponse().getMessage())) {
            log.info(responseInfo.toString());
            return ApiResult.fail(responseInfo.getTxResponse().getMessage());
        }
        if (resultTxInfo == null) {
            if (sync) {
                return ApiResult.ok(rtVo);
            } else {
                return ApiResult.result(6000,"上链中",rtVo);
            }

        }
        ResultOuterClass.Result result = resultTxInfo.getTransaction().getResult();
        if (result != null) {
            if (ResultOuterClass.TxStatusCode.SUCCESS.getNumber() != result.getCode().getNumber()) {
                log.error("执行合约失败:" + result.getCode().name() + "  ==>  " + result.getContractResult().getMessage());
                return ApiResult.fail("执行合约失败：" + result.getContractResult().getMessage());
            }
        }

        if (sync) {
            return ApiResult.ok(rtVo);
        } else {
            return ApiResult.result(6000,"上链中",rtVo);
        }
    }

    /**
     * 转换 调用合约 返回结果
     */
    public static ApiResult<String> trsQ(ResponseInfo responseInfo, long syncTimeOut) {
        ResultOuterClass.TxResponse txResponse = responseInfo.getTxResponse();
        if (txResponse == null) {
            return ApiResult.fail("返回数据为空，可能请求超时");
        }
        ResultOuterClass.ContractResultOrBuilder result = txResponse.getContractResult();
        if (!CALL_OK_Q.equals(responseInfo.getTxResponse().getMessage()) && result == null) {
            log.info(responseInfo.toString());
            return ApiResult.fail(responseInfo.getTxResponse().getMessage());
        }

        if (result != null) {
            if (ResultOuterClass.TxStatusCode.SUCCESS.getNumber() != result.getCode().getNumber()) {
                log.error("执行合约失败:" + result.getCode().name() + "  ==>  " + result.getMessage());
                return ApiResult.fail( "执行合约失败：" + result.getMessage());
            }
        }
        return ApiResult.result(ApiCode.SUCCESS,result.getResult().toStringUtf8());
    }
}
