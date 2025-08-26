package com.carbon.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.carbon.domain.common.ApiResult;
import com.carbon.system.entity.CrmCarbonCreditDetail;
import com.carbon.system.mapper.CrmCarbonCreditDetailMapper;
import com.carbon.system.service.CrmCarbonCreditDetailService;
import com.carbon.system.vo.CrmCarbonCreditDetailVo;
import com.carbon.common.service.BaseServiceImpl;
import com.carbon.system.vo.DateQueryDTO;
import com.carbon.system.vo.TenantUserPageVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;


/**
 * <p>
 * 碳信分详情 服务实现类
 * </p>
 *
 * @author Zhang Jinrui
 * @since 2022-05-02
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class CrmCarbonCreditDetailServiceImpl extends BaseServiceImpl<CrmCarbonCreditDetailMapper, CrmCarbonCreditDetail> implements CrmCarbonCreditDetailService {

    @Resource
    private CrmCarbonCreditDetailMapper crmCarbonCreditDetailMapper;

    @Override
    public ApiResult getCrmCarbonCreditDetailByUserId(String id) {
        QueryWrapper<CrmCarbonCreditDetail> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_id",id);
        //查询指定用户的碳信分明细
        List<CrmCarbonCreditDetail> crmCarbonCreditDetails = crmCarbonCreditDetailMapper.selectList(queryWrapper);
        List<CrmCarbonCreditDetailVo> res = crmCarbonCreditDetails.stream().map( detail->{
            CrmCarbonCreditDetailVo vo=new CrmCarbonCreditDetailVo();
            BeanUtil.copyProperties(detail,vo);
            return vo;
        }).collect(Collectors.toList());
        return ApiResult.ok(res);
    }

    @Override
    public ApiResult getCrmCarbonCreditDetailByDate(DateQueryDTO date) {
        QueryWrapper<CrmCarbonCreditDetail> queryWrapper=new QueryWrapper();
        queryWrapper.eq("user_id",date.getUserId());
        queryWrapper.between("clear_time",date.getStartDate(),date.getEndDate());
        List<CrmCarbonCreditDetail> list = crmCarbonCreditDetailMapper.selectList(queryWrapper);
        List<CrmCarbonCreditDetailVo> res=list.stream().map(detail -> {
            CrmCarbonCreditDetailVo vo=new CrmCarbonCreditDetailVo();
            BeanUtil.copyProperties(detail,vo);
            return vo;
        }).collect(Collectors.toList());

        return ApiResult.ok(res);
    }

//    @Override
//    public Paging<CrmCarbonCreditDetailQueryVo> getCrmCarbonCreditDetailPageList(CrmCarbonCreditDetailQueryParam param) {
//        IPage<CrmCarbonCreditDetailQueryVo> iPage = crmCarbonCreditDetailMapper.getCrmCarbonCreditDetailPageList(getPage(param),param);
//        return new Paging<>(iPage);
//    }

}
