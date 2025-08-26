package com.carbon.assets.service.impl;

import cn.hutool.extra.ssh.JschUtil;
import cn.hutool.json.JSONUtil;
import com.carbon.assets.entity.CarbonMetaregistry;
import com.carbon.assets.mapper.CarbonMetaregistryMapper;
import com.carbon.assets.service.CarbonMetaregistryService;
import com.carbon.assets.param.CarbonMetaregistryQueryParam;
import com.carbon.assets.vo.CarbonMetaregistryQueryVo;
import com.carbon.common.service.BaseServiceImpl;
import com.carbon.common.api.Paging;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * <p>
 * 项目仓库 服务实现类
 * </p>
 *
 * @author ChengJX
 * @since 2022-08-09
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class CarbonMetaregistryServiceImpl extends BaseServiceImpl<CarbonMetaregistryMapper, CarbonMetaregistry> implements CarbonMetaregistryService {

    @Resource
    private CarbonMetaregistryMapper carbonMetaregistryMapper;

    @Override
    public CarbonMetaregistryQueryVo getCarbonMetaregistryById(Serializable id) {
        return carbonMetaregistryMapper.getCarbonMetaregistryById(id);
    }

    @Override
    public Paging<CarbonMetaregistryQueryVo> getCarbonMetaregistryPageList(CarbonMetaregistryQueryParam param) {
        IPage<CarbonMetaregistryQueryVo> iPage = carbonMetaregistryMapper.getCarbonMetaregistryPageList(getPage(param),param);
        log.info("{}", JSONUtil.toJsonStr(iPage));
        return new Paging<>(iPage);
    }

}
