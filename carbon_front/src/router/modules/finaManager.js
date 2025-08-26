import Layout from '@/layout'

let finaManager = {
  path: '/carbonFinaManager/',
  component: Layout,
  name: 'finaManager',
//   meta: {
//     title: '',
//     icon: ''
//   },
  children: [
  {
      path: '/',
      component: () => import('@/views/finaManager/index.vue'),
      name: 'FinaManager',
      meta: { title: '碳金融管理', icon: 'clipboard' }
  },
  // {
  //   path: 'addRole',
  //   component: () => import('@/views/systemSetting/administratorAuthority/identityManager/index.vue'),
  //   name: 'addRole',
  //   meta: { title: '添加身份', icon: '' }
  // },
 ]
}

export default finaManager
