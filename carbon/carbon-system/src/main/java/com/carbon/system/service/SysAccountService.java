package com.carbon.system.service;

import com.carbon.domain.common.ApiResult;
import com.carbon.domain.system.param.ChangePasswordParam;
import com.carbon.domain.system.param.SysAccountParam;
import com.carbon.system.entity.SysAccount;
import com.carbon.common.service.BaseService;
import com.carbon.system.param.*;
import com.carbon.system.vo.SysAccountQueryVo;
import com.carbon.common.api.Paging;

/**
 * <p>
 * 帐号  服务类
 * </p>
 *
 * @author Li Jun
 * @since 2021-06-11
 */
public interface SysAccountService extends BaseService<SysAccount> {

    /**
     * 添加账户
     * @param param 参数
     */
    void addAccount(SysAccountParam param);

    /**
     * 更新账户
     * @param param 参数
     */
    void updateAccount(SysAccountParam param);

    /**
     * 根据ID获取查询对象
     * @param id 主键id
     * @return SysAccountQueryVo
     */
    SysAccountQueryVo getSysAccountById(Long id);

    /**
     * 获取分页对象
     * @param param SysAccountQueryParam
     * @return Paging<SysAccountQueryVo>
     */
    Paging<SysAccountQueryVo> getSysAccountPageList(SysAccountQueryParam param);


    /**
     * 更新账户基本信息
     * @param param 参数
     */
    void updateAccountBaseInfo(AccountBaseInfoUpdateParam param);

    /**
     * 修改密码
     * @param param 参数
     */
    void updatePassword(ChangePasswordParam param);

    /**
     * 修改头像
     * @param param 参数
     */
    void updateAccountAvatar(ChangeAvatarParam param);


    /**
     * 发送修改验证码
     * @param phone 参数
     */
    void sendUpdateCode(String phone);

    /**
     * 修改手机号
     * @param param 参数
     */
    void updatePhone(ChangePhoneParam param);

    /**
     * 绑定/修改邮箱
     * @param param 参数
     */
    void bindEmail(BindEmailParam param);

    /**
     * 发送邮件到客户邮箱
     * @param param 参数
     */
    void sendEmail(SendEmailParam param);

    /**
     * 绑定/修改邮箱
     * @param id 参数
     */
    void bindEmail2(Long id,String email,String value);

    /**
     * 删除账户
     * @param id 参数
     */
    void deleteAccountById(Long id);

}
