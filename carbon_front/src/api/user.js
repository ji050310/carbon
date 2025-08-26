import request from '@/utils/request'
import { resolve } from 'core-js/fn/promise'
// import { info } from 'node-sass'
import md5 from 'js-md5';
import { getToken } from '@/utils/auth'
/**
 * 注册
 * @param {*} data 
 * @returns 
 */
export function register(data) {
  data.password = md5(data.password)
  data.confirmPassword = md5(data.confirmPassword)
  return request({
    url: '/authCenter/auth/register',
    method: 'post',
    data
  }
  )
}
/**
 * 忘记密码
 * @param {*} data 
 * @returns 
 */
export function putForgotPassword(data) {
  data.confirmPassword = md5(data.confirmPassword)
  data.password = md5(data.password)
  return request({
    url: '/authCenter/auth/forgotPassword',
    method: 'post',
    headers: {
      'Content-Type': 'application/json'
    },
    data
  }
  )
}

/**
 * 登录
 * @param {*} data 
 * @returns 
 */
export function login(info) {
  var data = {
    "accountName": info.account,
    "password": md5(info.pwd)
    //"password": info.pwd
  }
  return request({
    url: '/authCenter/auth/login',
    method: 'post',
    headers: {
      'Content-Type': 'application/json'
    },
    data
  })
}

/**
 * 验证账户是否存在
 * @param {*} data 
 * @returns 
 * {accountName}
 */
export function getAuthCenterAuthVerify(param) {
  return request({
    url: '/authCenter/auth/verify/' + param,
    method: 'GET',

  })
}

export function getInfo(token) {
  return request({
    url: '/admin/getAdminInfoByToken',
    method: 'get',
    token: token
  })
}

/**
 * 退出登录
 * @param {*} data 
 * @returns 
 * {accountName}
 */
export function logout() {
  let token = getToken()
  return request({
    url: '/authCenter/auth/logout',
    method: 'GET',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded',
      'token': token
    }
  })
}

/**
 * 会员管理 列表
 * @param pram
 */
export function userListApi(params) {
  return request({
    url: `/admin/user/list`,
    method: 'get',
    params
  })
}

/**
 * 会员管理 修改
 * @param pram
 */
export function userUpdateApi(params, data) {
  return request({
    url: `/admin/user/update`,
    method: 'post',
    params,
    data
  })
}

/**
 * 会员管理 详情
 * @param pram
 */
export function userInfoApi(params) {
  return request({
    url: `/admin/user/info`,
    method: 'get',
    params
  })
}

/**
 * 会员管理 账户详情
 * @param pram
 */
export function infobyconditionApi(params) {
  return request({
    url: `/admin/user/infobycondition`,
    method: 'get',
    params
  })
}

/**
 * 会员管理 账户详情top数据
 * @param pram
 */
export function topdetailApi(params) {
  return request({
    url: `/admin/user/topdetail`,
    method: 'get',
    params
  })
}

/**
 * 会员管理 批量设置分组
 * @param pram
 */
export function groupPiApi(params) {
  return request({
    url: `/admin/user/group`,
    method: 'post',
    params
  })
}

/**
 * 会员管理 批量设置标签
 * @param pram
 */
export function tagPiApi(params) {
  return request({
    url: `/admin/user/tag`,
    method: 'post',
    params
  })
}

/**
 * 会员管理 积分余额
 * @param pram
 */
export function foundsApi(params) {
  return request({
    url: `/admin/user/operate/founds`,
    method: 'get',
    params
  })
}

/**
 * 会员管理 删除
 * @param pram
 */
export function userDeleteApi(params) {
  return request({
    url: `/admin/user/delete`,
    method: 'get',
    params
  })
}

/**
 * 会员等级 列表
 * @param pram
 */
export function levelListApi(params) {
  return request({
    url: `/admin/system/user/level/list`,
    method: 'get',
    params
  })
}

