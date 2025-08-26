package com.carbon.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.carbon.system.entity.SysMenu;
import com.carbon.system.param.SysMenuQueryParam;
import com.carbon.system.vo.SysMenuVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 菜单  Mapper 接口
 * </p>
 *
 * @author Li Jun
 * @since 2021-06-18
 */
@Repository
public interface SysMenuMapper extends BaseMapper<SysMenu> {


    /**
     * 获取分页对象
     * @param param SysMenuQueryParam
     * @return List<SysPermissionQueryVo>
     */
    List<SysMenuVo> getSysMenuList(@Param("param") SysMenuQueryParam param);

    /**
     * 获取用户菜单
     * @param accountId 用户id
     * @return List<SysMenuVo>
     */
    List<SysMenuVo> getUserMenu(@Param("accountId") Long accountId);

    SysMenuVo getMenuByName(String menuName);
}
