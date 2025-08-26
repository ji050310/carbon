package com.carbon.system.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.carbon.domain.system.vo.SysDictModelVo;
import com.carbon.system.entity.SysDict;
import com.carbon.system.entity.SysDictItem;
import com.carbon.system.param.SysDictQueryParam;
import com.carbon.system.vo.SysDictQueryVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 系统字典  Mapper 接口
 * </p>
 *
 * @author Li Jun
 * @since 2021-08-16
 */
@Repository
public interface SysDictMapper extends BaseMapper<SysDict> {

    /**
     * 获取分页对象
     * @param page 分页参数
     * @param param 查询参数
     * @return IPage<SysDictQueryVo>
     */
    IPage<SysDictQueryVo> getSysDictPageList(@Param("page") Page<?> page, @Param("param") SysDictQueryParam param);

    List<SysDictModelVo> getAllDict();

    List<SysDictItem> getFieldDict();

}
