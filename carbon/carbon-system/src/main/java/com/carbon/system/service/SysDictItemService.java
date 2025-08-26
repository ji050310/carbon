package com.carbon.system.service;

import com.carbon.system.entity.SysDictItem;
import com.carbon.common.service.BaseService;
import com.carbon.system.param.SysDictItemQueryParam;
import com.carbon.system.vo.SysDictItemQueryVo;
import com.carbon.common.api.Paging;

import java.io.Serializable;

/**
 * <p>
 * 系统字典条目 服务类
 * </p>
 *
 * @author Jiang zhenhua
 * @since 2022-05-11
 */
public interface SysDictItemService extends BaseService<SysDictItem> {

    /**
     * 根据ID获取查询对象
     * @param id 主键id
     * @return SysDictItemQueryVo
     */
    SysDictItemQueryVo getSysDictItemById(Serializable id);

    /**
     * 获取分页对象
     * @param param SysDictItemQueryParam
     * @return Paging<SysDictItemQueryVo>
     */
    Paging<SysDictItemQueryVo> getSysDictItemPageList(SysDictItemQueryParam param);

}
