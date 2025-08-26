package com.carbon.domain.mall.api;

import cn.hutool.json.JSONObject;
import com.carbon.domain.common.ApiResult;
import com.carbon.domain.mq.entity.AddTradingAccountApproval;
import com.carbon.domain.mq.entity.AssetUploadApproval;
import com.carbon.domain.mq.entity.ProjectApproval;
import com.carbon.domain.system.api.hystrix.SystemServiceApiFallback;
import com.carbon.domain.system.param.ChangePasswordParam;
import com.carbon.domain.system.param.SysAccountParam;
import com.carbon.domain.system.vo.SysAccountModelVo;
import com.carbon.domain.system.vo.SysTenantModelVo;
import com.carbon.domain.system.vo.UmsMemberSetmealQueryVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


/**
 * 系统服务Api
 * @author Li Jun
 */
@FeignClient(value = "carbon-cmall")
public interface CmallApi {

	@PostMapping("/cmall/umsMemberSetmeal/info")
	ApiResult<UmsMemberSetmealQueryVo> getMemberSetmeal(@RequestParam("id") int id);

	@PostMapping("/cmall/umsMember/openVip")
	ApiResult openVip(@RequestParam("setMealId") int setMealId) throws Exception;
}
