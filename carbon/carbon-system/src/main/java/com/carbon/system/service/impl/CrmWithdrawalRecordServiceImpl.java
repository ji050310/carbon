package com.carbon.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.carbon.domain.common.ApiResult;
import com.carbon.system.entity.CrmExchangeRecord;
import com.carbon.system.entity.CrmWithdrawalRecord;
import com.carbon.system.mapper.CrmWithdrawalRecordMapper;
import com.carbon.system.service.CrmWithdrawalRecordService;
import com.carbon.system.param.CrmWithdrawalRecordQueryParam;
import com.carbon.system.vo.CrmExchangeRecordVo;
import com.carbon.system.vo.CrmWithdrawalRecordVo;
import com.carbon.common.service.BaseServiceImpl;
import com.carbon.common.api.Paging;
import com.carbon.system.vo.DateQueryDTO;
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
 * 提现记录 服务实现类
 * </p>
 *
 * @author Zhang Jinrui
 * @since 2022-05-02
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class CrmWithdrawalRecordServiceImpl extends BaseServiceImpl<CrmWithdrawalRecordMapper, CrmWithdrawalRecord> implements CrmWithdrawalRecordService {

    @Resource
    private CrmWithdrawalRecordMapper crmWithdrawalRecordMapper;

    @Override
    public ApiResult getCrmWithdrawalRecordByUserId(String id) {
        QueryWrapper<CrmWithdrawalRecord> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_id",id);
        //查询指定用户的兑换记录明细
        List<CrmWithdrawalRecord> crmExchangeRecords = crmWithdrawalRecordMapper.selectList(queryWrapper);
        List<CrmWithdrawalRecordVo> res = crmExchangeRecords.stream().map(record->{
            CrmWithdrawalRecordVo vo=new CrmWithdrawalRecordVo();
            BeanUtil.copyProperties(record,vo);
            return vo;
        }).collect(Collectors.toList());
        return ApiResult.ok(res);
    }

    @Override
    public ApiResult getCrmWithdrawalRecordByDate(DateQueryDTO date) {
        QueryWrapper<CrmWithdrawalRecord> queryWrapper=new QueryWrapper();
        queryWrapper.eq("user_id",date.getUserId());
        queryWrapper.between("exchange_time",date.getStartDate(),date.getEndDate());
        List<CrmWithdrawalRecord> list = crmWithdrawalRecordMapper.selectList(queryWrapper);
        List<CrmWithdrawalRecordVo> res=list.stream().map(record -> {
            CrmWithdrawalRecordVo vo=new CrmWithdrawalRecordVo();
            BeanUtil.copyProperties(record,vo);
            return vo;
        }).collect(Collectors.toList());
        return ApiResult.ok(res);

    }
//
//    @Override
//    public Paging<CrmWithdrawalRecordVo> getCrmWithdrawalRecordPageList(CrmWithdrawalRecordQueryParam param) {
//        IPage<CrmWithdrawalRecordVo> iPage = crmWithdrawalRecordMapper.getCrmWithdrawalRecordPageList(getPage(param),param);
//        return new Paging<>(iPage);
//    }

}
