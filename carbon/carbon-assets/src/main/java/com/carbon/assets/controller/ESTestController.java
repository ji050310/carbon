//package com.carbon.assets.controller;
//
//import com.carbon.assets.entity.EsCarbonMethodology;
//import com.carbon.assets.repository.MethodologyRepository;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//@Slf4j
//@RestController
//public class ESTestController
//{
//    @Autowired
//    private MethodologyRepository methodologyRepository;
//
//    @PostMapping("/save")
//    public void testSave(@RequestBody EsCarbonMethodology esCarbonMethodology)
//    {
//        System.out.println(esCarbonMethodology);
//        methodologyRepository.save(esCarbonMethodology);
//    }
//
//    @GetMapping("/getById")
//    public void findById(String id)
//    {
//        Page<EsCarbonMethodology> methodologies= methodologyRepository.findById(id, PageRequest.of(0,10));
//        for (EsCarbonMethodology esCarbonMethodology :methodologies.getContent())
//        {
//            System.out.println(esCarbonMethodology);
//        }
//    }
//
//    @GetMapping("/getByName")
//    public void findByName(String name)
//    {
//        Page<EsCarbonMethodology> methodologies= methodologyRepository.findByName(name, PageRequest.of(0,10));
//        for (EsCarbonMethodology esCarbonMethodology :methodologies.getContent())
//        {
//            System.out.println(esCarbonMethodology);
//        }
//    }
//
//    @GetMapping("/getByContent")
//    public void findByKeyword(String keyword)
//    {
//        Page<EsCarbonMethodology> methodologies= methodologyRepository.findByContentOrName(keyword,keyword, PageRequest.of(0,10));
//        for (EsCarbonMethodology esCarbonMethodology :methodologies.getContent())
//        {
//            System.out.println(esCarbonMethodology);
//        }
//    }
//
//    @PostMapping("/update")
//    public void update(EsCarbonMethodology esCarbonMethodology)
//    {
//        Page<EsCarbonMethodology> methodologies= methodologyRepository.findById(esCarbonMethodology.getId(), PageRequest.of(0,10));
//        EsCarbonMethodology newcarbonMethodologyEs =methodologies.getContent().get(0);
//        methodologyRepository.save(newcarbonMethodologyEs);
//    }
//
//    @PostMapping("/delete")
//    public void delete(String id)
//    {
//        Page<EsCarbonMethodology> methodologies= methodologyRepository.findById(id, PageRequest.of(0,10));
//        EsCarbonMethodology esCarbonMethodology =methodologies.getContent().get(0);
//        methodologyRepository.delete(esCarbonMethodology);
//    }
//}
