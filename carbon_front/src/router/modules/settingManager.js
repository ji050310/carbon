import Layout from '@/layout'

let carbonNewsManager = {
  path: '/sys',
  component: Layout,
  // meta: { title: '系统管理', icon: 'clipboard' },
  name: 'Cert',
  //   meta: {
  //     title: '认证',
  //     icon: ''
  //   },
  children: [
    {
      path: '/sys/information',
      component: () => import('@/views/systemSetting/newsManager'),
      name: 'newsManager',
      meta: { title: '资讯管理', icon: 'clipboard' }
    },
    {
      path: '/sys/metaregistrydoc',
      component: () => import('@/views/systemSetting/projectFileManager/projectFile'),
      name: 'projectFile',
      meta: { title: '项目文档管理', icon: 'clipboard' }
    },
    {
      path: '/sys/projectDetail',
      component: () => import('@/views/systemSetting/projectLib/projectDetail.vue'),
      name: 'projectDetail',
      meta: { title: '项目详情', icon: '' },
    },
    {
      path: '/sys/metaregistry',
      component: () => import('@/views/systemSetting/projectLib/projectLib'),
      name: 'projectLib',
      meta: { title: '项目库管理', icon: 'clipboard' }
    },
    {
      path: '/sys/methodology',
      component: () => import('@/views/systemSetting/methodManager'),
      name: 'methodManager',
      meta: { title: '方法学管理', icon: 'clipboard' }
    },
    {
      path: '/sys/exchange',
      component: () => import('@/views/systemSetting/exchangeManager'),
      name: 'exchangeManager',
      meta: { title: '交易所管理', icon: 'clipboard' }
    },
    {
      path: '/sys/base',
      component: () => import('@/views/systemSetting/administratorAuthority'),
      name: 'baseManagement',
      // meta: { title: '基础管理', icon: 'clipboard' },
      children: [
        {
          path: '/sysRole',
          component: () => import('@/views/systemSetting/administratorAuthority/adminList/index'),
          meta: { title: '角色管理', icon: 'clipboard' },
        },
        {
          path: '/sysDict',
          component: () => import('@/views/systemSetting/dictionaryManagement/index'),
          meta: { title: '字典管理', icon: 'clipboard' },
        },
        {
          path: '/sysMenu',
          component: () => import('@/views/systemSetting/menuManager/index'),
          meta: { title: '菜单管理', icon: 'clipboard' },
        },
        {
          path: '/sysTenant',
          component: () => import('@/views/systemSetting/accountManager/accountManager'),
          meta: { title: '账户管理', icon: 'clipboard' },
        },
      ]
    },
    {
      path: '/sys/business',
      component: () => import('@/views/systemSetting/business'),
      name: 'baseBusiness',
      meta: { title: '平台服务管理', icon: 'clipboard' },
    },
    {
      path: '/sys/business_comp',
      component: () => import('@/views/systemSetting/business'),
      name: 'business_comp',
      meta: { title: '平台服务管理', icon: 'clipboard' },
    },
    {
      path: '/sys/approve_comp',
      component: () => import('@/views/systemSetting/approve'),
      name: 'approve_comp',
      meta: { title: '平台审批管理', icon: 'clipboard' },
    },
    {
      path: '/sys/approve',
      component: () => import('@/views/systemSetting/approve'),
      name: 'baseApprove',
      meta: { title: '平台审批管理', icon: 'clipboard' },
    },
    {
      path: '/sys/tenantMgt',
      component: () => import('@/views/systemSetting/tenantMgtManager/tenantMgtManager'),
      name: 'tenantMgtManager',
      meta: { title: '租户管理', icon: 'clipboard' },
    },
  ]
}

export default carbonNewsManager
