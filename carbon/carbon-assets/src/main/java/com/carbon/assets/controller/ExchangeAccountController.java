package com.carbon.assets.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.carbon.assets.param.ExchangeAccountBindingParam;
import com.carbon.assets.service.CarbonExchangeService;
import com.carbon.assets.service.ExchangeAccountService;
import com.carbon.assets.param.ExchangeAccountQueryParam;
import com.carbon.assets.vo.ExchangeAccountQueryVo;
import com.carbon.assets.entity.ExchangeAccount;
import com.carbon.assets.common.BaseController;
import com.carbon.common.api.Paging;
import com.carbon.common.feishu.FeiShuAPI;
import com.carbon.domain.common.ApiResult;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;



/**
 * <p>
 * 交易账户  前端控制器
 * </p>
 *
 * @author Li Jun
 * @since 2021-08-16
 */
@Slf4j
@RestController
@RequestMapping("/exchangeAccount")
@Api(value = "交易账户 模块", tags = {"交易账户 模块"})
public class ExchangeAccountController extends BaseController {

    @Autowired
    private ExchangeAccountService exchangeAccountService;

    /**
     * 添加交易账户
     */
    @PostMapping("/add")
    @ApiOperation(value = "添加交易账户",notes = "添加交易账户 ")
    public ApiResult<Boolean> addExchangeAccount(@Valid @RequestBody ExchangeAccount exchangeAccount) {
        exchangeAccountService.addExchangeAccount(exchangeAccount);
        return ApiResult.ok();
    }

    /**
     * 修改交易账户
     */
    @PutMapping("/update")
    @ApiOperation(value = "修改交易账户",notes = "修改交易账户 ")
    public ApiResult<Boolean> updateExchangeAccount(@Valid @RequestBody ExchangeAccount exchangeAccount) {
        boolean flag = exchangeAccountService.updateById(exchangeAccount);
        return ApiResult.result(flag);
    }

    /**
     * 删除交易账户
     */
    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "删除交易账户",notes = "删除交易账户 ")
    public ApiResult<Boolean> deleteExchangeAccount(@PathVariable String id) {
        boolean flag = exchangeAccountService.removeById(id);
        return ApiResult.result(flag);
    }


    @PutMapping("/binding")
    @ApiOperation(value = "交易账户-绑定",notes = "绑定交易账户 ")
    public ApiResult<Boolean> binding(@Valid @RequestBody ExchangeAccountBindingParam param) {
        exchangeAccountService.binding(param);
        return ApiResult.ok();
    }

    @PutMapping("/unbind/{id}")
    @ApiOperation(value = "交易账户-解绑",notes = "解绑交易账户 ")
    public ApiResult<Boolean> unbind(@PathVariable Long id) {
        exchangeAccountService.unbind(id);
        return ApiResult.ok();
    }

    /**
     * 获取交易账户
     */
    @GetMapping("/info/{id}")
    @ApiOperation(value = "查看交易账户",notes = "查看交易账户 ")
    public ApiResult<ExchangeAccountQueryVo> getExchangeAccount(@PathVariable String id) {
        ExchangeAccountQueryVo exchangeAccountQueryVo = exchangeAccountService.getExchangeAccountById(id);
        return ApiResult.ok(exchangeAccountQueryVo);
    }

    /**
     * 交易账户 分页列表
     */
    @PostMapping("/getPageList")
    @ApiOperation(value = "（带关键词）获取交易账户分页列表",notes = "（带关键词）交易账户分页列表")
    public ApiResult<Paging<ExchangeAccountQueryVo>> getExchangeAccountPageList(@RequestBody ExchangeAccountQueryParam exchangeAccountQueryParam) {
        Paging<ExchangeAccountQueryVo> paging = exchangeAccountService.getExchangeAccountPageList(exchangeAccountQueryParam);
        return ApiResult.ok(paging);
    }

    /**
     * 上传项目管理的文档
     */
    @PostMapping("/upload")
    @ApiOperation(value = "上传项目管理的文档",notes = "上传项目管理的文档")
    public ApiResult upLoadCarbonDoc(@RequestParam("file") MultipartFile file, @RequestParam("fileName") String fileName) {
        return FeiShuAPI.uploadFile(file, fileName);
    }

    @PostMapping("/uploadCredential")
    @ApiOperation(value = "飞书查看用户凭证", notes = "飞书查看用户凭证")
    public ApiResult uploadCredential(@RequestParam("file") MultipartFile file) {
        // 检查上传文件是否为空
        if (file == null || file.isEmpty()) {
            log.info("上传文件不能为空");
        }

        String originalFilename = file.getOriginalFilename();

        // 检查文件名是否为空
        if (originalFilename == null || originalFilename.isEmpty()) {
            log.info("文件名不能为空");
        }

        ApiResult apiResult = FeiShuAPI.uploadFile(file, originalFilename);

        // 检查API调用结果是否为空
        if (apiResult == null) {
            log.info("文件上传失败");
        }

        // 检查API返回的数据是否为空
        if (apiResult.getData() == null) {
            log.info("文件上传返回数据为空");
        }
        System.out.println(apiResult);
        JsonObject jsonObject = (JsonObject) new JsonParser().parse(apiResult.getData().toString());

        String url = "https://carbonmsger.feishu.cn/file/" + jsonObject.get("file_token").getAsString();

        return ApiResult.ok(url);
    }
}

