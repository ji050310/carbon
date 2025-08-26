package com.carbon.system.service;

import com.carbon.domain.system.vo.SysDictModelVo;
import com.carbon.system.entity.SysDict;
import com.carbon.common.service.BaseService;
import com.carbon.system.param.SysDictAddParam;
import com.carbon.system.param.SysDictItemAddParam;
import com.carbon.system.param.SysDictItemQueryParam;
import com.carbon.system.param.SysDictQueryParam;
import com.carbon.system.vo.SysDictItemQueryVo;
import com.carbon.system.vo.SysDictQueryVo;
import com.carbon.common.api.Paging;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 系统字典  服务类
 * </p>
 *
 * @author Li Jun
 * @since 2021-08-16
 */
public interface SysDictService extends BaseService<SysDict> {
    /**
     * 获取所有字典
     * @return list
     */
    List<SysDictModelVo> getAllDict();

    /**
     * 添加字典
     * @param param 字典
     */
    void addSysDict(SysDictAddParam param);

    /**
     * 删除字典
     * @param id 字典id
     */
    void deleteSysDict(Long id);

    /**
     * 获取分页对象
     * @param param SysDictQueryParam
     * @return Paging<SysDictQueryVo>
     */
    Paging<SysDictQueryVo> getSysDictPageList(SysDictQueryParam param);

    /**
     * 根据code 获取字典
     * @return List<SysDictModelVo>
     */
    List<SysDictModelVo> getNameMapByCode(String dictCode);

    /**
     * 修改字典
     * @return boolean
     */
    boolean updateByCode(SysDictAddParam param);

    /**
     * 获取字典项分页对象
     */
    Paging<SysDictItemQueryVo> getSysDictItemPageList(SysDictItemQueryParam sysDictItemQueryParam);

    /**
     * 新增子集字典项
     */
    boolean addSysDictItem(SysDictItemAddParam param);

    /**
     * 修改子集字典项
     */
    boolean updateSysDictItemById(SysDictItemAddParam param);

    /**
     * 删除子集字典项
     */
    boolean removeSysDictItemById(Long id);

    Map<String,Map<String,String>> getCityDict();

    Map<String, List<String>>getFieldDict();
}
