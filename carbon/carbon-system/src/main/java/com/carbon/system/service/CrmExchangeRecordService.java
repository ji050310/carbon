package com.carbon.system.service;

import com.carbon.domain.common.ApiResult;
import com.carbon.system.entity.CrmExchangeRecord;
import com.carbon.common.service.BaseService;
import com.carbon.system.param.CrmExchangeRecordQueryParam;
import com.carbon.system.vo.CrmExchangeRecordVo;
import com.carbon.common.api.Paging;
import com.carbon.system.vo.DateQueryDTO;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 兑换记录 服务类
 * </p>
 *
 * @author Zhang Jinrui
 * @since 2022-05-02
 */
public interface CrmExchangeRecordService extends BaseService<CrmExchangeRecord> {

    /**
     * 根据ID获取查询对象
     * @param id 主键id
     * @return CrmExchangeRecordQueryVo
     */
    ApiResult getCrmExchangeRecordByUserId(String id);


    ApiResult getCrmExchangeRecordByDate(DateQueryDTO date);


//    /**
//     * 获取分页对象
//     * @param param CrmExchangeRecordQueryParam
//     * @return Paging<CrmExchangeRecordQueryVo>
//     */
//    Paging<CrmExchangeRecordVo> getCrmExchangeRecordPageList(CrmExchangeRecordQueryParam param);

}
