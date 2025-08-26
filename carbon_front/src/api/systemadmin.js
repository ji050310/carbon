import request from '@/utils/request'

export function getMenu() {
  return request({
    url: '/admin/system/role/testMenu',
    method: 'GET'
  })
}

export function adminDel(pram) {
  const data = {
    id: pram.id
  }
  return request({
    url: '/admin/system/admin/delete',
    method: 'GET',
    params: data
  })
}

export function adminInfo(pram) {
  const data = {
    id: pram.id
  }
  return request({
    url: '/admin/system/admin/info',
    method: 'GET',
    params: data
  })
}
/**
 * 作者: 
 * 时间: 2022-05-30 14:37:45
 * 功能: 开户管理分页列表
 */
export function accountList(data) {
  return request({
    // url: '/admin/system/admin/list',
    url: '/system/sysAccount/getPageList',
    method: 'POST',
    data: data
  })
}
/**
 * 作者: 
 * 时间: 2022-06-17 08:44:50
 * 功能: 拿到租户列表
 */
export function getTenantList() {
  return request({
    // url: '/admin/system/admin/list',
    url: '/system/sysTenant/list',
    method: 'GET',
    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
  })
}
/**
 * 作者: 
 * 时间: 2022-08-26 14:26:05
 * 功能: 删除租户
 */
 export function delTenantById(id) {
  return request({
    // url: '/admin/system/admin/list',
    url: '/system/sysTenant/delete/' + id,
    method: 'DELETE'
  })
}
/**
 * 作者: 
 * 时间: 2022-08-15 
 * 功能:添加租户
 * /system/sysTenant/add
 */
export function addTenantUser(data) {
  return request({
    url: '/system/sysTenant/add',
    method: 'POST',
    data:data,
  })
}
/**
 * 作者: 
 * 时间: 2022-08-15 
 * 功能:租户分页列表
 */
export function getTenantPageList(data) {
  return request({
    url: '/system/sysTenant/getPageList',
    method: 'POST',
    data:data,
  })
}

/**
 * 作者: 
 * 时间: 2022-06-02 14:04:56
 * 功能: 角色列表
 */
export function adminList(data) {
  return request({
    // url: '/admin/system/admin/list',
    url: '/system/sysRole/getPageList',
    method: 'POST',
    data: data
    // params
  })
}
/**
 * 作者: 
 * 时间: 2022-06-02 14:53:34
 * 功能: 更新账户
 */
export function updatedAccout(data) {
  return request({
    // url: '/admin/system/admin/list',
    url: '/system/sysAccount/update',
    method: 'POST',
    data: data
    // params
  })
}
/**
 * 作者: 
 * 时间: 2022-06-02 14:53:34
 * 功能: 更新企业
 */
export function updatedTenant(data) {
  return request({
    // url: '/admin/system/admin/list',
    url: '/system/sysTenant/update',
    method: 'PUT',
    data: data
    // params
  })
}
// 项目库管理
export function getProjectLibList(data) {
  return request({
    // url: '/admin/system/admin/list',
    url: '/assets/carbonMetaregistry/getPageList',
    method: 'post',
    data: data
    // params
  })
}
/**
 * 作者: 
 * 时间: 2022-06-06 14:26:05
 * 功能: 删除账户
 */
export function delAccout(id) {
  return request({
    // url: '/admin/system/admin/list',
    url: '/system/sysAccount/delete/' + id,
    method: 'DELETE'
    // params
  })
}
/**
 * 作者: 
 * 时间: 2022-06-02 15:20:14
 * 功能: 新增用户
 */
export function addAccount(data) {
  return request({
    // url: '/admin/system/admin/list',
    url: '/system/sysAccount/add',
    method: 'POST',
    data: data
    // params
  })
}
/**
 * 
 * 作者: 
 * 时间: 2022-06-02 11:57:11
 * 功能: 查询企业详情
 */
export function getTenantInfo(id) {
  return request({
    // url: '/admin/system/admin/list',
    url: '/system/sysTenant/info/' + id,
    method: 'GET',
    // params
  })
}
// 修改角色权限
export function editRolePermissions(data) {
  return request({
    // url: '/admin/system/admin/list',
    url: '/system/sysRole/menu/add',
    method: 'POST',
    data: data
    // params
  })
}
export function adminAdd(pram) {
  const data = {
    account: pram.account,
    level: pram.level,
    pwd: pram.pwd,
    realName: pram.realName,
    roles: pram.roles.join(','),
    status: pram.status
  }
  return request({
    url: '/admin/system/admin/save',
    method: 'POST',
    params: data
  })
}
//字典分页列表
export function getDictList(data) {
  return request({
    url: '/system/sysDict/getPageList',
    method: 'POST',
    data: data
  })
}
//单个字典的list
export function getSingleDictList(data) {
  return request({
    url: '/system/sysDict/dictConfig/getPageList',
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    data: data
  })
}
//编辑字典
export function editDictList(data) {
  return request({
    url: '/system/sysDict/update',
    method: 'PUT',
    headers: { 'Content-Type': 'application/json' },
    data: data
  })
}
//修改子集字典
export function editChildDict(data) {
  return request({
    url: '/system/sysDict/dictConfig/update',
    method: 'PUT',
    headers: { 'Content-Type': 'application/json' },
    data: data
  })
}
//删除子集字典
export function delChildDict(id) {
  return request({
    url: '/system/sysDict/dictConfig/delete/' + id,
    method: 'DELETE',
    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
  })
}
//添加子集字典
export function addChildDict(data) {
  return request({
    url: '/system/sysDict/dictConfig/add',
    method: 'POST',
    data: data
  })
}
//添加字典
export function addDict(data) {
  return request({
    url: '/system/sysDict/add',
    method: 'POST',
    data: data
  })
}
//删除字典
export function delDict(id) {
  return request({
    url: '/system/sysDict/delete/' + id,
    method: 'DELETE',
    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
  })
}
//获取菜单列表
export function getMenuList(data) {
  return request({
    url: '/system/sysMenu/list',
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    data: data
  })
}
/**
  * @Description:修改菜单
  * @author lbt
  */
export function editMenu(data) {
  return request({
    url: '/system/sysMenu/update',
    method: 'PUT',
    headers: { 'Content-Type': 'application/json' },
    data: data
  })
}
/**
 * 作者: 
 * 时间: 2022-05-30 08:48:47
 * 功能: 根据id修改菜单状态
 */
export function editMenuStatus(data) {
  return request({
    url: '/system/sysMenu/updateStatus',
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    data: data
  })
}
/**
  * @Description:删除菜单
  * @author lbt
  */
export function delMenu(id) {
  return request({
    url: '/system/sysMenu/delete/' + id,
    method: 'DELETE',
    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
  })
}
/**
  * @Description:添加菜单
  * @author lbt
  */
export function addMenu(data) {
  return request({
    url: '/system/sysMenu/add',
    method: 'POST',
    data: data
  })
}
export function adminUpdate(pram) {
  // const data = {
  //   account: pram.account,
  //   level: pram.level,
  //   pwd: pram.pwd,
  //   realName: pram.realName,
  //   roles: pram.roles.join(','),
  //   status: pram.status,
  //   id: pram.id,
  //   isDel: pram.isDel
  // }
  return request({
    url: '/admin/system/admin/update',
    method: 'POST',
    params: pram
  })
}