/**
 * 会员等级 新增
 * @param pram
 */
export function levelSaveApi(data) {
  return request({
    url: `/admin/system/user/level/save`,
    method: 'post',
    data
  })
}

/**
 * 会员等级 编辑
 * @param pram
 */
export function levelUpdateApi(params, data) {
  return request({
    url: `/admin/system/user/level/update`,
    method: 'post',
    params,
    data
  })
}

/**
 * 会员等级 详情
 * @param pram
 */
export function levelInfoApi(params) {
  return request({
    url: `/admin/system/user/level/info`,
    method: 'get',
    params
  })
}

/**
 * 会员等级 删除
 * @param pram
 */
export function levelDeleteApi(params) {
  return request({
    url: `/admin/system/user/level/delete`,
    method: 'get',
    params
  })
}

/**
 * 会员等级 是否显示
 * @param pram
 */
export function levelUseApi(params) {
  return request({
    url: `/admin/system/user/level/use`,
    method: 'get',
    params
  })
}

/**
 * 会员标签 列表
 * @param pram
 */
export function tagListApi(params) {
  return request({
    url: `/admin/user/tag/list`,
    method: 'get',
    params
  })
}

/**
 * 会员标签 新增
 * @param pram
 */
export function tagSaveApi(data) {
  return request({
    url: `/admin/user/tag/save`,
    method: 'post',
    data
  })
}

/**
 * 会员标签 编辑
 * @param pram
 */
export function tagUpdateApi(params, data) {
  return request({
    url: `/admin/user/tag/update`,
    method: 'post',
    params,
    data
  })
}

/**
 * 会员标签 详情
 * @param pram
 */
export function tagInfoApi(params) {
  return request({
    url: `/admin/user/tag/info`,
    method: 'get',
    params
  })
}

/**
 * 会员标签 删除
 * @param pram
 */
export function tagDeleteApi(params) {
  return request({
    url: `/admin/user/tag/delete`,
    method: 'get',
    params
  })
}

/**
 * 会员分组 列表
 * @param pram
 */
export function groupListApi(params) {
  return request({
    url: `/admin/user/group/list`,
    method: 'get',
    params
  })
}

/**
 * 会员分组 新增
 * @param pram
 */
export function groupSaveApi(data) {
  return request({
    url: `/admin/user/group/save`,
    method: 'post',
    data
  })
}

/**
 * 会员分组 编辑
 * @param pram
 */
export function groupUpdateApi(params, data) {
  return request({
    url: `/admin/user/group/update`,
    method: 'post',
    params,
    data
  })
}

/**
 * 会员分组 详情
 * @param pram
 */
export function groupInfoApi(params) {
  return request({
    url: `/admin/user/group/info`,
    method: 'get',
    params
  })
}

/**
 * 会员分组 删除
 * @param pram
 */
export function groupDeleteApi(params) {
  return request({
    url: `/admin/user/group/delete`,
    method: 'get',
    params
  })
}

/**
 *获取登录页图片
 */
export function getLoginPicApi() {
  return request({
    url: `/admin/getLoginPic`,
    method: 'get'
  })
}

/**
 * @description 验证码
 */
export function captchaApi() {
  return request({
    url: `/admin/validate/code/get`,
    method: 'get'
  })
}

/**
 * @description 修改上级推广人
 */
export function updateSpreadApi(data) {
  return request({
    url: `/admin/user/update/spread`,
    method: 'post',
    data
  })
}

/**
 * 获取短信验证码
 * @param {*} data 
 * @returns 
 */
export function regCode(phone) {
  return request({
    url: `/authCenter/auth/register/code/` + phone,
    method: 'GET',
    headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
  })
}
/**
 * 忘记密码 短信验证码
 * @param {*} data 
 * @returns 
 */
export function regForgotPasswordCode(phone) {
  return request({
    url: `/authCenter/auth/forgotPassword/code/` + phone,
    method: 'GET',
    headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
  })
}
