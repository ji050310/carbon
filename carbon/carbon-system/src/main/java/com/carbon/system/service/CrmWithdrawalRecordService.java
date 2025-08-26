package com.carbon.system.service;

import com.carbon.domain.common.ApiResult;
import com.carbon.system.entity.CrmWithdrawalRecord;
import com.carbon.common.service.BaseService;
import com.carbon.system.param.CrmWithdrawalRecordQueryParam;
import com.carbon.system.vo.CrmWithdrawalRecordVo;
import com.carbon.common.api.Paging;
import com.carbon.system.vo.DateQueryDTO;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 提现记录 服务类
 * </p>
 *
 * @author Zhang Jinrui
 * @since 2022-05-02
 */
public interface CrmWithdrawalRecordService extends BaseService<CrmWithdrawalRecord> {

    /**
     * 根据UserID获取查询对象集合
     * @param id 主键id
     * @return CrmWithdrawalRecordQueryVo
     */
    ApiResult getCrmWithdrawalRecordByUserId(String id);

    ApiResult getCrmWithdrawalRecordByDate(DateQueryDTO date);
//
//    /**
//     * 获取分页对象
//     * @param param CrmWithdrawalRecordQueryParam
//     * @return Paging<CrmWithdrawalRecordQueryVo>
//     */
//    Paging<CrmWithdrawalRecordVo> getCrmWithdrawalRecordPageList(CrmWithdrawalRecordQueryParam param);

}
