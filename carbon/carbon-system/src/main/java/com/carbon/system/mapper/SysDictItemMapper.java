package com.carbon.system.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.carbon.system.entity.SysDictItem;
import com.carbon.system.param.SysDictItemQueryParam;
import com.carbon.system.vo.SysDictItemQueryVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * <p>
 * 系统字典条目 Mapper 接口
 * </p>
 *
 * @author Jiang zhenhua
 * @since 2022-05-11
 */
@Repository
public interface SysDictItemMapper extends BaseMapper<SysDictItem> {

    /**
     * 根据ID获取查询对象
     * @param id 主键id
     * @return SysDictItemQueryVo
     */
    SysDictItemQueryVo getSysDictItemById(Serializable id);


    /**
     * 根据DictItem获取查询对象
     * @param id 主键id
     * @return SysDictItemQueryVo
     */
    SysDictItemQueryVo getSysDictItemByItemValue(String id);

    /**
     * 根据dict_code 获取 carbon_methodology表的 方法学名
     * @param id 主键id
     * @return SysDictItemQueryVo
     */
    String getMethodologNameyByDictCode(String id);

    /**
     * 获取分页对象
     * @param page 分页参数
     * @param param 查询参数
     * @return IPage<SysDictItemQueryVo>
     */
    IPage<SysDictItemQueryVo> getSysDictItemPageList(@Param("page") Page<?> page, @Param("param") SysDictItemQueryParam param);

}
