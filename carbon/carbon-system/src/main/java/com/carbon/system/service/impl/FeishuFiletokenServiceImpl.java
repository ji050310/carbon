package com.carbon.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.carbon.system.entity.FeishuFiletoken;
import com.carbon.system.mapper.FeishuFiletokenMapper;
import com.carbon.system.service.FeishuFiletokenService;
import com.carbon.common.service.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Jiang zhenhua
 * @since 2022-06-16
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class FeishuFiletokenServiceImpl extends BaseServiceImpl<FeishuFiletokenMapper, FeishuFiletoken> implements FeishuFiletokenService {

    @Resource
    private FeishuFiletokenMapper feishuFiletokenMapper;

    @Override
    public void updatedTime(String templateNum) {
        QueryWrapper<FeishuFiletoken> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("code",templateNum);
        FeishuFiletoken one = feishuFiletokenMapper.selectOne(queryWrapper);
        one.setUpdatedTime(new Date());
        feishuFiletokenMapper.update(one,queryWrapper);
    }

    @Override
    public FeishuFiletoken getOne(String templateName) {
        QueryWrapper<FeishuFiletoken> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("code",templateName);
        FeishuFiletoken one = feishuFiletokenMapper.selectOne(queryWrapper);
        return one;
    }

    @Override
    public List<Map<String,Object>> getDocToken() {

        return feishuFiletokenMapper.getDocToken();
    }

}
