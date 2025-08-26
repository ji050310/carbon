import { storeStaffListApi } from '@/api/storePoint'
import {  seckillListApi } from '@/api/marketing'
import Cookies from 'js-cookie'

/**
 * @description 确定操作弹框
 */
export function modalSure(title) {
  return new Promise((resolve, reject) => {
    this.$confirm(`确定${title || '永久删除该文件'}?`, '提示', {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning'
    }).then(() => {
      resolve()
    }).catch(() => {
      reject()
      this.$message({
        type: 'info',
        message: '已取消'
      })
    })
  })
}

/**
 * @description 短信是否登录
 */
export function isLogin() {
  return new Promise((resolve, reject) => {
    isLoginApi().then(async res => {
      resolve(res)
    }).catch(res => {
      reject(res)
    })
  })
}

/**
 * @description 核销员列表
 */
export function getStoreStaff() {
  return new Promise((resolve, reject) => {
    storeStaffListApi({page: 1, limit: 9999}).then(async res => {
      localStorage.setItem('storeStaffList', res.list ? JSON.stringify(res.list) : [])
    })
  })
}

/**
 * @description 秒杀配置列表
 */
export function getSeckillList(status) {
  return new Promise((resolve, reject) => {
    seckillListApi({page: 1, limit: 9999, isDel: false, status: status || null}).then(async res => {
      resolve(res)
    })
  })
}

export function dataFormat(params) {
    let time = params;
    var datetime = new Date();
    datetime.setTime(time);
    var year = datetime.getFullYear();
    var month = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1) : datetime.getMonth() + 1;
    var date = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime.getDate();
    var hour = datetime.getHours()< 10 ? "0" + datetime.getHours() : datetime.getHours();
    var minute = datetime.getMinutes()< 10 ? "0" + datetime.getMinutes() : datetime.getMinutes();
    var second = datetime.getSeconds()< 10 ? "0" + datetime.getSeconds() : datetime.getSeconds();
    return year + "-" + month + "-" + date +" " + hour + ":" + minute+":" + second;
}

/**
 * 资产类型为碳额度、绿证时，项目类型类型置灰
 * @param {*} assetTypeName 
 * @returns 
*/
export function isProjectTypeDisable(assetType) {
  return assetType == '0140000002' || assetType == '0140000003'
}

/**
 * 项目类型样式
 * @param {*} isQuota 
 * @returns 
 */
export function projectTypeClass(isDisable) {
  if (isQuota) {
    return "selectbox-root margleft colorType"
  } else {
    return "selectbox-root margleft"
  }
}

/**
 * 列表序号
 * @page 当前页
 * @page 当前页
 * @index 
 * @returns 
 */

export function setListNo(page,size,index) {
  let curPage = parseInt(page);
  let curSize = parseInt(size);
  let curIndex = parseInt(index);
  return (curPage * curSize ) + curIndex;
}
/**
 * 邮箱格式认证
 * @str 
 */
export function emailVerify(str){
  var emailVerify = /^([a-zA-Z0-9]+[_|\-|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\-|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
  return emailVerify.test(str);
}
/**
 * 手机格式认证
 * @str 
 * 13812341234
 * 010-12345678
 * (0432)1234567-1234
 * 12345678
 */

 export function verifyPhoneNumber(str){
  var myreg =/^1\d{10}$|^(0\d{2,3}-?|\(0\d{2,3}\))?[1-9]\d{4,7}(-\d{1,8})?$/;
  return myreg.test(str);
}
/**
 * 防抖
 */
 export function click_action(fn, waite) {
  var timeout = null
  var waiteTime = waite || 1000
  return function () {
    var _this = this
    if (timeout) clearTimeout(timeout)
    timeout = setTimeout(function () {
        fn.apply(_this)
    }, waiteTime)
  }
}

/**
 * 千、百万级的数量加上逗号
 */
 export function setLargeNumber(str){
  if(!str || str == 0 || str == "0"){
    return 0;
  }
  if(str == '--'){
    return str;
  }
  let num = Number(str)
  return num.toLocaleString();
 }

