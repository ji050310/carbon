package com.carbon.system.service.impl;

import com.carbon.system.entity.SysDictItem;
import com.carbon.system.mapper.SysDictItemMapper;
import com.carbon.system.service.SysDictItemService;
import com.carbon.system.param.SysDictItemQueryParam;
import com.carbon.system.vo.SysDictItemQueryVo;
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
 * 系统字典条目 服务实现类
 * </p>
 *
 * @author Jiang zhenhua
 * @since 2022-05-11
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class SysDictItemServiceImpl extends BaseServiceImpl<SysDictItemMapper, SysDictItem> implements SysDictItemService {

    @Resource
    private SysDictItemMapper sysDictItemMapper;

    @Override
    public SysDictItemQueryVo getSysDictItemById(Serializable id) {
        return sysDictItemMapper.getSysDictItemById(id);
    }

    @Override
    public Paging<SysDictItemQueryVo> getSysDictItemPageList(SysDictItemQueryParam param) {
        IPage<SysDictItemQueryVo> iPage = sysDictItemMapper.getSysDictItemPageList(getPage(param),param);
        return new Paging<>(iPage);
    }

}
