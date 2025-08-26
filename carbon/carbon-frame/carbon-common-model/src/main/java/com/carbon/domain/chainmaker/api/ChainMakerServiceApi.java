package com.carbon.domain.chainmaker.api;

import com.carbon.domain.block.param.CarbonBusinessParam;
import com.carbon.domain.block.param.CarbonCertifiedParam;
import com.carbon.domain.block.param.CarbonOffsetAddParam;
import com.carbon.domain.chainmaker.api.hystrix.ChainMakerServiceApiFallback;
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
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;


/**
 * 系统服务Api
 * @author Li Jun
 */
@FeignClient(value = "carbon-chaninmaker", fallbackFactory = ChainMakerServiceApiFallback.class)
public interface ChainMakerServiceApi {

	/**
	 * 碳配额资产上链
	 * @param param CarbonQuotaAssetsParam
	 * @return Boolean
	 */
	@PostMapping("/chaninmaker/carbonAssets/addQuotaAssets")
	ApiResult<ChainMakerRtVO> addQuotaAssets(@Valid @RequestBody CarbonQuotaAssetsParam param);

	/**
	 * 碳信用资产上链
	 * @param param CarbonCreditAssetsParam
	 * @return Boolean
	 */
	@PostMapping("/chaninmaker/carbonAssets/addCreditAssets")
	ApiResult<ChainMakerRtVO> addCreditAssets(@Valid @RequestBody CarbonCreditAssetsParam param);

	/**
	 * 碳交易履约合同上链
	 * @param param CarbonTradeContractParam
	 * @return Boolean
	 */
	@PostMapping("/chaninmaker/carbonTrade/addContract")
	ApiResult<ChainMakerRtVO> addContract(@Valid @RequestBody CarbonTradeContractParam param);

	/**
	 * 碳减排数据资产上链
	 * @param param
	 * @return
	 */
	@PostMapping("/chaninmaker/carbonAssets/addCarbonOffset")
	@ApiOperation(value = "碳减排资产上链",notes = "碳减排资产上链")
	ApiResult<ChainMakerRtVO> AddCarbonOffset( @RequestBody Map<String,String> param);


	/**
	 * 根据交易Id获取交易详情
	 * @param txId
	 * @return
	 */
	@PostMapping("/chaninmaker/chainQuery/queryTxByTxId")
	@ApiOperation(value = "根据TxId查询交易信息",notes = "根据TxId查询交易信息")
	ApiResult<TransactionRtVO> queryTxByTxId(@RequestParam(value = "txId",required = true) String txId);


	/**
	 * 项目库数据上链
	 */
	@PostMapping("/chaninmaker/addCarbonMetaregistry")
	@ApiOperation(value = "项目库数据区块链上链",notes = "项目库数据区块链上链")
	public ApiResult<ChainMakerRtVO> addCarbonMetaregistry(@RequestBody Map<String,String> map);

	@PostMapping("/chaninmaker/addAssetsCertified")
	@ApiOperation(value = "中和资产核证上链",notes = "中和资产核证上链")
	ApiResult<ChainMakerRtVO> addAssetsCertified(@Valid @RequestBody(required = false) CarbonCertifiedParam param);


	@PostMapping("/chaninmaker/assetsBusiness")
	@ApiOperation(value = "中和资产交易上链",notes = "中和资产交易上链")
	public ApiResult<ChainMakerRtVO> addAssetsBusiness(@Valid @RequestBody(required = false) CarbonBusinessParam param);


	@PostMapping("/chaninmaker/registerAdmin")
	@ApiOperation(value = "注册成为管理员",notes = "注册成为管理员")
	ApiResult<ChainMakerRtVO> registerAdmin(@RequestBody Map<String,String> map);

	@PostMapping("/chaninmaker/registerExchangeAccount")
	@ApiOperation(value = "注册交易员账号",notes = "注册交易员账号")
	ApiResult<ChainMakerRtVO> registerExchangeAccount(@RequestBody Map<String,String> map);

}
