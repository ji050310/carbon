import request from '@/utils/request'

/**
 * 拉取账户列表数据
 * @param {*} data
 * @returns
 * /system/feishu/information/{code}
 */
/**
 * 获取鉴权信息 云文档
 * @param 
 */
 export function getFeishuInformation(datas) {
  // return  console.log("datas",datas);
    return request({
      url: '/system/feishu/information',
      method: 'GET',
      params: datas,
      headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
      
    })
  }

/**
 * 获取电子表格鉴权信息 云文档
 * @param 
 */
 export function getFeishuFormInformation(datas) {
  // return  console.log("datas",datas);
    return request({
      url: '/system/feishu/formInformation',
      method: 'GET',
      params:datas,
      headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
    })
  }

  /**
 * 飞书API调用 模块
 * 修改指定内容
 * @param {} data
 * @returns
 */
export function putFeishuUpdate(data) {
  return request({
      url: '/system/feishu/update',
      method: 'POST',
      data,
      headers:{'Content-Type':"application/x-www-form-urlencoded"}
  })
}
 /**
 * 飞书API调用 模块
 * 修改指定内容测试
 * @param {} data
 * @returns
 */
export function putFeishuUpdateTest(data) {
  return request({
      url: '/system/feishu/update/test',
      method: 'POST',
      data,
      headers:{'Content-Type':"application/x-www-form-urlencoded"}
  })
}

 /**
 * 飞书API调用 模块
 * 根据文档编号和位置修改指定区域内容
 * @param {} data
 * @returns
 */
export function putFeishuUpdateByNum(data) {
  return request({
      url: '/system/feishu/updateByNum',
      method: 'POST',
      data,
      headers:{'Content-Type':"application/x-www-form-urlencoded"}
  })
}

 /**
 * 获取文档信息
 * @param {} data
 * @returns
 */
export function getFeishuFileDate(templateNum) {
  return request({
      url: '/system/feishu/getFileDate/' + templateNum,
      method: 'GET',
      headers:{'Content-Type':"application/x-www-form-urlencoded"}
  })
}
 /**
 * 获取项目对应的飞书文档
 * @param {} data
 * @returns
 */
export function getFeishuProjectFile(data) {
  return request({
      url: '/system/feishu/getProjectFile' ,
      method: 'GET',
      params: data,
      headers:{'Content-Type':"application/x-www-form-urlencoded"}
  })
}

  /**
 * url =  用不到
 * @param {} url
 * code
 * @returns
 */
function getParams(code){
  let url  = window.location.search;
  var res = new RegExp("(?:&|/?)" + code + "=([^&$]+)").exec(url);
  return res ? res[1] : '';
}
