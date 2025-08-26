package com.carbon.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.NumberUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.carbon.domain.common.ApiResult;
import com.carbon.system.entity.CrmCarbonCreditDetail;
import com.carbon.system.entity.CrmExchangeRecord;
import com.carbon.system.entity.CrmWithdrawalRecord;
import com.carbon.system.entity.TenantUser;
import com.carbon.system.mapper.CrmCarbonCreditDetailMapper;
import com.carbon.system.mapper.CrmExchangeRecordMapper;
import com.carbon.system.mapper.CrmWithdrawalRecordMapper;
import com.carbon.system.mapper.TenantUserMapper;
import com.carbon.system.service.TenantUserService;
import com.carbon.system.param.TenantUserQueryPageParam;
import com.carbon.system.vo.*;
import com.carbon.common.service.BaseServiceImpl;
import com.carbon.common.api.Paging;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


/**
 * <p>
 * 租户-用户  服务实现类
 * </p>
 *
 * @author Zhang Jinrui
 * @since 2022-05-03
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class TenantUserServiceImpl extends BaseServiceImpl<TenantUserMapper, TenantUser> implements TenantUserService {

    @Resource
    private TenantUserMapper tenantUserMapper;
    @Resource
    private CrmCarbonCreditDetailMapper creditDetailMapper;
    @Resource
    private CrmExchangeRecordMapper exchangeRecordMapper;
    @Autowired
    private CrmWithdrawalRecordMapper withdrawalRecordMapper;



    @Override
    public ApiResult getTenantUserByCondition(String param) {
        QueryWrapper queryWrapper=new QueryWrapper();
        //传入的是用户id
        if(NumberUtil.isInteger(param)){
            queryWrapper.eq("carbon_user_id",param);
        }else {    //传入的是用户昵称
            queryWrapper.like("nick_name",param);
        }
        List<TenantUser> list = baseMapper.selectList(queryWrapper);
        if(list.size()==0){
            return ApiResult.fail("无此用户记录！");
        }
        List<TenantUserPageVo> res= list.stream().map(tenantUser->{
            TenantUserPageVo vo=new TenantUserPageVo();
            BeanUtil.copyProperties(tenantUser,vo);

            QueryWrapper q=new QueryWrapper();
            q.select("SUM(carbon_credit) as carbonCredit,SUM(carbon_reduction) as carbonReduction");
            q.eq("user_id",tenantUser.getCarbonUserId());
            CrmCarbonCreditDetail detail = creditDetailMapper.selectOne(q);
            if(detail!=null){
                //获取碳信分余额
                vo.setCarbonCredit(detail.getCarbonCredit());
                //获得累计碳减排量
                vo.setCarbonReduction(detail.getCarbonReduction());
            }
            return vo;
        }).collect(Collectors.toList());
        return ApiResult.ok(res);
    }


    @Override
    public ApiResult getTenantUserById(String id) {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("carbon_user_id",id);
        TenantUser tenantUser = baseMapper.selectOne(queryWrapper);
        if(tenantUser==null){
            return ApiResult.fail("无此用户记录！");
        }
        TenantUserDetailVo vo=new TenantUserDetailVo();
        BeanUtil.copyProperties(tenantUser,vo);
        queryWrapper=new QueryWrapper();
        queryWrapper.select("SUM(carbon_credit) as carbonCredit,SUM(carbon_reduction) as carbonReduction");
        queryWrapper.eq("user_id",tenantUser.getCarbonUserId());
        CrmCarbonCreditDetail detail = creditDetailMapper.selectOne(queryWrapper);
        if(detail!=null){
            //获取碳信分余额
            vo.setCarbonCredit(detail.getCarbonCredit());
            //获得累计碳减排量
            vo.setTotalCarbonReduction(detail.getCarbonReduction());
        }

        //获取本月第一天和最后一天
        String[] monthDay = getMonthFirstAndLastDay();

        queryWrapper.between("clear_time",monthDay[0],monthDay[1]);
        detail = creditDetailMapper.selectOne(queryWrapper);

        if(detail!=null){
            //获取当月碳信分
            vo.setMonthCarbonCredit(detail.getCarbonCredit());
            //获得当月碳减排量
            vo.setMonthCarbonReduction(detail.getCarbonReduction());
        }


        queryWrapper=new QueryWrapper();
        queryWrapper.select("SUM(exchange_integral) as exchangeIntegral");
        CrmExchangeRecord exchangeRecord = exchangeRecordMapper.selectOne(queryWrapper);

        if(exchangeRecord!=null){
            //获得累计兑换积分
            vo.setTotalExchangeIntegral(exchangeRecord.getExchangeIntegral());
            //获得当月兑换积分
            queryWrapper.between("exchange_time",monthDay[0],monthDay[1]);
        }

        exchangeRecord = exchangeRecordMapper.selectOne(queryWrapper);

        if(exchangeRecord!=null){
            vo.setMonthExchangeIntegral(exchangeRecord.getExchangeIntegral());
        }

        return ApiResult.ok(vo);
    }


    public String[] getMonthFirstAndLastDay(){
        // 获取当月第一天和最后一天
        Calendar cale = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String firstday, lastday;
        // 获取前月的第一天
        cale = Calendar.getInstance();
        cale.add(Calendar.MONTH, 0);
        cale.set(Calendar.DAY_OF_MONTH, 1);
        firstday = format.format(cale.getTime());
        // 获取前月的最后一天
        cale = Calendar.getInstance();
        cale.add(Calendar.MONTH, 1);
        cale.set(Calendar.DAY_OF_MONTH, 0);
        lastday = format.format(cale.getTime());
        return new String[]{firstday,lastday};
    }


    @Override
    public Paging<TenantUserPageVo> getTenantUserPageList(Page page, TenantUserQueryPageParam param) {
        QueryWrapper<TenantUser> queryWrapper=new QueryWrapper();
        if (!"0220000000".equals(param.getSourceChannel())&& !"0".equals(param.getSourceChannel())){
            queryWrapper.eq("source_channel",param.getSourceChannel());
        }
        IPage selectPage = baseMapper.selectPage(page, queryWrapper);
        List<TenantUser> records = selectPage.getRecords();
        List<TenantUserPageVo> res=records.stream().map(tenantUser->{
            TenantUserPageVo vo=new TenantUserPageVo();
            BeanUtil.copyProperties(tenantUser,vo);

            QueryWrapper q=new QueryWrapper();
            q.select("SUM(carbon_credit) as carbonCredit,SUM(carbon_reduction) as carbonReduction");
            q.eq("user_id",tenantUser.getCarbonUserId());
            CrmCarbonCreditDetail detail = creditDetailMapper.selectOne(q);
            if(detail!=null){
                //获取碳信分余额
                vo.setCarbonCredit(detail.getCarbonCredit());
                //获得累计碳减排量
                vo.setCarbonReduction(detail.getCarbonReduction());
            }
            return vo;
        }).collect(Collectors.toList());
        selectPage.setRecords(res);
        return new Paging<>(selectPage);
    }

    @Override
    public ApiResult removeByUserId(String userId) {
        QueryWrapper queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("carbon_user_id",userId);
        tenantUserMapper.delete(queryWrapper);
        return ApiResult.ok("删除成功");
    }

    @Override
    public ApiResult updateUser(TenantUserDTO tenantUser) {
        QueryWrapper<TenantUser> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("carbon_user_id",tenantUser.getCarbonUserId());
        TenantUser user = tenantUserMapper.selectOne(queryWrapper);
        BeanUtil.copyProperties(tenantUser,user);
        tenantUserMapper.update(user,queryWrapper);
        return ApiResult.ok("修改成功！");
    }

    @Override
    public ApiResult getCrmDetailByDate(DateQueryDTO date) {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("user_id",date.getUserId());
        List res=null;

        if("0".equals(date.getType())){
            queryWrapper.between("clear_time",date.getStartDate(),date.getEndDate());
            List<CrmCarbonCreditDetail> details = creditDetailMapper.selectList(queryWrapper);
            res = details.stream().map( detail->{
                CrmCarbonCreditDetailVo vo=new CrmCarbonCreditDetailVo();
                BeanUtil.copyProperties(detail,vo);
                return vo;
            }).collect(Collectors.toList());
        }else if("1".equals(date.getType())){
            queryWrapper.between("exchange_time",date.getStartDate(),date.getEndDate());
            List<CrmExchangeRecord> list = exchangeRecordMapper.selectList(queryWrapper);
            res=list.stream().map(record -> {
                CrmExchangeRecordVo vo=new CrmExchangeRecordVo();
                BeanUtil.copyProperties(record,vo);
                return vo;
            }).collect(Collectors.toList());
        }else if("2".equals(date.getType())){
            queryWrapper.between("exchange_time",date.getStartDate(),date.getEndDate());
            List<CrmWithdrawalRecord> list = withdrawalRecordMapper.selectList(queryWrapper);
            res=list.stream().map(record -> {
                CrmWithdrawalRecordVo vo=new CrmWithdrawalRecordVo();
                BeanUtil.copyProperties(record,vo);
                return vo;
            }).collect(Collectors.toList());
        }
        return ApiResult.ok(res);
    }

}
