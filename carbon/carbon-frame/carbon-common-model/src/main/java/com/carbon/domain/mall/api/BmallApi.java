package com.carbon.domain.mall.api;

import com.carbon.domain.common.ApiResult;
import com.carbon.domain.mall.param.WechatPayParam;
import com.carbon.domain.mall.vo.JsapiResultVo;
import com.carbon.domain.system.vo.UmsMemberSetmealQueryVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * 系统服务Api
 * @author cbd
 */
@FeignClient(value = "carbon-bmall",contextId = "b2")
public interface BmallApi {

	@PostMapping("/bmall/api/pay/wechat/createOrder/v3")
	public JsapiResultVo createOrderV3(@RequestBody WechatPayParam param) throws Exception;
}
