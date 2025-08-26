package com.carbon.system.service.impl;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.carbon.common.enums.ExpCodeEnum;
import com.carbon.common.exception.CommonBizException;
import com.carbon.domain.system.vo.SysDictModelVo;
import com.carbon.system.entity.SysDict;
import com.carbon.system.entity.SysDictItem;
import com.carbon.system.mapper.SysDictItemMapper;
import com.carbon.system.mapper.SysDictMapper;
import com.carbon.system.param.SysDictAddParam;
import com.carbon.system.param.SysDictItemAddParam;
import com.carbon.system.param.SysDictItemQueryParam;
import com.carbon.system.service.SysDictItemService;
import com.carbon.system.service.SysDictService;
import com.carbon.system.param.SysDictQueryParam;
import com.carbon.system.vo.SysDictItemQueryVo;
import com.carbon.system.vo.SysDictQueryVo;
import com.carbon.common.service.BaseServiceImpl;
import com.carbon.common.api.Paging;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.*;
import java.util.stream.Collectors;


/**
 * <p>
 * 系统字典  服务实现类
 * </p>
 *
 * @author Li Jun
 * @since 2021-08-16
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class SysDictServiceImpl extends BaseServiceImpl<SysDictMapper, SysDict> implements SysDictService {
    @Resource
    private SysDictMapper sysDictMapper;
    @Autowired
    private SysDictItemService sysDictItemService;
    @Autowired
    private SysDictItemMapper sysDictItemMapper;


    @Override
    public void addSysDict(SysDictAddParam param) {
        if (baseMapper.selectOne(new QueryWrapper<SysDict>()
                .eq("dict_code",param.getDictCode())
                .eq("del_flag",0))!=null){
            throw new CommonBizException("该字典已存在，请检查字典编码");
        }else if(baseMapper.selectOne(new QueryWrapper<SysDict>()
                .eq("dict_code",param.getDictCode())
                .eq("del_flag",1)) !=null){
            SysDict dict=(baseMapper.selectOne(new QueryWrapper<SysDict>()
                    .eq("dict_code",param.getDictCode())
                    .eq("del_flag",1)));
            dict.setUpdatedId(getCurrentAccountId());
            dict.setCreatorId(getCurrentAccountId());
            dict.setUpdatedTime(new Date());
            dict.setCreatedTime(new Date());
            dict.setDelFlag(0);
            BeanUtil.copyProperties(param,dict,"id");
            log.info("--- dict:{}",dict);
            updateById(dict);
        }else {
            SysDict dict=new SysDict();
            BeanUtil.copyProperties(param,dict);
            save(dict);
        }
    }

    @Override
    public boolean updateByCode(SysDictAddParam param) {
        SysDict dict=baseMapper.selectById(param.getId());
        if (!dict.getDictCode().equals(param.getDictCode()) && baseMapper.selectOne(new QueryWrapper<SysDict>().eq("dict_code",param.getDictCode()))!=null){
            throw new CommonBizException("该字典已存在，请检查字典编码");
        }else if(baseMapper.selectOne(new QueryWrapper<SysDict>()
                .eq("dict_code",param.getDictCode())
                .eq("del_flag",1)) !=null){
            SysDict isDeleted = baseMapper.selectOne(new QueryWrapper<SysDict>().eq("dict_code", param.getDictCode())
                    .eq("del_flag", 1));
            removeById(isDeleted.getId());
        }
        BeanUtil.copyProperties(param,dict);
        updateById(dict);
        return true;
    }

    @Override
    public void deleteSysDict(Long id) {
        SysDict sysDict = this.getById(id);
        if (sysDict == null){
            throw new CommonBizException("该字典不存在");
        }
        sysDict.setDelFlag(1);
        if (!updateById(sysDict)){
            throw new CommonBizException(ExpCodeEnum.OPERATE_FAIL_ERROR);
        }
    }

    @Override
    public Paging<SysDictQueryVo> getSysDictPageList(SysDictQueryParam param) {
        Page<?> page = getPage(param);
        page.addOrder(OrderItem.desc("sd.updated_time"));
        IPage<SysDictQueryVo> iPage = sysDictMapper.getSysDictPageList(page,param);
        return new Paging<>(iPage);
    }

    @Override
    public List<SysDictModelVo> getNameMapByCode(String dictCode) {
        ArrayList<SysDictModelVo> modelVos = new ArrayList<>();
        List<SysDictItem> dictItems = sysDictItemService.lambdaQuery().eq(SysDictItem::getDictCode,dictCode).list();
        dictItems.forEach(item -> {
            SysDictModelVo modelVo = new SysDictModelVo();
            modelVo.setDictCode(dictCode);
            modelVo.setName(item.getItemCh());
            modelVo.setValue(item.getItemValue());
            modelVos.add(modelVo);
        });

        return modelVos;
    }

    @Override
    public List<SysDictModelVo> getAllDict() {
        return sysDictMapper.getAllDict();
    }

    @Override
    public Paging<SysDictItemQueryVo> getSysDictItemPageList(SysDictItemQueryParam param) {
        //分页带条件查询字典项
        Page page=new Page();
        page.setCurrent(param.getCurrent());
        page.setSize(param.getSize());
        QueryWrapper<SysDictItem> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("dict_code",param.getDictCode());
        if(StrUtil.isNotBlank(param.getItemValue())){
            queryWrapper.like("item_value",param.getItemValue());
        }
        if(StrUtil.isNotBlank(param.getItemCh())){
            queryWrapper.like("item_ch",param.getItemCh());
        }
        if(param.getStatus()!=null){
            queryWrapper.eq("status",param.getStatus());
        }
        Page selectPage = sysDictItemMapper.selectPage(page, queryWrapper);
        List<SysDictItem> records = selectPage.getRecords();
        List<SysDictItemQueryVo> newRecords = records.stream().map(e -> {
            SysDictItemQueryVo vo = new SysDictItemQueryVo();
            BeanUtil.copyProperties(e, vo);
            return vo;
        }).collect(Collectors.toList());
        selectPage.setRecords(newRecords);
        return new Paging<>(selectPage);
    }

    @Override
    public boolean addSysDictItem(SysDictItemAddParam param) {
        //item_value 唯一性检查
        if(sysDictItemMapper.selectOne(new QueryWrapper<SysDictItem>().eq("item_value",param.getItemValue()))!= null){
            throw new CommonBizException("该字典项编码已存在，请更换编码！");
        }
        SysDictItem dictItem=new SysDictItem();
        BeanUtil.copyProperties(param,dictItem);
        int result = sysDictItemMapper.insert(dictItem);
        return result != 0;
    }

    @Override
    public boolean updateSysDictItemById(SysDictItemAddParam param) {
        Long id = param.getId();
        if(id == null){
            throw new CommonBizException("该字典项不存在");
        }
        SysDictItem dictItem = sysDictItemMapper.selectById(id);
        if(!dictItem.getItemValue().equals(param.getItemValue()) &&
                sysDictItemMapper.selectOne(new QueryWrapper<SysDictItem>()
                        .eq("item_value",param.getItemValue()))!= null){
            throw new CommonBizException("该字典项编码已存在，请更换编码！");
        }
        BeanUtil.copyProperties(param,dictItem);
        int result = sysDictItemMapper.updateById(dictItem);
        return result != 0;
    }

    @Override
    public boolean removeSysDictItemById(Long id) {
        SysDictItem sysDictItem = sysDictItemService.getById(id);
        if(sysDictItem == null){
            throw new CommonBizException("该字典项不存在");
        }
        //字典项没有逻辑删除，故直接删除
        int result = sysDictItemMapper.deleteById(id);
        return result != 0;
    }

    @Override
    public Map<String, Map<String, String>> getCityDict() {
        List<SysDictModelVo> dictList = getNameMapByCode("012");
        HashMap<String, Map<String, String>> vo = new HashMap<>();
        for (SysDictModelVo dict : dictList) {
            String provinceCode = dict.getValue().substring(6,8);
            String cityCode = dict.getValue().substring(6,10);
            Map<String, String> cityMap = vo.get(provinceCode);
            if (cityMap == null){
                cityMap = new HashMap<>();
                cityMap.put(cityCode,dict.getName());
                vo.put(provinceCode,cityMap);
            }else {
                cityMap.put(cityCode,dict.getName());
            }
        }
        return vo;
    }

    @Override
    public Map<String, List<String>> getFieldDict() {

        List<SysDictItem> Dict = sysDictMapper.getFieldDict();
        List<SysDictItem> field = new ArrayList<>();
        List<SysDictItem> type = new ArrayList<>();
        Map<String, List<String>> allDict = new HashMap<>();
        for (SysDictItem sysDictItem : Dict) {
            if ("003".equals(sysDictItem.getDictCode())) {
                field.add(sysDictItem);
            } else {
                type.add(sysDictItem);
            }
        }

        int i = 0;
        for (SysDictItem sysDictItem : field) {
            if (i == 0) {
                i++;
                continue;
            }
            List<String> tem = new ArrayList<>();
            for (SysDictItem dictItem : type) {
                if (dictItem.getItemValue().substring(4, 5).equals(sysDictItem.getItemValue().substring(4, 5))) {
                    tem.add(dictItem.getItemCh());
                }
            }
            allDict.put(sysDictItem.getItemCh(), tem);
        }

        return allDict;
    }


}
