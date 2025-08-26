package com.carbon.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.carbon.domain.system.vo.SysTenantModelVo;
import com.carbon.system.entity.SysTenant;
import com.carbon.system.mapper.SysTenantMapper;
import com.carbon.system.service.SysTenantService;
import com.carbon.system.param.SysTenantQueryParam;
import com.carbon.system.vo.SysTenantQueryVo;
import com.carbon.common.service.BaseServiceImpl;
import com.carbon.common.api.Paging;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;


/**
 * <p>
 * 租户  服务实现类
 * </p>
 *
 * @author ChengJX
 * @since 2022-08-24
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class SysTenantServiceImpl extends BaseServiceImpl<SysTenantMapper, SysTenant> implements SysTenantService {

    @Resource
    private SysTenantMapper sysTenantMapper;

    @Override
    public SysTenantQueryVo getSysTenantById(Serializable id) {
        SysTenantQueryVo vo = sysTenantMapper.getSysTenantById(id);
        if(vo==null)
        {
            return new SysTenantQueryVo();
        }
        vo.setValidityDayNum(vo==null?100:vo.getValidityDayNum(vo.getValidityTime()));
        return vo;
    }

    @Override
    public Paging<SysTenantQueryVo> getSysTenantPageList(SysTenantQueryParam param) {
        IPage<SysTenantQueryVo> iPage = sysTenantMapper.getSysTenantPageList(getPage(param),param);
        iPage.getRecords().forEach(vo->vo.setValidityDayNum(vo.getValidityDayNum(vo.getValidityTime())));
        return new Paging<>(iPage);
    }

    @Override
    public List<SysTenantModelVo> getSysTenantList() {
        return sysTenantMapper.getSysTenantList();
    }

}
