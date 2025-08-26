package com.carbon.system.controller;

import com.carbon.domain.system.vo.SysDictModelVo;
import com.carbon.system.entity.SysDictItem;
import com.carbon.system.param.SysDictAddParam;
import com.carbon.system.param.SysDictItemAddParam;
import com.carbon.system.param.SysDictItemQueryParam;
import com.carbon.system.service.SysDictService;
import com.carbon.system.param.SysDictQueryParam;
import com.carbon.system.vo.SysDictItemQueryVo;
import com.carbon.system.vo.SysDictQueryVo;
import com.carbon.system.entity.SysDict;
import com.carbon.system.common.BaseController;
import com.carbon.domain.common.ApiResult;
import com.carbon.common.api.Paging;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 系统字典  前端控制器
 * </p>
 *
 * @author Li Jun
 * @since 2021-08-16
 */
@Slf4j
@RestController
@RequestMapping("/sysDict")
@Api(value = "系统字典 模块", tags = {"系统字典 模块"})
public class SysDictController extends BaseController {
    @Autowired
    private SysDictService sysDictService;

    /**
     * 添加系统字典
     */
    @PostMapping("/add")
    @ApiOperation(value = "添加字典",notes = "添加系统字典")
    public ApiResult<Boolean> addSysDict(@Valid @RequestBody SysDictAddParam sysDictAddParam){
        sysDictService.addSysDict(sysDictAddParam);
        return ApiResult.ok();
    }

    /**
     * 修改系统字典
     */
    @PutMapping("/update")
    @ApiOperation(value = "修改字典",notes = "修改系统字典")
    public ApiResult<Boolean> updateSysDict(@Valid @RequestBody SysDict sysDict){
        boolean flag = sysDictService.updateById(sysDict);
        return ApiResult.result(flag);
    }

    /**
     * 删除系统字典
     */
    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "删除字典对象",notes = "删除系统字典")
    public ApiResult<Boolean> deleteSysDict(@PathVariable Long id){
        sysDictService.deleteSysDict(id);
        return ApiResult.ok();
    }

    /**
     * 获取字典名称集合
     */
    @GetMapping("getNameMapByCode/{dictCode}")
    @ApiOperation(value = "获取字典名称集合",notes = "获取字典名称集合")
    public ApiResult<List<SysDictModelVo>> getNameMapByCode(@PathVariable String dictCode){
        List<SysDictModelVo> list = sysDictService.getNameMapByCode(dictCode);
        return ApiResult.ok(list);
    }

    /**
     * 获取所有字典集合
     */
    @GetMapping("getAllDict")
    @ApiOperation(value = "获取所有字典集合",notes = "获取所有字典集合")
    public ApiResult<List<SysDictModelVo>> getAllDict(){
        List<SysDictModelVo> list = sysDictService.getAllDict();
        return ApiResult.ok(list);
    }

    /**
     * 系统字典 分页列表
     */
    @PostMapping("/getPageList")
    @ApiOperation(value = "获取系统字典分页列表",notes = "系统字典 分页列表")
    public ApiResult<Paging<SysDictQueryVo>> getSysDictPageList(@Valid @RequestBody(required = false) SysDictQueryParam sysDictQueryParam) {
        Paging<SysDictQueryVo> paging = sysDictService.getSysDictPageList(sysDictQueryParam);
        return ApiResult.ok(paging);
    }


    /**
     * 通过dictCode获取系统字典配置的分页列表（子集字典）
     */
    @PostMapping("/dictConfig/getPageList")
    @ApiOperation(value = "通过dictCode获取系统字典配置的分页列表（子集字典）",notes = "字典配置 分页列表")
    public ApiResult<Paging<SysDictItemQueryVo>> getDictConfigPageList(@Valid @RequestBody(required = false) SysDictItemQueryParam sysDictItemQueryParam) {
        Paging<SysDictItemQueryVo> paging = sysDictService.getSysDictItemPageList(sysDictItemQueryParam);
        return ApiResult.ok(paging);
    }


    /**
     * 添加系统字典条目
     */
    @PostMapping("/dictConfig/add")
    @ApiOperation(value = "添加系统字典条目（子集字典）",notes = "添加系统字典条目")
    public ApiResult<Boolean> addSysDictItem(@Valid @RequestBody SysDictItemAddParam param) {
        boolean flag = sysDictService.addSysDictItem(param);
        return ApiResult.result(flag);
    }

    /**
     * 修改系统字典条目
     */
    @PutMapping("/dictConfig/update")
    @ApiOperation(value = "修改系统字典条目（子集字典）",notes = "修改系统字典条目")
    public ApiResult<Boolean> updateSysDictItem(@Valid @RequestBody SysDictItemAddParam param) {
        boolean flag = sysDictService.updateSysDictItemById(param);
        return ApiResult.result(flag);
    }

    /**
     * 删除系统字典条目
     */
    @DeleteMapping("/dictConfig/delete/{id}")
    @ApiOperation(value = "删除系统字典条目（子集字典）",notes = "删除系统字典条目")
    public ApiResult<Boolean> deleteSysDictItem(@PathVariable Long id) {
        boolean flag = sysDictService.removeSysDictItemById(id);
        return ApiResult.result(flag);
    }

    @GetMapping("/getCityDict")
    @ApiOperation(value = "获取城市字典",notes = "获取城市字典 ")
    public ApiResult<Map<String,Map<String,String>>> getCityDict(){
        return ApiResult.ok(sysDictService.getCityDict());
    }

    @GetMapping("/getFieldDict")
    @ApiOperation(value = "获取领域字典", notes = "获取领域字典")
    public ApiResult<Map<String, List<String>>> getFieldDict() {

        return ApiResult.ok(sysDictService.getFieldDict());
    }


}

