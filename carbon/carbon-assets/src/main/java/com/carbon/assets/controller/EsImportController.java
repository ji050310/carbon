package com.carbon.assets.controller;

import com.carbon.assets.common.BaseController;
import com.carbon.assets.entity.*;
import com.carbon.assets.mapper.CarbonMetaregistryMapper;
import com.carbon.assets.repository.CarbonMetaregistryRepository;
import com.carbon.assets.repository.MethodologyRepository;
import com.carbon.assets.service.*;
import com.carbon.assets.util.CommonUtil;
import com.carbon.assets.vo.CarbonMetaregistryDocContentEsVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 碳减排方法学文档 前端控制器
 * </p>
 *
 * @author Cbd
 * @since 2022-08-09
 */
@Slf4j
@RestController
@RequestMapping("/change")
public class EsImportController extends BaseController {

    @Autowired
    private CarbonMethodologyContentService carbonMethodologyContentService;

    @Autowired
    private CarbonProjectService carbonProjectService;

    @Autowired
    private CarbonMetaregistryDocContentService carbonMetaregistryDocContentService;

    @Autowired
    private MethodologyRepository methodologyRepository;

    @Autowired
    CarbonMetaregistryRepository carbonMetaregistryRepository;

    @Autowired
    CarbonMetaregistryDocService carbonMetaregistryDocService;

    @Autowired
    CarbonMetaregistryMapper carbonMetaregistryMapper;

    @PostMapping("/add")
    public void change(String token) throws Exception {
        int sum=0;
        String nt=CommonUtil.getNextToken(token, "");
        boolean first=true;
        while (true)
        {
            if(first) {
                nt="";
            }
            List<Map<String,String>> l= CommonUtil.geturl(token,nt);
            System.out.println(nt);
            sum+=l.size();
            for(Map<String,String> mapList : l){
                String content=CommonUtil.getContent(CommonUtil.getArticleToken(mapList.get("url")));
                String url=mapList.get("url");
                String name=CommonUtil.getTitle(CommonUtil.getArticleToken(url));
                CarbonMethodologyContent carbonMethodologyContent=
                        carbonMethodologyContentService.getCarbonMethodologyById(name);
                System.out.println("name:--------------"+name);
                if(null!=carbonMethodologyContent)
                {
                    carbonMethodologyContent.setContent(content);
                    //存入mysql
//                    carbonMethodologyContent.setSourceFileUrl(carbonMethodologyContent.getSourceFileUrl());
//                    carbonMethodologyContent.setMethodId(carbonMethodologyContent.getId());
//                    carbonMethodologyContentService.save(carbonMethodologyContent);
                    //存入es
                    methodologyRepository.save(carbonMethodologyContent);
                }
                else {
                    System.out.println("方法学不存在");
                }
            }
            nt=CommonUtil.getNextToken(token, nt);
            if(first) {
                nt=CommonUtil.getNextToken(token, "");
                first=false;
            }
            if(nt.equals(""))
            {
                break;
            }
        }
        System.out.println("sum="+sum);
    }

