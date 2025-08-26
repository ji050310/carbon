package com.carbon.assets.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.carbon.assets.entity.CarbonAssetAssessment;
import com.carbon.assets.entity.CarbonMethodologyContent;
import com.carbon.assets.mapper.CarbonAssetAssessmentMapper;
import com.carbon.assets.mapper.CarbonMetaregistryMapper;
import com.carbon.assets.param.BusinessDataQueryParam;
import com.carbon.assets.param.CarbonAssetAssessmentQueryParam;
import com.carbon.assets.repository.CarbonMetaregistryRepository;
import com.carbon.assets.repository.MethodologyRepository;
import com.carbon.assets.service.*;
import com.carbon.assets.util.CommonUtil;
import com.carbon.assets.vo.CarbonAssetAssessmentQueryVo;
import com.carbon.assets.vo.CarbonDetectionDataVo;
import com.carbon.assets.vo.CarbonMetaregistryDocContentEsVo;
import com.carbon.common.api.Paging;
import com.carbon.common.service.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Code534
 * @since 2022-07-14
 */
@Slf4j
@Service
public class EsImportServiceImpl  implements EsImportService {

    @Autowired
    private CarbonMethodologyContentService carbonMethodologyContentService;

    @Autowired
    private MethodologyRepository methodologyRepository;

    @Autowired
    CarbonMetaregistryRepository carbonMetaregistryRepository;

    @Autowired
    CarbonMetaregistryDocService carbonMetaregistryDocService;

    @Autowired
    CarbonMetaregistryMapper carbonMetaregistryMapper;

    @Override
    public void methodImport() throws Exception{
        String token="fldcnJjoTqAvU15mapuorYkhRbd";
        int sum=0;
        String nt= CommonUtil.getNextToken(token, "");
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

    @Override
    public void metaregistryImport() throws Exception{
        //先全部项目库导入es
        List<CarbonMetaregistryDocContentEsVo> cml=carbonMetaregistryMapper.MetaregistryEsAdd();
        for (CarbonMetaregistryDocContentEsVo vo:cml)
        {
            carbonMetaregistryRepository.save(vo);
        }
        System.out.println("全部项目库导入完成");

        //飞书文件夹的目录token（注意：文件夹下只能有纯doc文档，不能有其他文件，飞书接口目前不能实现自动递归）
        List<String> tokenList=new ArrayList<>();
        tokenList.add("fldcnDl4ukZeV0uL37VmVUgAdMc"); tokenList.add("fldcnt1zy2AFMLdeNgGoUyFHRjf");
        //项目库对应文档入库
        for(int i=0;i<tokenList.size();i++)
        {
            String token=tokenList.get(i);
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
    }
}
