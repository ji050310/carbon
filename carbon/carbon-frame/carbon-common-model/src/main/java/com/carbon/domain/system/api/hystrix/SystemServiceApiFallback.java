package com.carbon.domain.system.api.hystrix;

import cn.hutool.json.JSONObject;
import com.carbon.domain.common.ApiResult;
import com.carbon.domain.mq.entity.AddTradingAccountApproval;
import com.carbon.domain.mq.entity.AssetUploadApproval;
import com.carbon.domain.mq.entity.ProjectApproval;
import com.carbon.domain.mq.entity.QuotaApproval;
import com.carbon.domain.system.api.SystemServiceApi;
import com.carbon.domain.system.param.ChangePasswordParam;
import com.carbon.domain.system.param.SysAccountParam;
import com.carbon.domain.system.vo.SysAccountModelVo;
import com.carbon.domain.system.vo.SysTenantModelVo;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Li Jun
 */
@Component
public class SystemServiceApiFallback implements FallbackFactory<SystemServiceApi> {

	@Override
	public SystemServiceApi create(Throwable arg0) {

		ApiResult result = ApiResult.fail("系统服务不可用");

		return new SystemServiceApi() {
			@Override
			public ApiResult<Boolean> addSysAccount(@Valid SysAccountParam param) {
				return result;
			}

			@Override
			public ApiResult updatePassword(@Valid ChangePasswordParam param) {
				return result;
			}

			@Override
			public ApiResult<List<SysAccountModelVo>> getAccountList() {
				return result;
			}

            @Override
            public ApiResult<List<SysTenantModelVo>> getTenantList() {
                return result;
            }

			@Override
			public ApiResult<Boolean> addAssetsApproval(@Valid AssetUploadApproval approval) {
				return result;
			}

			@Override
			public ApiResult<Boolean> addQuotaApproval(QuotaApproval approval) {
				return result;
			}

			@Override
			public ApiResult<Boolean> addTradeAccountApproval(@Valid AddTradingAccountApproval approval) {
				return result;
			}

			@Override
			public ApiResult<Boolean> addProjectApproval(@Valid ProjectApproval approval) {
				return result;
			}

			@Override
			public ApiResult pushArticle(JSONObject jsonObject) {
				return null;
			}
		};
	}
}
