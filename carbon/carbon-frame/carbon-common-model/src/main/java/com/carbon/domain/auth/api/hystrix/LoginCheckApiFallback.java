package com.carbon.domain.auth.api.hystrix;

import com.carbon.domain.auth.api.LoginCheckApi;
import com.carbon.domain.auth.vo.SecurityData;
import com.carbon.domain.common.ApiResult;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author Li Jun
 */
@Component
public class LoginCheckApiFallback implements FallbackFactory<LoginCheckApi> {

	@Override
	public LoginCheckApi create(Throwable arg0) {

		ApiResult result = ApiResult.fail("授权服务不可用");

		return new LoginCheckApi() {
			@Override
			public ApiResult<Boolean> checkPermission(String checkUrl) {
				return result;
			}

			@Override
			public ApiResult<SecurityData> getSecurityData() {
				return result;
			}
		};
	}
}
