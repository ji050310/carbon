package com.carbon.assets.controller;

import cn.hutool.json.JSONUtil;
import com.carbon.assets.common.BaseController;
import com.carbon.assets.entity.CarbonMetaregistryDoc;
import com.carbon.assets.entity.CarbonMethodologyContent;
import com.carbon.assets.mapper.CarbonMetaregistryDocContentMapper;
import com.carbon.assets.mapper.CarbonMetaregistryMapper;
import com.carbon.assets.param.CarbonMetaregistryDocQueryParam;
import com.carbon.assets.repository.CarbonMetaregistryRepository;
import com.carbon.assets.repository.CarbonMetaregistryRepositoryImpl;
import com.carbon.assets.repository.MethodologyRepository;
import com.carbon.assets.service.CarbonMetaregistryDocContentService;
import com.carbon.assets.service.CarbonMetaregistryDocService;
import com.carbon.assets.service.CarbonMetaregistryService;
import com.carbon.assets.util.CommonUtil;
import com.carbon.assets.util.EsUtil;
import com.carbon.assets.vo.CarbonMetaregistryDocContentEsVo;
import com.carbon.assets.vo.CarbonMetaregistryDocQueryVo;
import com.carbon.assets.vo.CarbonMetaregistryQueryVo;
import com.carbon.common.api.Paging;
import com.carbon.domain.common.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ChengJX
 * @since 2022-08-08
 */
@Slf4j
@RestController
@RequestMapping("/EscarbonMetaregistry")
@Api(value = "项目文档管理Es模块", tags = {"项目文档管理Es模块"})
public class CarbonMetaregistryEsController extends BaseController {

    @Autowired
    private CarbonMetaregistryDocContentService carbonMetaregistryDocContentService;

    @Autowired
    private MethodologyRepository methodologyRepository;

    @Autowired
    private CarbonMetaregistryMapper carbonMetaregistryMapper;
    @Autowired
    private RestHighLevelClient restHighLevelClient;

    EsUtil esUtil=new EsUtil();

    @GetMapping("/getByRef")
    ApiResult<CarbonMetaregistryQueryVo> getMetaregistryContentByRefId(String refId, String typeCode)
    {
        List<CarbonMetaregistryQueryVo> vo =  carbonMetaregistryMapper.getMetaregistryContentByRefId(refId);
        if(vo.size()>0){
            return ApiResult.ok(vo.get(0));
        }
 //       CarbonMetaregistryDocContentEsVo contentEsVo=carbonMetaregistryDocContentService.getMetaregistryContentByRefId(refId,typeCode);
//            contentEsVo.setContent("");
        return ApiResult.ok();
    }

    @PostMapping("/getByKeyword")
    @ApiOperation(value = "项目文档全文检索列表",notes = "项目文档全文检索列表")
    public ApiResult<Map> findByKeyword3(@Valid @RequestBody(required = false) CarbonMetaregistryDocQueryParam param) throws Exception {
//        try {
//            CommonUtil.setEsSize();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        String mSearchKey=param.getMethodSearchKey();
        List<String> l=new ArrayList<>();
        if(mSearchKey!=null)
        {
            Page<CarbonMethodologyContent> methodologies=methodologyRepository
                    .findByNameOrContentOrIndustryCodeNameOrDictCode(mSearchKey,mSearchKey,mSearchKey,mSearchKey, PageRequest.of(0,10000));
            List<CarbonMethodologyContent> methodologyList = methodologies.toList();
            for(CarbonMethodologyContent content:methodologyList)
            {
                l.add(content.getDictCode());
            }
            param.setMethodCodeList(l);
            System.out.println(l);
        }
        Map res= esUtil.matchRes3(restHighLevelClient,param);
        return ApiResult.ok(res);
    }
}

