import Layout from '@/layout'

const userRouter = {
  path: '/cusRelationship/user',
  component: Layout,
  // redirect: '/account/personal',
  name: 'account',
  // meta: {
  //   title: '用户管理',
  //   icon: 'clipboard'
  // },
  children: [
    // {
    //   path: '/cusRelationship/user',
    //   component: () => import('@/views/user/list/index'),
    //   name: 'Group',
    //   meta: { title: '账户列表', icon: '' }
    // },
    {
      path: '/company/',
      component: () => import('@/views/user/group/index'),
      name: 'Label',
      meta: { title: '用户标签', icon: '' }
    },
    // {
    //   path: '/accountlist',
    //   component: () => import('@/views/user/list/index'),
    //   name: 'Group',
    //   meta: { title: '用户详情', icon: '' }
    // },
    // {
    //   path: '/personalinfo',
    //   component: () => import('@/views/user/list/userDetails'),
    //   name: 'Group',
    //   meta: { title: '账户信息', icon: '' }
    // },
    // {
    //   path: '/account/personalinfo/edit',
    //   component: () => import('@/views/user/list/edit'),
    //   name: 'Group',
    //   meta: { title: '编辑用户信息', icon: '' }
    // },
    // {
    //   path: '/account/personalinfo/read',
    //   component: () => import('@/views/user/list/readUser'),
    //   name: 'Group',
    //   meta: { title: '查看账户信息', icon: '' }
    // },
    // {
    //   path: '/account/personalinfo/userInfo',
    //   component: () => import('@/views/user/list/userInfo'),
    //   name: 'Group',
    //   meta: { title: '账户详情', icon: '' }
    //  },
  ]
}

export default userRouter
