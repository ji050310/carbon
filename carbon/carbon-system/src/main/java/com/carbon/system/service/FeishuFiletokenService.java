package com.carbon.system.service;

import com.carbon.system.entity.FeishuFiletoken;
import com.carbon.common.service.BaseService;

import java.util.List;
import java.util.Map;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Jiang zhenhua
 * @since 2022-06-16
 */
public interface FeishuFiletokenService extends BaseService<FeishuFiletoken> {


    void updatedTime(String templateNum);

    FeishuFiletoken getOne(String templateName);

    List<Map<String,Object>> getDocToken();

}
