package com.carbon.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.carbon.common.api.Paging;
import com.carbon.common.enums.ApprovalCodeEnum;
import com.carbon.common.feishu.FeiShuAPI;
import com.carbon.common.service.BaseServiceImpl;
import com.carbon.domain.mq.entity.AddTradingAccountApproval;
import com.carbon.domain.mq.entity.AssetUploadApproval;
import com.carbon.domain.mq.entity.ProjectApproval;
import com.carbon.domain.mq.entity.QuotaApproval;
import com.carbon.system.entity.CarbonProject;
import com.carbon.system.entity.CarbonApproval;
import com.carbon.system.entity.SysDictItem;
import com.carbon.system.mapper.CarbonApprovalMapper;
import com.carbon.system.mapper.CarbonProjectMapper;
import com.carbon.system.mapper.SysDictItemMapper;
import com.carbon.system.param.CarbonProjectInstanceQueryParam;
import com.carbon.system.param.FeiShuEventParam1;
import com.carbon.system.service.CarbonApprovalService;
import com.carbon.system.service.CarbonProjectService;
import com.carbon.system.vo.CarbonProjectFormVo;
import com.carbon.system.vo.CarbonApprovalQueryVo;
import com.carbon.system.vo.SysDictItemQueryVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * <p>
 *  服务实现类
 *  碳项目审批实例
 * </p>
 *
 * @author JinRui Zhang
 * @since 2022-06-21
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class CarbonApprovalServiceImpl extends BaseServiceImpl<CarbonApprovalMapper, CarbonApproval> implements CarbonApprovalService {

    @Resource
    private CarbonApprovalMapper carbonApprovalMapper;
    @Resource
    private CarbonProjectMapper projectMapper;
    @Resource
    private CarbonProjectService carbonProjectService;
    @Resource
    private SysDictItemMapper dictItemMapper;
    @Override
    public void addAssetsApproval(AssetUploadApproval approval) {
        String instanceCode = FeiShuAPI.assetUploadApproval(approval);
        if (StrUtil.isNotEmpty(instanceCode)){
            saveCarbonApproval(ApprovalCodeEnum.ASSETS_APPROVAL,instanceCode,approval.getId());
        }

        FeiShuAPI.updateForm("shtcnqwBAfIjn9Vej19jAtAtEtb","455911",approval.getUserName(),approval,"K");
    }

    @Override
    public void addQuotaApproval(QuotaApproval approval) {
        SysDictItemQueryVo sys = dictItemMapper.getSysDictItemByItemValue(approval.getIssuingAgency());
        approval.setIssuingAgency(sys.getItemCh());
        String instanceCode=FeiShuAPI.quotaApproval(approval);
        if (StrUtil.isNotEmpty(instanceCode)){
            saveCarbonApproval(ApprovalCodeEnum.QUOTA_APPROVAL,instanceCode,approval.getId());
        }
    }

    @Override
    public void addTradeAccountApproval(AddTradingAccountApproval approval) {
        String instanceCode = FeiShuAPI.addTradingAccountApproval(approval);
        if (StrUtil.isNotEmpty(instanceCode)){
            saveCarbonApproval(ApprovalCodeEnum.TRADE_ACCOUNT_APPROVAL,instanceCode,approval.getId());
        }

        FeiShuAPI.updateForm("shtcnqwBAfIjn9Vej19jAtAtEtb","tYUZEj",
                approval.getUserName(),approval,"I");
    }

    @Override
    public void addProjectApproval(ProjectApproval approval) {
        log.info("addProjectApproval init: [{}]", JSONUtil.toJsonStr(approval));
        SysDictItemQueryVo getCountry = dictItemMapper.getSysDictItemByItemValue(approval.getCountry());
        SysDictItemQueryVo getProvince = dictItemMapper.getSysDictItemByItemValue(approval.getProvince());
        SysDictItemQueryVo getCity = dictItemMapper.getSysDictItemByItemValue(approval.getCity());
        String getCarbonMethodology =dictItemMapper.getMethodologNameyByDictCode(approval.getCarbonMethodology());
        log.info("addProjectApproval itemValue: getCountry:[{}] getProvince:[{}] getCity:[{}] getCarbonMethodology:[{}]",
                getCountry,getProvince,getCity,getCarbonMethodology);
        approval.setCountry(getCountry.getItemCh());
        approval.setProvince(getProvince.getItemCh());
        approval.setCity(getCity.getItemCh());
        approval.setCarbonMethodology(getCarbonMethodology);
        String instanceCode = FeiShuAPI.projectApproval(approval);
        log.info("addProjectApproval finally: [{}]", JSONUtil.toJsonStr(approval));
        if (StrUtil.isNotEmpty(instanceCode)){
            log.info("------addProjectApproval---------projectid:{}",approval.getProjectId());
            //System.out.println("------addProjectApproval---------projectid:"+approval.getProjectId());
            saveCarbonApproval(ApprovalCodeEnum.PROJECT_INITIATION_APPROVAL,instanceCode,approval.getProjectId());
        }
    }


    @Override
    public void approvalEventCallback(FeiShuEventParam1 param) {
        String approvalCode = param.getEvent().getApproval_code();
        String instanceCode = param.getEvent().getInstance_code();
        String status = param.getEvent().getStatus();

        CarbonApproval approval = this.lambdaQuery()
                .eq(CarbonApproval::getInstanceCode, instanceCode)
                .one();

        SimpleDateFormat sdf= new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
        log.info("------收到ApprovalCode！！！！："+approvalCode);

        //根据审批状态修改，项目状态
        if (ApprovalCodeEnum.PROJECT_INITIATION_APPROVAL.getCode().equals(approvalCode)) {
            if ("APPROVED".equals(status)) {
                carbonApprovalMapper.updateProjectStatus(approval.getBusinessId(), "0100000002");

                carbonProjectService.updateInitiationDate(sdf.format(new Date()),approval.getBusinessId());
            }
        }
        //添加交易账户
        else if (ApprovalCodeEnum.TRADE_ACCOUNT_APPROVAL.getCode().equals(approvalCode)){
            if ("APPROVED".equals(status)) {
                carbonApprovalMapper.updateExchangeAccountStatus(approval.getBusinessId(), "0430000004");
                carbonApprovalMapper.updateExchangeDate(approval.getBusinessId(),sdf.format(new Date()));
                log.info("---------------已通过！！！");
            }
            else if("REJECTED".equals(status))
            {
                log.info("---------------已拒绝！！！");
                log.info("---------------状态："+status);
                carbonApprovalMapper.deleteExchangeAccount(approval.getBusinessId());
            }
        }
        else if(ApprovalCodeEnum.ASSETS_APPROVAL.getCode().equals(approvalCode))
        {
            if ("APPROVED".equals(status)) {
                log.info("---------------资产审批通过！！！");
                carbonApprovalMapper.updateAssetsStatus(approval.getBusinessId(),"0130000001");
            }
            else if("REJECTED".equals(status))
            {
                log.info("---------------资产审批驳回！！！");
                carbonApprovalMapper.updateAssetsStatus(approval.getBusinessId(),"0130000005");
            }
        }
        else if(ApprovalCodeEnum.QUOTA_APPROVAL.getCode().equals(approvalCode))
        {
            if ("APPROVED".equals(status)) {
                log.info("---------------配额审批通过！！！");
                //do  0130000001
                carbonApprovalMapper.updateQuotaStatus(approval.getBusinessId(),"0130000001");
            }
            else if("REJECTED".equals(status))
            {
                log.info("---------------配额审批驳回！！！");
                //do  0130000005
                carbonApprovalMapper.updateQuotaStatus(approval.getBusinessId(),"0130000005");
            }
        }
    }

    /**
     * 保存审批记录
     * @param codeEnum 审批定义类型
     * @param instanceCode 审批实例code
     * @param businessId 审批关联的业务id
     */
    private void saveCarbonApproval(ApprovalCodeEnum codeEnum,String instanceCode,Long businessId){
        CarbonApproval approval = new CarbonApproval();
        approval.setApprovalCode(codeEnum.getCode());
        approval.setInstanceCode(instanceCode);
        approval.setBusinessId(businessId);
        save(approval);
    }


    @Override
    public void updateForm(String instanceCode,String status) {
        QueryWrapper<CarbonApproval> queryWrapper=new QueryWrapper();
        queryWrapper.eq("instance_code", instanceCode);
        CarbonApproval instance = carbonApprovalMapper.selectOne(queryWrapper);
        CarbonProject carbonProject = projectMapper.selectById(instance.getBusinessId());

        CarbonProjectFormVo projectForm = new CarbonProjectFormVo();
        fillNull(projectForm);

        BeanUtil.copyProperties(carbonProject,projectForm,CopyOptions.create().setIgnoreNullValue(true).setIgnoreError(true));
        projectForm.setApprovalPerson("龚海平");
        projectForm.setProjectStatus(status);
        QueryWrapper<SysDictItem> wrapper=new QueryWrapper<>();
        //转化方法学字典
        wrapper.eq("item_value",projectForm.getCarbonMethodology());
        projectForm.setCarbonMethodology(dictItemMapper.selectOne(wrapper).getItemCh());
        //转化国家字典
        wrapper=new QueryWrapper<>();
        wrapper.eq("item_value",projectForm.getCountry());
        projectForm.setCountry(dictItemMapper.selectOne(wrapper).getItemCh());
        //转化省份字典
        wrapper=new QueryWrapper<>();
        wrapper.eq("item_value",projectForm.getProvince());
        projectForm.setProvince(dictItemMapper.selectOne(wrapper).getItemCh());
        //转化城市字典
        wrapper=new QueryWrapper<>();
        wrapper.eq("item_value",projectForm.getCity());
        projectForm.setCity(dictItemMapper.selectOne(wrapper).getItemCh());

        FeiShuAPI.updateForm("shtcnBp0M56MxHzp8qRIizlWwLb","b305ce",projectForm.getOwnerName(),projectForm,"R");
    }



    private void fillNull(Object o) {
        Field[] fields = o.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                field.set(o,"");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

}
