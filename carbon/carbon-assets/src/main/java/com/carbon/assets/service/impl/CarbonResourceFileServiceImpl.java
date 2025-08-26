package com.carbon.assets.service.impl;

import com.carbon.assets.entity.CarbonResourceFile;
import com.carbon.assets.mapper.CarbonResourceFileMapper;
import com.carbon.assets.service.CarbonResourceFileService;
import com.carbon.common.service.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;



/**
 * <p>
 * 碳资源文件 服务实现类
 * </p>
 *
 * @author Li Jun
 * @since 2022-05-06
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class CarbonResourceFileServiceImpl extends BaseServiceImpl<CarbonResourceFileMapper, CarbonResourceFile> implements CarbonResourceFileService {
    @Resource
    private CarbonResourceFileMapper carbonResourceFileMapper;

}
