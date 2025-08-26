package com.carbon.domain.assets.api.hystrix;

import com.carbon.domain.assets.api.AssetsServiceApi;
import com.carbon.domain.assets.vo.CarbonExchangeQueryVo;
import com.carbon.domain.assets.vo.MethodologyUploadParam;
import com.carbon.domain.common.ApiResult;

import com.carbon.domain.mq.entity.CarbonProjectAddParam;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;


/**
 * @author Li Jun
 */
@Component
public class AssetsServiceApiFallback implements FallbackFactory<AssetsServiceApi> {

	@Override
	public AssetsServiceApi create(Throwable arg0) {

		ApiResult result = ApiResult.fail("系统服务不可用");

		return new AssetsServiceApi() {

			@Override
			public ApiResult<CarbonExchangeQueryVo> getExchangeInfoById(Long id) {
				return result;
			}

			@Override
			public ApiResult<CarbonExchangeQueryVo> getExchangeInfoByDict(String dictCode) {
				return result;
			}

			@Override
			public ApiResult addFeishuProject(CarbonProjectAddParam param) {
				return result;
			}

			@Override
			public ApiResult uploadContent(MethodologyUploadParam param) {
				return null;
			}


		};
	}
}
