package com.carbon.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.carbon.domain.common.ApiResult;
import com.carbon.system.entity.CrmCarbonCreditDetail;
import com.carbon.system.entity.CrmExchangeRecord;
import com.carbon.system.mapper.CrmExchangeRecordMapper;
import com.carbon.system.service.CrmExchangeRecordService;
import com.carbon.system.param.CrmExchangeRecordQueryParam;
import com.carbon.system.vo.CrmCarbonCreditDetailVo;
import com.carbon.system.vo.CrmExchangeRecordVo;
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
 * 兑换记录 服务实现类
 * </p>
 *
 * @author Zhang Jinrui
 * @since 2022-05-02
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class CrmExchangeRecordServiceImpl extends BaseServiceImpl<CrmExchangeRecordMapper, CrmExchangeRecord> implements CrmExchangeRecordService {

    @Resource
    private CrmExchangeRecordMapper crmExchangeRecordMapper;

    @Override
    public ApiResult getCrmExchangeRecordByUserId(String id) {
        QueryWrapper<CrmExchangeRecord> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_id",id);
        //查询指定用户的兑换记录明细
        List<CrmExchangeRecord> crmExchangeRecords = crmExchangeRecordMapper.selectList(queryWrapper);
        List<CrmExchangeRecordVo> res = crmExchangeRecords.stream().map(record->{
            CrmExchangeRecordVo vo=new CrmExchangeRecordVo();
            BeanUtil.copyProperties(record,vo);
            return vo;
        }).collect(Collectors.toList());
        return ApiResult.ok(res);
    }

    @Override
    public ApiResult getCrmExchangeRecordByDate(DateQueryDTO date) {
        QueryWrapper<CrmExchangeRecord> queryWrapper=new QueryWrapper();
        queryWrapper.eq("user_id",date.getUserId());
        queryWrapper.between("exchange_time",date.getStartDate(),date.getEndDate());
        List<CrmExchangeRecord> list = crmExchangeRecordMapper.selectList(queryWrapper);
        List<CrmExchangeRecordVo> res=list.stream().map(record -> {
            CrmExchangeRecordVo vo=new CrmExchangeRecordVo();
            BeanUtil.copyProperties(record,vo);
            return vo;
        }).collect(Collectors.toList());
        return ApiResult.ok(res);
    }

//    @Override
//    public Paging<CrmExchangeRecordVo> getCrmExchangeRecordPageList(CrmExchangeRecordQueryParam param) {
//        IPage<CrmExchangeRecordVo> iPage = crmExchangeRecordMapper.getCrmExchangeRecordPageList(getPage(param),param);
//        return new Paging<>(iPage);
//    }

}
