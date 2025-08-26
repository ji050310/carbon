package com.carbon.domain.auth.api;

import com.carbon.domain.auth.api.hystrix.LoginCheckApiFallback;
import com.carbon.domain.auth.vo.LoginInfoVo;
import com.carbon.domain.auth.vo.SecurityData;
import com.carbon.domain.common.ApiResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;


/**
 * 用户登录验证接口
 * 检验登录状态
 * 获取用户数据
 * 获取权限等接口
 * @author Li Jun
 */
@FeignClient(value = "carbon-auth", fallbackFactory = LoginCheckApiFallback.class)
public interface LoginCheckApi {

	/**
	 * 根据请求url，判断是否有权限
//	 * @param token token
	 * @param checkUrl 需要校验的url
	 * @return ApiResult<Boolean>
	 */
	@PostMapping("/authCenter/check/checkPermission")
	ApiResult<Boolean> checkPermission(@RequestParam("checkUrl")String checkUrl);


	/***
	 * 获取登录信息
	 * @return LoginInfoVo
	 */
	@GetMapping("/authCenter/check/getSecurityData")
	ApiResult<SecurityData> getSecurityData();


}
