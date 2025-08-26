package com.carbon.chainmaker.common;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.carbon.chainmaker.utils.TrsUtil;
import com.carbon.common.enums.ExpCodeEnum;
import com.carbon.common.exception.CommonBizException;
import com.carbon.domain.chainmaker.vo.ChainMakerRtVO;
import com.carbon.domain.chainmaker.vo.TransactionRtVO;
import com.carbon.domain.common.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.chainmaker.pb.common.ChainmakerBlock;
import org.chainmaker.pb.common.ChainmakerTransaction;
import org.chainmaker.pb.common.Request;
import org.chainmaker.pb.common.ResultOuterClass;
import org.chainmaker.sdk.ChainClient;
import org.chainmaker.sdk.ChainClientException;
import org.chainmaker.sdk.ResponseInfo;
import org.chainmaker.sdk.crypto.ChainMakerCryptoSuiteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description: 智能合约处理类
 * @Package: com.kingsft.fabric.manger
 * @ClassName: ChaincodeManger
 * @author: wupeng
 * @email: wupeng0501@163.com
 * @createDate: 2020/7/14 10:02
 * <p>
 * ---------------------------------------------------------
 * Version    Author    Status    Date
 * V1.0       wupeng    C         2020/7/14
 */
@Slf4j
@Component
public class ChainMakerSdkManger {

    private final static String CONTRACT_ARGS_ISSUE_LIMIT = "issue_limit";
    private final static String CONTRACT_ARGS_TOTAL_SUPPLY = "total_supply";
    private final static String CONTRACT_ARGS_DEFAULT_AMOUNT = "1000000000";


    @Value("${chain.timeout.rpc}")
    private Long rpcTimeout;
    @Value("${chain.timeout.sync}")
    private Long syncTimeout;
    @Value("${chain.timeout.block}")
    private Long blockimeout;

    @Autowired
    private ChainClient chainClient;

    public ApiResult<ChainMakerRtVO> invoke(String chainCodeName, String fcn, Map<String, String> params, boolean sync) throws CommonBizException {
        log.info("合约名称：{}", chainCodeName);
        log.info("合约方法名：{}", fcn);
        log.info("合约参数：{}", JSONUtil.toJsonPrettyStr(params));
        try {
            ResponseInfo responseInfo = null;
            if (sync) {
                responseInfo = chainClient.invokeContract(chainCodeName, fcn, params, rpcTimeout, syncTimeout);
            } else {
                responseInfo = chainClient.invokeContract(chainCodeName, fcn, params, rpcTimeout, 0);
            }

            ApiResult<ChainMakerRtVO> rt = TrsUtil.trs(responseInfo, fcn, sync);
            log.info("返回结果集:{}",JSONUtil.toJsonPrettyStr(rt));
            return rt;
        } catch (Exception e) {
            log.info("调用异常:{}",e.getMessage());
            throw new CommonBizException(ExpCodeEnum.UNKNOW_ERROR);
        }
    }

    public ApiResult<String> query(String ChainCodeName, String fcn, Map<String, String> params) throws CommonBizException {
        //查询合约
        try {
            ResponseInfo responseInfo = chainClient.queryContract(ChainCodeName, fcn, params, rpcTimeout);
            ApiResult<String> rt = TrsUtil.trsQ(responseInfo, syncTimeout);
            return rt;
        } catch (Exception e) {
            throw new CommonBizException(ExpCodeEnum.UNKNOW_ERROR);
        }
    }


    public ApiResult<TransactionRtVO> getTxByTxId(String txId) throws CommonBizException {

        //根据交易Id查询交易
        try {
            // 是否交易成功
            ChainmakerTransaction.TransactionInfo transactionInfo = chainClient.getTxByTxId(txId, rpcTimeout);
            boolean txBool = transactionInfo.getTransaction().getResult().getContractResult().getCode().getNumber() == 0;
            // 合约交易成功(相当于上链成功)
            if(txBool){
                TransactionRtVO vo = new TransactionRtVO();
                // 设置基本信息
                vo.setTxid(transactionInfo.getTransaction().getHeader().getTxId());
                vo.setBlockHeight(transactionInfo.getBlockHeight());
                vo.setTimestamp(transactionInfo.getTransaction().getHeader().getTimestamp());
                // 根据区块高度 调用 getBlockByHash 查询区块hash
                ChainmakerBlock.BlockInfo blockByHash = chainClient.getBlockByHash(String.valueOf(transactionInfo.getBlockHeight()), false, rpcTimeout);
                vo.setBlockHash(blockByHash.getBlock().getHeader().getBlockHash().toStringUtf8());

                // 构建Result ,返回的结构：
                /**
                 * {
                 *     code: 0 成功，非0,失败
                 *     message: ""或者错误谢谢，成功为"",失败为报错信息
                 *     result: 信息
                 * }
                 */
                Request.TransactPayload queryPayload = Request.TransactPayload.parseFrom(transactionInfo.getTransaction().getRequestPayload());
                Map<String, String> res = queryPayload.getParametersList().stream().collect(Collectors.toMap(Request.KeyValuePair::getKey, Request.KeyValuePair::getValue));
                JSONObject txInfo = new JSONObject();
                ResultOuterClass.ContractResult contractResult = transactionInfo.getTransaction().getResult().getContractResult();
                txInfo.put("code",contractResult.getCodeValue());
                txInfo.put("message",contractResult.getMessage());
                txInfo.put("result",res);

                return ApiResult.ok(vo);
            } else {
                String message = transactionInfo.getTransaction().getResult().getContractResult().getMessage().replace("contract message:", "");
                return ApiResult.fail(message);
            }
        } catch (ChainMakerCryptoSuiteException e) {
            throw new CommonBizException(ExpCodeEnum.UNKNOW_ERROR,e.getMessage());
        } catch (ChainClientException e) {
            throw new CommonBizException(ExpCodeEnum.UNKNOW_ERROR,e.getMessage());
        }catch (Exception e){
            throw new CommonBizException(ExpCodeEnum.UNKNOW_ERROR);
        }

    }


}
