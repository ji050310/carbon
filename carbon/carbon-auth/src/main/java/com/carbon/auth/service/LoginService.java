package com.carbon.auth.service;

import com.carbon.domain.auth.param.ForgotPasswordParam;
import com.carbon.domain.auth.param.LoginParam;
import com.carbon.domain.auth.param.RegisterParam;
import com.carbon.domain.auth.vo.LoginInfoVo;
import com.carbon.domain.auth.vo.SecurityData;
import com.carbon.domain.common.ApiResult;


/**
 * @author Li Jun
 */
public interface LoginService {

	/**
	 * 用户名密码登录
	 * @param loginParam 登录参数
	 * @return LoginInfoVo
	 */
	LoginInfoVo byLoginName(LoginParam loginParam);


	/**
	 * 退出登录
	 * @param accountId 账户id
	 */
	void logout(Long accountId);


	/**
	 * 注册
	 * @param param RegisterParam
	 */
	void register(RegisterParam param);

	/**
	 * 忘记密码
	 * @param param 参数
	 */
	void forgotPassword(ForgotPasswordParam param);

	/**
	 * 获取认证数据
	 * @param accountId 账户id
	 * @return SecurityData
	 */
	SecurityData getSecurityData(Long accountId);

	/**
	 * 根据请求头中的token 判断 是否登录
	 * @param accountId 账户id
	 * @return Boolean
	 */
	Boolean isLogin(Long accountId);

	/**
	 * 校验权限
	 * @param checkUrl 需要校验的url
	 * @return ApiResult
	 */
	ApiResult<Boolean> checkPermission(String checkUrl);

//	ApiResult<Boolean> checkWxPermission(String token);
	/**
	 * 校验账户
	 * @param accountName
	 * @return ApiResult
	 */
    Boolean verify(String accountName);
}
