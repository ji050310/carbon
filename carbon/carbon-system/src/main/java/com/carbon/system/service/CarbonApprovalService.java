package com.carbon.system.service;


import com.carbon.common.api.Paging;
import com.carbon.common.service.BaseService;
import com.carbon.domain.mq.entity.AddTradingAccountApproval;
import com.carbon.domain.mq.entity.AssetUploadApproval;
import com.carbon.domain.mq.entity.ProjectApproval;
import com.carbon.domain.mq.entity.QuotaApproval;
import com.carbon.system.entity.CarbonApproval;
import com.carbon.system.param.CarbonProjectInstanceQueryParam;
import com.carbon.system.param.FeiShuEventParam1;
import com.carbon.system.vo.CarbonApprovalQueryVo;

import java.io.Serializable;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author JinRui Zhang
 * @since 2022-06-21
 */
public interface CarbonApprovalService extends BaseService<CarbonApproval> {


    void updateForm(String instanceCode,String status);

    /**
     * 添加资产上传审批
     * @param approval 审批参数
     */
    void addAssetsApproval(AssetUploadApproval approval);

    /**
     * 添加交易账户审批
     * @param approval 审批参数
     */
    void addTradeAccountApproval(AddTradingAccountApproval approval);

    /**
     * 添加项目立项审批
     * @param approval 审批参数
     */
    void addProjectApproval(ProjectApproval approval);

    void addQuotaApproval(QuotaApproval approval);
    /**
     * 审批事件回调处理
     * @param param 回调参数
     */
    void approvalEventCallback(FeiShuEventParam1 param);
}
