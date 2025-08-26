package com.carbon.assets.controller;

import com.carbon.assets.common.BaseController;
import com.carbon.assets.entity.CarbonMethodologyContent;
import com.carbon.assets.entity.CarbonProjectContent;
import com.carbon.assets.param.CarbonMethodologyQueryParam;
import com.carbon.assets.param.CarbonProjectContentQueryParam;
import com.carbon.assets.repository.CarbonProjectRepository;
import com.carbon.assets.repository.MethodologyRepository;
import com.carbon.assets.service.CarbonMethodologyService;
import com.carbon.domain.common.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 碳减排方法学 前端控制器
 * </p>
 *
 * @author cbd
 * @since 2021-07-31
 */
@Slf4j
@RestController
@RequestMapping("/EscarbonProject")
@Api(value = "碳减排项目模块es搜索", tags = {"碳减排方法学模块"})
public class CarbonProjectEsController extends BaseController {

    @Autowired
    private CarbonProjectRepository carbonProjectRepository;


    @PostMapping("/getByKeyword")
    @ApiOperation(value = "方法学全文检索列表",notes = "方法学全文检索列表")
    public ApiResult<Map> findByKeyword(@Valid @RequestBody(required = false) CarbonProjectContentQueryParam carbonProjectContentQueryParam)
    {
        String content=carbonProjectContentQueryParam.getSearchKey();
        String name=carbonProjectContentQueryParam.getSearchKey();
        String Scope=carbonProjectContentQueryParam.getSearchKey();
        System.out.println(name);
        Page<CarbonProjectContent> project= carbonProjectRepository.
                findByProjectContentOrProjectNameOrProjectScope(content,name,Scope,PageRequest.of(carbonProjectContentQueryParam.getCurrent()-1,carbonProjectContentQueryParam.getSize()));
        List projectList=new ArrayList();
        for (CarbonProjectContent carbonProjectContent :project.getContent())
        {
            carbonProjectContent.setProjectContent("");
            projectList.add(carbonProjectContent);
        }
        Map res=new HashMap();
        res.put("current",carbonProjectContentQueryParam.getCurrent());
        res.put("total",project.getTotalElements());
        res.put("data",projectList);
        return ApiResult.ok(res);
    }

}

