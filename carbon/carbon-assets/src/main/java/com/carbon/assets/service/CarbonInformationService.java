package com.carbon.assets.service;

import com.carbon.assets.entity.CarbonInformation;
import com.carbon.common.service.BaseService;
import com.carbon.assets.param.CarbonInformationQueryParam;
import com.carbon.assets.vo.CarbonInformationQueryVo;
import com.carbon.common.api.Paging;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 碳资讯 服务类
 * </p>
 *
 * @author Li Jun
 * @since 2022-01-11
 */
public interface CarbonInformationService extends BaseService<CarbonInformation> {

    /**
     * 根据ID获取查询对象
     * @param id 主键id
     * @return CarbonInformationQueryVo
     */
    CarbonInformationQueryVo getCarbonInformationById(Serializable id);

    /**
     * 获取分页对象
     * @param param CarbonInformationQueryParam
     * @return Paging<CarbonInformationQueryVo>
     */
    Paging<CarbonInformationQueryVo> getCarbonInformationPageList(CarbonInformationQueryParam param);

    /**
     * 随机获取固定条数list
     * @param num 数量
     * @return List<CarbonInformationQueryVo>
     */
    List<CarbonInformationQueryVo> getRandomList(Integer num);
}