    @PostMapping("/add2")
    public void change2(String token) throws Exception {
        int sum=0;
        String nt=CommonUtil.getNextToken(token, "");
        SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd");
        boolean first=true;
        while (true)
        {
            if(first) {
                nt="";
            }
            List<Map<String,String>> l= CommonUtil.geturl(token,nt);
            System.out.println(nt);
            sum+=l.size();
            for(Map<String,String> mapList : l){
                String content=CommonUtil.getContent(CommonUtil.getArticleToken(mapList.get("url")));
                String url=mapList.get("url");
                String name=CommonUtil.getTitle(CommonUtil.getArticleToken(url));

                String ref=CommonUtil.getRef(name);
                String type=CommonUtil.getType(name);
                System.out.println("ref:--"+ref+"\n"+"type:--"+type);

                CarbonMetaregistryDocContentEsVo carbonMetaregistryDocContentEsVo=
                        carbonMetaregistryMapper.getContentMsgByref(ref,type);
                if(null!=carbonMetaregistryDocContentEsVo)
                {
                    carbonMetaregistryDocContentEsVo.setContent(content);
                    carbonMetaregistryDocContentEsVo.setUrl(url);
                    if(carbonMetaregistryDocContentEsVo.getRecordFilingDate()!=null&&carbonMetaregistryDocContentEsVo.getRecordFilingDate()!="")
                    {
                        carbonMetaregistryDocContentEsVo.setRecordFilingTime
                                (format.parse(carbonMetaregistryDocContentEsVo.getRecordFilingDate()).getTime());
                    }
                    if(carbonMetaregistryDocContentEsVo.getIssuingDate()!=null&&carbonMetaregistryDocContentEsVo.getIssuingDate()!="")
                    {
                        carbonMetaregistryDocContentEsVo.setIssuingTime(
                                (format.parse(carbonMetaregistryDocContentEsVo.getIssuingDate()).getTime()));
                    }

                    //存入mysql    根据备案号refid和type作为唯一标识
//                    CarbonMetaregistryDocContent carbonMetaregistryDocContent=new CarbonMetaregistryDocContent();
//                    carbonMetaregistryDocContent.setDocId(carbonMetaregistryDocContentEsVo.getId());
//                    carbonMetaregistryDocContent.setProjectRefId(ref);
//                    carbonMetaregistryDocContent.setTypeCode(type);
//                    carbonMetaregistryDocContent.setContent(content);
//                    carbonMetaregistryDocContent.setTitle(carbonMetaregistryDocContentEsVo.getTitle());
//                    carbonMetaregistryDocContent.setUrl(url);
//                    carbonMetaregistryDocContentService.save(carbonMetaregistryDocContent);

                    //存入es
                    carbonMetaregistryRepository.save(carbonMetaregistryDocContentEsVo);
                }
                else {
                    System.out.println("项目不存在");
                }
//                try {
//                    carbonMetaregistryDocService.updateByref(url,ref,type);
//                }catch (Exception e){
//                    System.out.println("项目不存在！");
//                }
            }
            nt=CommonUtil.getNextToken(token, nt);
            if(first) {
                nt=CommonUtil.getNextToken(token, "");
                first=false;
            }
            if(nt.equals(""))
            {
                break;
            }
        }
        System.out.println("sum="+sum);
    }



//    全部项目库存入es
    @PostMapping("/add3")
    public void change3() throws Exception {
        List<CarbonMetaregistryDocContentEsVo> l=carbonMetaregistryMapper.MetaregistryEsAdd();
        for (CarbonMetaregistryDocContentEsVo vo:l)
        {
            carbonMetaregistryRepository.save(vo);
        }
        System.out.println("全部项目库导入完成");
    }

    @PostMapping("/test")
    public void changecccc() throws Exception {
            CarbonMethodologyContent carbonMethodologyContent=
                    carbonMethodologyContentService.getCarbonMethodologyById("CM-049-V01");
            if(null!=carbonMethodologyContent)
            {
                carbonMethodologyContent.setSourceFileUrl("url");
                carbonMethodologyContent.setContent("content");
                carbonMethodologyContent.setMethodId(carbonMethodologyContent.getId());
                carbonMethodologyContentService.save(carbonMethodologyContent);
            }
            else {
                System.out.println("方法学不存在");
            }
        }

//    @GetMapping("/gbn")
//    public CarbonProjectContent getbn(String name)
//    {
//        return carbonProjectService.getCarbonProjectByName(name);
//    }


    @PostMapping("/updateUrl")
    public void updateUrl(String token) throws Exception {
        int sum=0;
        String nt=CommonUtil.getNextToken(token, "");
        boolean first=true;
        while (true)
        {
            if(first) {
                nt="";
            }
            List<Map<String,String>> l= CommonUtil.geturl(token,nt);
            System.out.println(nt);
            sum+=l.size();
            for(Map<String,String> mapList : l){
                String url=mapList.get("url");
                String name=mapList.get("name");

                String ref=CommonUtil.getRef(name);
                String type=CommonUtil.getType(name);
                System.out.println("ref:--"+ref+"\n"+"type:--"+type);

                try {
                    carbonMetaregistryDocService.updateByref(url,ref,type);
                }catch (Exception e){
                    System.out.println("项目不存在！");
                }
            }
            nt=CommonUtil.getNextToken(token, nt);
            if(first) {
                nt=CommonUtil.getNextToken(token, "");
                first=false;
            }
            if(nt.equals(""))
            {
                break;
            }
        }
        System.out.println("sum="+sum);
    }
}

