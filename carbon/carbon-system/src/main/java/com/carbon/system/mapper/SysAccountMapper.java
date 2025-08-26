package com.carbon.system.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.carbon.system.entity.SysAccount;
import com.carbon.system.param.SysAccountQueryParam;
import com.carbon.system.vo.SysAccountQueryVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * <p>
 * 帐号  Mapper 接口
 * </p>
 *
 * @author Li Jun
 * @since 2021-06-11
 */
@Repository
public interface SysAccountMapper extends BaseMapper<SysAccount> {
    /**
     * 根据ID获取查询对象
     * @param id 主键id
     * @return SysAccountQueryVo
     */
    SysAccountQueryVo getSysAccountById(Serializable id);

    /**
     * 根据账户名 获取账户(加锁)
     * @param accountName 账户名
     * @return SysAccount
     */
    SysAccount selectForUpdate(@Param("accountName") String accountName);

    /**
     * 根据账户ID 获取账户(加锁)
     * @param accountId 账户id
     * @return SysAccount
     */
    SysAccount selectByIdForUpdate(@Param("accountId") Long accountId);

    /**
     * 获取分页对象
     * @param page 分页参数
     * @param param 查询参数
     * @return IPage<SysAccountQueryVo>
     */
    IPage<SysAccountQueryVo> getSysAccountPageList(@Param("page") Page<?> page, @Param("param") SysAccountQueryParam param);

}
