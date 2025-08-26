package com.carbon.auth.controller;


import com.carbon.auth.service.LoginService;
import com.carbon.common.redis.RedisService;
import com.carbon.common.utils.HttpContextUtils;
import com.carbon.common.utils.WxJwtUtils;
import com.carbon.domain.auth.api.LoginCheckApi;
import com.carbon.domain.auth.vo.SecurityData;
import com.carbon.domain.common.ApiCode;
import com.carbon.domain.common.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



/**
 * @author Li jun
 */
@Slf4j
@RestController
@RequestMapping("check")
@Api(value = "权限校验", tags = {"权限校验 模块"})
public class CheckController implements LoginCheckApi {


	@Autowired
	private LoginService loginService;

	@Autowired
	private RedisService redisService;

	@Override
	@PostMapping("/checkPermission")
	@ApiOperation(value = "验证权限")
	public ApiResult<Boolean> checkPermission(@RequestParam("checkUrl")String checkUrl) {
		Long accountId = HttpContextUtils.getAccountId();
		if (!loginService.isLogin(accountId)){
			return ApiResult.fail(ApiCode.UNAUTHORIZED);
		}
		return loginService.checkPermission(checkUrl);
	}

	@Override
	@GetMapping("/getSecurityData")
	@ApiOperation(value = "获取认证数据")
	public ApiResult<SecurityData> getSecurityData() {
		//获取认证信息
		SecurityData securityData = loginService.getSecurityData(HttpContextUtils.getAccountId());
		return ApiResult.ok(securityData);
	}

}
