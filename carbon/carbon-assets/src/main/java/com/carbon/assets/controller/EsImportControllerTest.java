package com.carbon.assets.controller;

import com.carbon.assets.common.BaseController;
import com.carbon.assets.entity.CarbonMethodologyContent;
import com.carbon.assets.entity.CarbonProjectContent;
import com.carbon.assets.mapper.CarbonMetaregistryMapper;
import com.carbon.assets.repository.CarbonMetaregistryRepository;
import com.carbon.assets.repository.MethodologyRepository;
import com.carbon.assets.service.*;
import com.carbon.assets.util.CommonUtil;
import com.carbon.assets.vo.CarbonMetaregistryDocContentEsVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;


/**
 *
 * @author Cbd
 * @since 2022-08-09
 */
@Slf4j
@RestController
@RequestMapping("/es")
public class EsImportControllerTest extends BaseController {

    @Autowired
    EsImportService esImportService;

    //方法学入库
    @PostMapping("/addMethod")
    public void importMethod() throws Exception {
        esImportService.methodImport();
    }

    //项目库入库
    @PostMapping("/addMetaregistry")
    public void change2() throws Exception {
        esImportService.metaregistryImport();
    }

    @PostMapping("/addAll")
    public void importAll() throws Exception
    {
        log.info("---------------------开始导入方法学------------------");
        esImportService.methodImport();
        log.info("---------------------开始导入项目库------------------");
        esImportService.metaregistryImport();
        log.info("---------------------全部数据导入完成------------------");
    }

}