package com.carbon.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.carbon.domain.auth.vo.SecurityData;
import com.carbon.auth.entity.SysAccount;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 帐号  Mapper 接口
 * </p>
 *
 * @author Li Jun
 * @since 2021-06-11
 */
@Repository
public interface LoginMapper extends BaseMapper<SysAccount> {

    /**
     * getSecurityDataByToken
     * @param accountId 账户id
     * @return SecurityData
     */
    SecurityData getSecurityDataAccountId(@Param("accountId") Long accountId);
}
