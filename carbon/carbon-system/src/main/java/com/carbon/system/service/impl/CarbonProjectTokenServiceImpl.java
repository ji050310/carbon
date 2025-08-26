package com.carbon.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.carbon.common.feishu.FeiShuAPI;
import com.carbon.system.controller.FeishuAPI;
import com.carbon.system.entity.CarbonProjectToken;
import com.carbon.system.mapper.CarbonProjectTokenMapper;
import com.carbon.system.service.CarbonProjectTokenService;
import com.carbon.system.param.CarbonProjectTokenQueryParam;
import com.carbon.system.vo.CarbonProjectTokenQueryVo;
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
 * @author Bae
 * @since 2022-06-27
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class CarbonProjectTokenServiceImpl extends BaseServiceImpl<CarbonProjectTokenMapper, CarbonProjectToken> implements CarbonProjectTokenService {

    @Resource
    private CarbonProjectTokenMapper tokenMapper;

    @Override
    public CarbonProjectTokenQueryVo getCarbonProjectTokenById(Serializable id) {
        return tokenMapper.getCarbonProjectTokenById(id);
    }

    @Override
    public Paging<CarbonProjectTokenQueryVo> getCarbonProjectTokenPageList(CarbonProjectTokenQueryParam param) {
        IPage<CarbonProjectTokenQueryVo> iPage = tokenMapper.getCarbonProjectTokenPageList(getPage(param),param);
        return new Paging<>(iPage);
    }

    @Override
    public Long getProjectFileCode(String projectId,String projectName) {
        QueryWrapper<CarbonProjectToken> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("project_id",projectId);
        CarbonProjectToken token = tokenMapper.selectOne(queryWrapper);
        if(token==null){
            String fileToken = FeiShuAPI.addProjectFile(projectId, projectName);
            token=new CarbonProjectToken();
            token.setFileToken(fileToken);
            token.setProjectId(projectId);
            tokenMapper.insert(token);
            token = tokenMapper.selectOne(queryWrapper);
        }
        Long code =token.getTextCode();
        return code;
    }

}
