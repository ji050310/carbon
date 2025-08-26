package com.carbon.assets.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.carbon.assets.entity.CarbonMethodology;
import com.carbon.assets.mapper.CarbonMethodologyMapper;
import com.carbon.assets.service.SynMethodologyContentService;
import com.carbon.assets.util.CommonUtil;
import com.carbon.assets.util.EsUtil;
import com.carbon.assets.vo.CarbonMethodologyQueryVo;
import com.carbon.domain.assets.vo.MethodologyUploadParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class SynMethodologyContentServiceimpl implements SynMethodologyContentService
{
    @Autowired
    private CarbonMethodologyMapper carbonMethodologyMapper;

    @Override
    public void synMethodologyContent(MethodologyUploadParam param)
    {
//        String url=param.getWordUrl();
//        String methodCode= param.getMethodCode();
//        String content = CommonUtil.getContent(CommonUtil.getArticleToken(url));
//
//        System.out.println("接收到的url："+url);
//
//
//        QueryWrapper<CarbonMethodology> wrapper = new QueryWrapper<>();
//        wrapper.eq("method_code",methodCode);
//
//        CarbonMethodologyQueryVo carbonMethodology=carbonMethodologyMapper.getByCode(methodCode);
//

//
//        String json="{\n" +
//                "    \"doc\":{\n" +
//                "        \"content\": \""+content+"\"\n" +
//                "    }\n" +
//                "}";
//        try {
//            EsUtil.UpdateMethod(json,carbonMethodology.getId().intValue());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
    }
}
