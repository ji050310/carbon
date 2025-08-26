package com.carbon.system.service;

import com.carbon.domain.common.ApiResult;
import com.carbon.system.entity.CrmCarbonCreditDetail;
import com.carbon.common.service.BaseService;
import com.carbon.system.vo.CrmCarbonCreditDetailVo;
import com.carbon.system.vo.DateQueryDTO;

import java.util.List;

/**
 * <p>
 * 碳信分详情 服务类
 * </p>
 *
 * @author Zhang Jinrui
 * @since 2022-05-02
 */
public interface CrmCarbonCreditDetailService extends BaseService<CrmCarbonCreditDetail> {

    /**
     * 根据ID获取查询对象
     * @param id 主键id
     * @return CrmCarbonCreditDetailQueryVo
     */
    ApiResult getCrmCarbonCreditDetailByUserId(String id);

    /**
     * 通过日期范围查询信息
     * @param date
     * @return
     */
    ApiResult getCrmCarbonCreditDetailByDate(DateQueryDTO date);
//
//    /**
//     * 获取分页对象
//     * @param param CrmCarbonCreditDetailQueryParam
//     * @return Paging<CrmCarbonCreditDetailQueryVo>
//     */
//    Paging<CrmCarbonCreditDetailQueryVo> getCrmCarbonCreditDetailPageList(CrmCarbonCreditDetailQueryParam param);

}
