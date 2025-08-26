package com.carbon.domain.system.api;

import cn.hutool.json.JSONObject;
import com.carbon.domain.common.ApiResult;
import com.carbon.domain.mq.entity.AddTradingAccountApproval;
import com.carbon.domain.mq.entity.AssetUploadApproval;
import com.carbon.domain.mq.entity.ProjectApproval;
import com.carbon.domain.mq.entity.QuotaApproval;
import com.carbon.domain.system.api.hystrix.SystemServiceApiFallback;
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
@FeignClient(value = "carbon-system", fallbackFactory = SystemServiceApiFallback.class)
public interface SystemServiceApi {

	/**
	 * 添加账号
	 * @param param SysAccountParam
	 * @return Boolean
	 */
	@PostMapping("/system/sysAccount/add")
	ApiResult<Boolean> addSysAccount(@Valid @RequestBody SysAccountParam param);

	/**
	 * 修改密码
	 * @param param SysAccountParam
	 */
	@PostMapping("/system/sysAccount/update/password")
	ApiResult updatePassword(@Valid @RequestBody ChangePasswordParam param);

	/**
	 * 获取账户列表
	 * @return ApiResult<List<SysAccountModelVo>>
	 */
	@GetMapping("/system/sysAccount/list")
	ApiResult<List<SysAccountModelVo>> getAccountList();


	/**
	 * 获取租户列表
	 */
	@GetMapping("/system/sysTenant/list")
	ApiResult<List<SysTenantModelVo>> getTenantList();


	/**
	 * 添加资产上传审批
	 * @param approval AssetUploadApproval
	 * @return ApiResult<Boolean>
	 */
	@PostMapping("/system/approval/addAssetsApproval")
	ApiResult<Boolean> addAssetsApproval(@Valid @RequestBody AssetUploadApproval approval);

	@PostMapping("/system/approval/addQuotaApproval")
	ApiResult<Boolean> addQuotaApproval(@Valid @RequestBody QuotaApproval approval);

	/**
	 * 添加交易账户审批
	 * @param approval AddTradingAccountApproval
	 * @return ApiResult<Boolean>
	 */
	@PostMapping("/system/approval/addTradeAccountApproval")
	ApiResult<Boolean> addTradeAccountApproval(@Valid @RequestBody AddTradingAccountApproval approval);

	/**
	 * 添加项目立项审批
	 * @param approval ProjectApproval
	 * @return ApiResult<Boolean>
	 */
	@PostMapping("/system/approval/addProjectApproval")
	ApiResult<Boolean> addProjectApproval(@Valid @RequestBody ProjectApproval approval);



	/**
	 * 添加碳文章
	 */
	@PostMapping("/system/carbonArticle/push")
	@ApiOperation(value = "添加碳文章",notes = "添加碳文章")
	ApiResult pushArticle(@RequestBody JSONObject jsonObject);
}
