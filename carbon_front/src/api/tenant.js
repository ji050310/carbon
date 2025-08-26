import request from '@/utils/request'
import SettingMer from '@/utils/settingMer'
import store from '@/store'
/**
 * 拉取账户列表数据
 * @param {*} data
 * @returns
 */
export function getPageList(data) {
    return request({
        url: '/system/tenantUser/getPageList',
        method: 'post',
        data
    })
}

/**
 * 新增账户
 * @returns
 */

export function addTenantUserData(data) {
    return request({
        url: '/system/tenantUser/add',
        method: 'POST',
        data
    })
}

/**
 * 账户详情
 * @returns
 */
export function readTenantUserData(id) {
    return request({
        url: '/system/tenantUser/info/' + id,
        method: 'GET',
        // params: {'id' : id},
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
    })
}

/**
 * 删除账户
 * @returns
 */
export function deleteTenantUserData(id) {
    return request({
        url: '/system/tenantUser/delete/' + id,
        method: 'DELETE',
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
    })
}
/**
* 编辑账户
* @returns
*/
export function editTenantUserData(data) {
    return request({
        url: '/system/tenantUser/update',
        method: 'PUT',
        data: data
    })
}

/**
 * 提现
 * @param {} userId
 * @param {*} amount
 * @returns
 */
export function widraw(userId, amount) {
    return request({
        url: '/system/tenantUser/cashBack/' + userId + '/' + amount,
        method: 'PUT',
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
    })
}

/**
* 账号管理-基本信息
* @param {} userId
* @returns
*/
export function getAccoutBaseInfo(userId) {
    return request({
        url: '/system/sysAccount/info/' + userId,
        method: 'GET',
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
    })
}

/**
  * 账号管理-企业信息
  * @param {} userId
  * @returns
  */
export function getAccoutEnterPriseInfo(userId) {
    return request({
        url: '/system/sysTenant/info/' + userId,
        method: 'GET',
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
    })
}

/**
 * 账号管理-账户更新
 * @param {} data
 * @returns
 */
export function putAccountUpdate(data) {
    return request({
        url: '/system/sysAccount/update',
        method: 'POST',
        data
    })
}
//
/**
 * 账号管理-更新密码
 * @param {} data
 * @returns
 */
export function putAccountUpdatePassword(data) {
    return request({
        url: '/system/sysAccount/update/password',
        method: 'POST',
        data
    })
}
/**
 * 账号管理-帐号新增
 * @param {} data
 * @returns
 */
export function putAccountAdd(data) {
    return request({
        url: '/system/sysAccount/add',
        method: 'POST',
        data
    })
}
/**
 * 账号管理-帐号分页列表
 * @param {} data
 * @returns
 */
export function getAccoutPageList(data) {
    return request({
        url: '/system/sysAccount/getPageList',
        method: 'POST',
        data
    })
}

/**
 *
 * 账号管理-帐号头像信息更新
 * @param {} data
 * @returns
 */
export function getAccoutUpdateAvatar(data) {
    return request({
        url: '/system/sysAccount/update/avatar',
        method: 'PUT',
        data
    })
}

/**
*
* 账号管理-帐号基本信息更新
* @param {} data
* @returns
*/
export function getAccoutUpdateBaseInfo(data) {
    return request({
        url: '/system/sysAccount/update/baseInfo',
        method: 'POST',
        data
    })
}
/**
*
* 账号管理-碳交易所列表
* @param {}
* @returns
 /assets/carbonExchange/getListByTenant
*/
export function getListByTenant(data) {
    return request({
        url: '/assets/carbonExchange/getListByTenant',
        method: 'POST',
        data
    })
}
/**
* 账号管理-修改手机号
* @param {}
* @returns
*/
export function putAccountUpdatePhone(data) {
    return request({
        url: '/system/sysAccount/update/phone',
        method: 'PUT',
        data
    })
}
/**
* 账号管理-修改手机号
* @param {}
* @returns
*/
export function getAccoutUpdateCode(phone) {
    return request({
        url: '/system/sysAccount/update/code/' + phone,
        method: 'GET',
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
    })
}
// /**20220602 不在使用
// * 账号管理-绑定邮箱
// * @param {}
// * @returns

// */
// export function putAccountUpdateEmail(data){
//     return request({
//         url: '/system/sysAccount/update/email',
//         method:'POST',
//         data
//     })
// }
/**
* 账号管理-账户-绑定-修改邮箱号（通过输入密码绑定-修改）
* @param {}
* @returns

*/
export function putAccountSend(data) {
    return request({
        url: '/system/sysAccount/send/email',
        method: 'POST',
        data
    })
}
/**
* 租户-用户 条件查询tenantUser
* @param {}
* @returns
/system/tenantUser/query/{param}
*/
export function getTenantUserQuery(param) {
    return request({
        url: '/system/tenantUser/query/' + param,
        method: 'GET',
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
    })

}
/**
* 租户-用户  获取用户 分页列表
* @param {}  
* @returns 
asc=&current=1&size=20&sortField=&sourceChannel=2&countId=&hitCount=&maxLimit=&optimizeCountSql=&orders%5B0%5D.asc=&orders%5B0%5D.column=&records=&searchCount=&total="
*/
export function getUserGetPageList(data) {
    return request({
        url: '/system/tenantUser/getPageList/',
        method: 'GET',
        params: data
        // headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
    })
}
/**
* 租户-用户  查询用户详情
* @param {}  
* @returns 
/system/tenantUser/info/{id}
*/
export function getUserInfo(id) {
    return request({
        url: '/system/tenantUser/info/' + id,
        method: 'GET',
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
    })

}
/**
* 交易账户  添加交易账号
* @param {}  
* @returns 
/assets/exchangeAccount/add
*/
export function putAddExchangeAccount(data) {
    return request({
        url: '/assets/exchangeAccount/add',
        method: 'POST',
        data,
    })

}


export function getUpLoadFileParams() {
    let token = !store.getters.token ? sessionStorage.getItem('token') : store.getters.token;
    return {
        url: `${SettingMer.apiBaseURL}/system/oss/uploadImages`,
        token: token
    }
}
export function getUpLoadProjectParams() {
    let token = !store.getters.token ? sessionStorage.getItem('token') : store.getters.token;
    return {
        url: `${SettingMer.apiBaseURL}/system/oss/uploadFiles`,
        token: token
    }
}
/**
* 飞书文件上传
* @param {}  
* @returns 
*/
export function getFeiShuUpLoadProjectParams() {
    let token = !store.getters.token ? sessionStorage.getItem('token') : store.getters.token;
    return {
        url: `${SettingMer.apiBaseURL}assets/exchangeAccount/uploadCredential`,
        token: token
    }
}
// 飞书项目文件上传
export function getFeiShuUpLoadFileParams() {
    let token = !store.getters.token ? sessionStorage.getItem('token') : store.getters.token;
    return {
        url: `${SettingMer.apiBaseURL}system/feishu/uploadFile`,
        token: token
    }
}
// export function getFeiShuUpLoadProjectParams(){
//     let token = !store.getters.token?sessionStorage.getItem('token'):store.getters.token;
//     return {
//         url: `${SettingMer.apiBaseURL}/system/feishu/uploadFile` ,
//         token: token
//     }
// }
