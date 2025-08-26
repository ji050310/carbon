package com.carbon.mq.service.impl;

import com.carbon.mq.entity.CarbonProjectInstance;
import com.carbon.mq.mapper.CarbonProjectInstanceMapper;
import com.carbon.mq.service.CarbonProjectInstanceService;
import com.carbon.mq.param.CarbonProjectInstanceQueryParam;
import com.carbon.mq.vo.CarbonProjectInstanceQueryVo;
import com.carbon.common.service.BaseServiceImpl;
import com.carbon.common.api.Paging;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.io.Serializable;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author JinRui Zhang
 * @since 2022-06-21
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class CarbonProjectInstanceServiceImpl extends BaseServiceImpl<CarbonProjectInstanceMapper, CarbonProjectInstance> implements CarbonProjectInstanceService {

    @Resource
    private CarbonProjectInstanceMapper carbonProjectInstanceMapper;

    @Override
    public CarbonProjectInstanceQueryVo getCarbonProjectInstanceById(Serializable id) {
        return carbonProjectInstanceMapper.getCarbonProjectInstanceById(id);
    }

    @Override
    public Paging<CarbonProjectInstanceQueryVo> getCarbonProjectInstancePageList(CarbonProjectInstanceQueryParam param) {
        IPage<CarbonProjectInstanceQueryVo> iPage = carbonProjectInstanceMapper.getCarbonProjectInstancePageList(getPage(param),param);
        return new Paging<>(iPage);
    }

}
