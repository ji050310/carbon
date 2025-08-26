package com.carbon.system.common.converter;
import com.carbon.system.entity.CarbonMethodologyContent;
import com.carbon.system.vo.CarbonArticleQueryVo;
import org.springframework.core.convert.converter.Converter;

public class Methodology2CarbonArticleQueryVo implements Converter<CarbonMethodologyContent, CarbonArticleQueryVo> {


    @Override
    public CarbonArticleQueryVo convert(CarbonMethodologyContent c) {
        CarbonArticleQueryVo n = new CarbonArticleQueryVo();
        n.setId(c.getMethodId());
        n.setTitle(c.getName());
        n.setUrl(c.getSourceFileUrl());
        return n;
    }
}
