package com.carbon.system.controller;

import com.carbon.domain.common.ApiResult;
import com.carbon.system.common.constant.OssConstant;
import com.carbon.system.mapper.CarbonProjectMapper;
import com.carbon.system.service.CarbonProjectService;
import com.carbon.system.service.OssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * OSS Controller.
 * https://github.com/alibaba/aliyun-spring-boot/tree/master/aliyun-spring-boot-samples/aliyun-oss-spring-boot-sample
 * @author Li Jun
 */
@RestController
@RequestMapping("/oss")
@Api(value = "Oss文件管理", tags = {"Oss文件管理"})
public class OssController {

    @Autowired
    private OssService ossService;

    @Autowired
    private CarbonProjectService carbonProjectService;

    @PostMapping("/uploadImages")
    @ApiOperation(value = "上传图片", notes = "上传图片")
    public ApiResult<List<String>> uploadImages(@RequestParam("file") MultipartFile[] file) {
        return ApiResult.ok(ossService.uploadFiles(file,OssConstant.OSS_FILE_DIR_PUBLIC_IMAGE));
    }


    @PostMapping("/uploadFiles")
    @ApiOperation(value = "上传项目附件", notes = "上传项目附件")
    public ApiResult<String> uploadFiles(@RequestParam("file") MultipartFile file) {
        return ApiResult.ok(ossService.uploadFile(file,OssConstant.OSS_FILE_DIR_PUBLIC_PROJECT_ANNEX));
    }
}
