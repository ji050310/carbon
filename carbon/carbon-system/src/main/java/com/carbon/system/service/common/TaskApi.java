//package com.carbon.system.service.common;
//
//import com.carbon.domain.common.ApiResult;
//import com.carbon.domain.system.api.hystrix.SystemServiceApiFallback;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.*;
//
//import javax.servlet.http.HttpServletRequest;
//
///**
// * cmall 完成活动api
// */
//@FeignClient(value = "carbon-cmall", fallbackFactory = SystemServiceApiFallback.class)
//@RequestMapping("/cmall")
//public interface TaskApi {
//
//    @ApiOperation("完成任务获取碳信分")
//    @PostMapping(value = "/weTaskFissionReward/activeComplete")
//    @ResponseBody
//    public ApiResult inviteFriend(@RequestParam(value = "taskId") int taskId);
//}
