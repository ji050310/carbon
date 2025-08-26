package com.carbon.system.service;

import com.carbon.system.entity.DataPanel;
import com.carbon.common.service.BaseService;
import com.carbon.system.vo.StatHomeDataVo;
import com.carbon.system.vo.EnterpriseGreennessVo;

/**
 * <p>
 * 数据面板 服务类
 * </p>
 *
 * @author Li Jun
 * @since 2021-07-20
 */
public interface DataPanelService extends BaseService<DataPanel> {

    StatHomeDataVo getHomeData();

    EnterpriseGreennessVo getGreenness();
}
