package com.carbon.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.Header;
import cn.hutool.http.HttpUtil;
import cn.hutool.http.Method;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.carbon.common.api.Paging;
import com.carbon.common.entity.AddResponse;
import com.carbon.common.feishu.FeiShuAPI;
import com.carbon.common.redis.RedisService;
import com.carbon.common.service.BaseServiceImpl;
import com.carbon.domain.common.constant.RedisKeyName;
import com.carbon.system.entity.CarbonArticle;
import com.carbon.system.entity.CarbonProject;
import com.carbon.system.mapper.CarbonArticleMapper;
import com.carbon.system.mapper.CarbonProjectMapper;
import com.carbon.system.param.CarbonArticleQueryParam;
import com.carbon.system.param.CarbonArticleStatuParam;
import com.carbon.system.service.CarbonArticleService;
import com.carbon.system.service.CarbonProjectService;
import com.carbon.system.service.FeishuFiletokenService;
import com.carbon.system.util.CommonUtil;
import com.carbon.system.vo.CarbonArticleAddVo;
import com.carbon.system.vo.CarbonArticleQueryVo;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


/**
 * <p>
 * 碳文章 服务实现类
 * </p>
 *
 * @author Li Jun
 * @since 2021-08-01
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class CarbonProjectServiceImpl extends BaseServiceImpl<CarbonProjectMapper, CarbonProject> implements CarbonProjectService {

    @Autowired
    CarbonProjectMapper carbonProjectMapper;

    @Override
    public void updateInitiationDate(@Param("date") String date, Long id) {
        carbonProjectMapper.updateInitiationDate(date,id);
    }
}
