import request from '@/utils/request'

export function systemConfigCheck(pram) {
  const data = {
    name: pram.name
  }
  return request({
    url: '/admin/system/config/check',
    method: 'GET',
    params: data
  })
}

export function systemConfigInfo(pram) {
  const data = {
    formId: pram.id
  }
  return request({
    url: '/admin/system/config/info',
    method: 'GET',
    params: data
  })
}

export function systemConfigSave(pram) {
  return request({
    url: '/admin/system/config/save/form',
    method: 'POST',
    data: pram
  })
}
// 项目文档管理
export function projectFileManager(pram) {
  return request({
    url: '/assets/carbonMetaregistryDoc/getPageList',
    method: 'POST',
    data: pram
  })
}
/**
 * 文件上传
 * @param data
 */
export function fileFileApi(data, params) {
  return request({
    url: '/admin/upload/file',
    method: 'POST',
    params,
    data
  })
}

/**
 * 图片上传
 * @param data
 */
export function fileImageApi(data, params) {
  return request({
    url: '/admin/upload/image',
    method: 'POST',
    params,
    data
  })
}

/**
 * 图片列表
 * @param data
 */
export function fileListApi(params) {
  return request({
    url: '/admin/system/attachment/list',
    method: 'get',
    params
  })
}
/*
 *@Description: 项目详情
 *@MethodAuthor: liuboting
 *@Date: 2022-08-20 12:16:27
*/
export function getProjectDetail(id) {
  return request({
    url: '/assets/carbonMetaregistry/info/' + id,
    method: 'get',
  })
}
// 项目库详情页接口
export function getProjectLibDetail(refId, typeCode) {
  

  var id = refId == "--" ? "" : refId
  var code = typeCode == "--" ? "" : typeCode
  return request({
    url: '/assets/EscarbonMetaregistry/getByRef?refId=' + id + '&typeCode=' + code,
    method: 'get'
  })
}
/**
 * 图片列表 删除图片
 * @param data
 */
export function fileDeleteApi(id) {
  return request({
    url: `/admin/system/attachment/delete/${id}`,
    method: 'get'
  })
}

/**
 * 图片列表 移動分類
 * @param data
 */
export function attachmentMoveApi(data) {
  return request({
    url: `/admin/system/attachment/move`,
    method: 'post',
    data
  })
}


/**
 * 微信上传图片
 * @param data
 */
export function wechatUploadApi(data, params) {
  return request({
    url: `/admin/wechat/media/upload`,
    method: 'post',
    data,
    params
  })
}
