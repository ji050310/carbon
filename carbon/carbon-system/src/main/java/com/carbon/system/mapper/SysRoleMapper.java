package com.carbon.system.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.carbon.system.entity.SysRole;
import com.carbon.system.param.SysRoleQueryParam;
import com.carbon.system.vo.SysAccountRoleVo;
import com.carbon.system.vo.SysRoleQueryVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * <p>
 * 角色  Mapper 接口
 * </p>
 *
 * @author Li Jun
 * @since 2021-06-18
 */
@Repository
public interface SysRoleMapper extends BaseMapper<SysRole> {

    IPage<SysRoleQueryVo> getSysRolePageList(Page<?> page, SysRoleQueryParam param);

    SysRoleQueryVo getSysRoleById(String id);
}
