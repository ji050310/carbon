package com.carbon.system.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.UUID;
import com.aliyun.oss.OSS;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.ObjectMetadata;
import com.carbon.common.exception.CommonBizException;
import com.carbon.domain.common.ApiResult;
import com.carbon.system.service.OssService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
public class OssServiceImpl implements OssService {

    /**
     * bucket
     */
    public static final String BUCKET_NAME = "xcarbon";

    /**
     * 图片文件夹
     */
    public static final String IMAGE_DIR = "public/image";

    /**
     * 文件访问地址
     */
    private final String FILE_HOST = "https://xcarbon.oss-cn-beijing.aliyuncs.com/";

    @Autowired
    private OSS ossClient;

    @Override
    public String uploadFile(MultipartFile file, String fileDirectory) throws CommonBizException {
        List<String> pathList = uploadFiles(new MultipartFile[]{file}, fileDirectory);
        return CollUtil.getFirst(pathList);
    }

    @Override
    public List<String> uploadFiles(MultipartFile[] files, String fileDirectory) throws CommonBizException {
        if (files.length == 0) {
            throw new CommonBizException("请选择文件!");
        }
        List<String> pathList = new ArrayList<>();
        try {
            for (MultipartFile multipartFile : files) {
                String suffix = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
                String fileName = fileDirectory + "/" + UUID.randomUUID() + "." + suffix;

                //保存图片原名称、设置图片公共可读权限
                ObjectMetadata meta = new ObjectMetadata();
                meta.setObjectAcl(CannedAccessControlList.PublicRead);
                meta.setContentDisposition("inline;filename*=utf-8'zh_cn'" + multipartFile.getOriginalFilename());
                meta.setContentType("application/octet-stream");

                ossClient.putObject(BUCKET_NAME, fileName, multipartFile.getInputStream(), meta);
                pathList.add(FILE_HOST + fileName);
            }
        } catch (IOException e) {
            throw new CommonBizException("上传图片失败!");
        }
        if (CollUtil.isEmpty(pathList)) {
            throw new CommonBizException("上传图片失败!");
        }
        return pathList;
    }
}
