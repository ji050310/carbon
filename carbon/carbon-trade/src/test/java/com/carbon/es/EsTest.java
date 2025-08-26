package com.carbon.es;

import com.carbon.trade.repository.MethodologyRepository;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class EsTest
{
    @Resource
    private MethodologyRepository methodologyRepository;

//    @Test
//    public void testSave()
//    {
//        CarbonMethodology carbonMethodology=new CarbonMethodology();
//        carbonMethodology.setId(100L);carbonMethodology.setName("张三");carbonMethodology.setStatusCode("200");
//        methodologyRepository.save(carbonMethodology);
//    }
}
