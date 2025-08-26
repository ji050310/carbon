//package com.carbon.system.controller;
//
//import com.carbon.domain.common.ApiResult;
//import com.carbon.domain.mq.entity.AddTradingAccountApproval;
//import com.carbon.domain.mq.entity.AssetUploadApproval;
//import com.carbon.domain.mq.entity.ProjectApproval;
//import com.carbon.system.common.BaseController;
//import com.carbon.system.service.CarbonApprovalService;
//import com.carbon.system.service.common.TaskApi;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.validation.Valid;
//
//
///**
// * <p>
// * 审批
// * </p>
// *
// * @author Li Jun
// * @since 2021-08-01
// */
//@Slf4j
//@RestController
//@RequestMapping("/weTaskFissionReward")
//@Api(value = "小程序活动调用", tags = {"小程序活动调用"})
//public class CmallTaskController extends BaseController {
//
//    @Autowired
//    private TaskApi taskApi;
//
//    @PostMapping("/activeComplete")
//    @ApiOperation(value = "小程序活动完成")
//    public ApiResult inviteFriend(int taskId) {
//        taskApi.inviteFriend(taskId);
//        return ApiResult.ok();
//    }
//
//}
//
