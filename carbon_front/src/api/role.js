import request from '@/utils/request'
import { getToken } from '@/utils/auth'
export function addRole(data) {
  return request({
    url: '/system/sysRole/add',
    method: 'post',
    data: data
  })
}
export function delRole(id) {
  const url = '/system/sysRole/delete/' + id
  return request({
    url: url,
    method: 'DELETE',
  })
}

export function getRoleMenu(id) {
  return request({
    url: '/system/sysRole/menu/' + id,
    method: 'GET'
  })
}
export function getInfo(pram) {
  const data = {
    ids: pram.id
  }
  return request({
    url: '/admin/system/role/info',
    method: 'get',
    params: data
  })
}

/**
 * 
 * @param {获取角色列表} pram 
 * @returns 
*/
export function getRoleList(data) {
  return request({
    url: '/system/sysRole/getPageList',
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    data: data,
  })
}


export function updateRole(pram) {
  let data = {
    id: pram.id,
    roleCode: pram.roleCode,
    roleName: pram.roleName,
    status: pram.status
  }
  return request({
    url: '/system/sysRole/update',
    method: 'put',
    // params: {id: pram.id},
    data: data
  })
}
