package com.carbon.assets.controller;

import cn.hutool.json.JSONUtil;
import com.carbon.assets.common.BaseController;
import com.carbon.assets.entity.CarbonMethodology;
import com.carbon.assets.entity.CarbonMethodologyContent;
import com.carbon.assets.entity.EsCarbonMethodology;
import com.carbon.assets.param.CarbonMethodologyQueryParam;
import com.carbon.assets.repository.MethodologyRepository;
import com.carbon.assets.service.CarbonMethodologyService;
import com.carbon.assets.vo.CarbonMethodologyQueryVo;
import com.carbon.assets.vo.CarbonMethodologySelectVo;
import com.carbon.common.api.Paging;
import com.carbon.domain.common.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 碳减排方法学es搜索控制器
 * </p>
 *
 * @author cbd
 * @since 2021-07-31
 */
@Slf4j
@RestController
@RequestMapping("/EscarbonMethodology")
@Api(value = "碳减排方法学模块es搜索", tags = {"碳减排方法学ES模块"})
public class CarbonMethodologyEsController extends BaseController {

    @Autowired
    private MethodologyRepository methodologyRepository;
    @PostMapping("/getByKeyword")
    @ApiOperation(value = "方法学全文检索列表",notes = "方法学全文检索列表")
    public ApiResult<Map> findByKeyword(@Valid @RequestBody(required = false) CarbonMethodologyQueryParam carbonMethodologyQueryParam)
    {
        if(carbonMethodologyQueryParam.getSearchKey()!=""||carbonMethodologyQueryParam.getSearchKey()!=null)
        {
            String content=carbonMethodologyQueryParam.getSearchKey();
            String name=carbonMethodologyQueryParam.getSearchKey();
            String industryCodeName=carbonMethodologyQueryParam.getSearchKey();
            String dictCode=carbonMethodologyQueryParam.getSearchKey();
            Page<CarbonMethodologyContent> methodologies= methodologyRepository.
                    findByNameOrContentOrIndustryCodeNameOrDictCode(content,name,industryCodeName,dictCode,PageRequest.of(0,10000));
            List<CarbonMethodologyContent> methodologyList = methodologies.toList();
            return ApiResult.ok(getSearchResMap(methodologies,carbonMethodologyQueryParam));
        }else {
            Page<CarbonMethodologyContent> methodologies= methodologyRepository.
                    findAll(PageRequest.of(carbonMethodologyQueryParam.getCurrent()-1,carbonMethodologyQueryParam.getSize()));
            List<CarbonMethodologyContent> methodologyList = methodologies.toList();
            return ApiResult.ok(getSearchResMap(methodologies,carbonMethodologyQueryParam));
        }
    }

    @PostMapping("/getByKeyword2")
    @ApiOperation(value = "方法学全文检索列表",notes = "方法学全文检索列表")
    public ApiResult<Map> findByKeyword2(@Valid @RequestBody(required = false) CarbonMethodologyQueryParam carbonMethodologyQueryParam)
    {
        if(carbonMethodologyQueryParam.getSearchKey()!=""||carbonMethodologyQueryParam.getSearchKey()!=null)
        {
            String content=carbonMethodologyQueryParam.getSearchKey();
            String name=carbonMethodologyQueryParam.getSearchKey();
            String industryCodeName=carbonMethodologyQueryParam.getSearchKey();
            String dictCode=carbonMethodologyQueryParam.getSearchKey();
            Page<CarbonMethodologyContent> methodologies= methodologyRepository.
                    findByNameOrContentOrIndustryCodeNameOrDictCode(content,name,industryCodeName,dictCode,PageRequest.of(0,10000));
            List<CarbonMethodologyContent> methodologyList = methodologies.toList();
            return ApiResult.ok(getSearchResMap(methodologies,carbonMethodologyQueryParam));
        }else {
            Page<CarbonMethodologyContent> methodologies= methodologyRepository.
                    findAll(PageRequest.of(carbonMethodologyQueryParam.getCurrent()-1,carbonMethodologyQueryParam.getSize()));
            List<CarbonMethodologyContent> methodologyList = methodologies.toList();
            return ApiResult.ok(getSearchResMap(methodologies,carbonMethodologyQueryParam));
        }
    }

    Map getSearchResMap(Page<CarbonMethodologyContent> methodologies,CarbonMethodologyQueryParam carbonMethodologyQueryParam)
    {
        Long total=methodologies.getTotalElements();
        List methodList=new ArrayList();
        String certificationCriteria;
        certificationCriteria=carbonMethodologyQueryParam.getCertificationCriteria()==null?
                null:"00"+carbonMethodologyQueryParam.getCertificationCriteria().toString();
        String fieldCode=carbonMethodologyQueryParam.getFieldCode();
        String industryCode=carbonMethodologyQueryParam.getIndustryCode();
        String statusCode=carbonMethodologyQueryParam.getStatusCode();
        if (certificationCriteria!=null||fieldCode!=null||industryCode!=null||statusCode!=null)
        {
            for (CarbonMethodologyContent carbonMethodologyContent :methodologies.getContent())
            {
                if(Match(certificationCriteria,fieldCode,industryCode,statusCode,carbonMethodologyContent)==1)
                {
                    carbonMethodologyContent.setContent("");
                    methodList.add(carbonMethodologyContent);
                }
                else {
                    total-=1;
                }
            }
        }
        else {
            for (CarbonMethodologyContent carbonMethodologyContent :methodologies.getContent())
            {
                carbonMethodologyContent.setContent("");
                methodList.add(carbonMethodologyContent);
            }
        }
        List resList=new ArrayList();
        int startNum=(carbonMethodologyQueryParam.getCurrent()*carbonMethodologyQueryParam.getSize()-carbonMethodologyQueryParam.getSize());
        int endNum=startNum+carbonMethodologyQueryParam.getSize().intValue()<total
                ?startNum+carbonMethodologyQueryParam.getSize().intValue():total.intValue();
        for (int i=startNum;i<endNum;i++)
        {
            resList.add(methodList.get(i));
        }
        Map res=new HashMap();
        res.put("current",carbonMethodologyQueryParam.getCurrent());
        res.put("total",total);
        res.put("data",resList);
        return res;
    }

    public int Match(String certificationCriteria,String fieldCode,String industryCode,String statusCode,CarbonMethodologyContent carbonMethodologyContent)
    {
        if(certificationCriteria!=null&&!carbonMethodologyContent.getCertificationCriteria().equals(certificationCriteria))
        {
            return 0;
        }
        if(fieldCode!=null&&!carbonMethodologyContent.getFieldCode().equals(fieldCode))
        {
            return 0;
        }
        if(industryCode!=null&&!carbonMethodologyContent.getIndustryCode().equals(industryCode))
        {
            return 0;

        }
        if(statusCode!=null&&!carbonMethodologyContent.getStatusCode().equals(statusCode))
        {
            return 0;
        }
        return 1;
    }
}

