package com.carbon.system.service;

import com.carbon.common.api.Paging;
import com.carbon.common.exception.CommonBizException;
import com.carbon.common.service.BaseService;
import com.carbon.system.entity.SysTenant;
import com.carbon.system.param.SysTenantParam;
import com.carbon.system.param.SysTenantQueryParam;
import com.carbon.system.vo.SysTenantQueryVo;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * oss  服务类
 * </p>
 *
 * @author Li Jun
 * @since 2022-04-05
 */
public interface OssService {

    /**
     * 上传单个文件
     * @param file 文件
     * @param fileDirectory 上传到指定目录
     * @return 文件访问 url
     * @throws CommonBizException 业务异常
     */
    String uploadFile(MultipartFile file,String fileDirectory) throws CommonBizException;

    /**
     * 上传多个文件
     * @param files 文件列表
     * @param fileDirectory 上传到指定目录
     * @return 文件访问 url 列表
     * @throws CommonBizException 业务异常
     */
    List<String> uploadFiles(MultipartFile[] files,String fileDirectory) throws CommonBizException;
}
