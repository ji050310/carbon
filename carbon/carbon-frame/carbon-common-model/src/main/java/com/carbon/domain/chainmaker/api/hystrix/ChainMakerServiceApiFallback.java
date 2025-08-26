package com.carbon.domain.chainmaker.api.hystrix;

import com.carbon.domain.block.param.CarbonBusinessParam;
import com.carbon.domain.block.param.CarbonCertifiedParam;
import com.carbon.domain.block.param.CarbonOffsetAddParam;
import com.carbon.domain.chainmaker.api.ChainMakerServiceApi;
import com.carbon.domain.chainmaker.param.CarbonCreditAssetsParam;
import com.carbon.domain.chainmaker.param.CarbonQuotaAssetsParam;
import com.carbon.domain.chainmaker.param.CarbonTradeContractParam;
import com.carbon.domain.chainmaker.vo.ChainMakerRtVO;
import com.carbon.domain.chainmaker.vo.TransactionRtVO;
import com.carbon.domain.common.ApiResult;
import com.carbon.domain.mq.entity.AddTradingAccountApproval;
import com.carbon.domain.mq.entity.AssetUploadApproval;
import com.carbon.domain.mq.entity.ProjectApproval;
import com.carbon.domain.system.param.ChangePasswordParam;
import com.carbon.domain.system.param.SysAccountParam;
import com.carbon.domain.system.vo.SysAccountModelVo;
import com.carbon.domain.system.vo.SysTenantModelVo;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @author Li Jun
 */
@Component
public class ChainMakerServiceApiFallback implements FallbackFactory<ChainMakerServiceApi> {

	@Override
	public ChainMakerServiceApi create(Throwable arg0) {

		ApiResult result = ApiResult.fail("系统服务不可用");

		return new ChainMakerServiceApi() {

			@Override
			public ApiResult<ChainMakerRtVO> addQuotaAssets(@Valid CarbonQuotaAssetsParam param) {
				return result;
			}

			@Override
			public ApiResult<ChainMakerRtVO> addCreditAssets(@Valid CarbonCreditAssetsParam param) {
				return result;
			}

			@Override
			public ApiResult<ChainMakerRtVO> addContract(@Valid CarbonTradeContractParam param) {
				return result;
			}

			@Override
			public ApiResult<ChainMakerRtVO> AddCarbonOffset(Map<String, String> param) {
				return result;
			}

			@Override
			public ApiResult<TransactionRtVO> queryTxByTxId(String txId) {
				return result;
			}

			@Override
			public ApiResult<ChainMakerRtVO> addCarbonMetaregistry(Map<String, String> map) {
				return null;
			}
			@Override
			public ApiResult<ChainMakerRtVO> addAssetsCertified(CarbonCertifiedParam param) {
				return result;
			}

			@Override
			public ApiResult<ChainMakerRtVO> addAssetsBusiness(CarbonBusinessParam param) {
				return result;
			}

			@Override
			public ApiResult<ChainMakerRtVO> registerAdmin(Map<String, String> map) {
				return result;
			}

			@Override
			public ApiResult<ChainMakerRtVO> registerExchangeAccount(Map<String, String> map) {
				return result;
			}


		};
	}
}
