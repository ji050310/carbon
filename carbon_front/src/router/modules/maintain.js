/** When your routing table is too long, you can split it into small modules **/

import Layout from '@/layout'

const maintainRouter = {
  path: '/account',
  component: Layout,
  redirect: '/maintain/devconfiguration/configCategory',
  name: 'maintain',
  // meta: {
  //   title: '账号管理',
  //   icon: 'clipboard'
  // },
  children: [
    {
      path: 'devconfiguration',
      name: 'devconfiguration',
      component: () => import('@/views/maintain'),
      meta: {
        title: '开发配置',
        icon: 'clipboard'
      },
      children: [
        {
          path: 'configCategory',
          name: 'configCategory',
          component: () => import('@/views/maintain/devconfig/configCategroy'),
          meta: {
            title: '配置分类',
            icon: 'clipboard'
          }
        },
        {
          path: 'combineddata',
          name: 'combineddata',
          component: () => import('@/views/maintain/devconfig/combinedData'),
          meta: {
            title: '组合数据',
            icon: 'clipboard'
          }
        },
        {
          path: 'formConfig',
          name: 'formConfig',
          component: () => import('@/views/maintain/formConfig/index'),
          meta: {
            title: '表单配置',
            icon: 'clipboard'
          }
        }
      ]
    },
    {
      path: 'info',
      name: 'info',
      component: () => import('@/views/maintain/user/index.vue'),
      meta: {
        title: '基本信息',
        icon: 'clipboard'
      },
      hidden: true
    },
    {
      path: 'companyInfo',
      name: 'enterprise',
      component: () => import('@/views/maintain/enterprise/index.vue'),
      meta: {
        title: '企业信息',
        icon: 'clipboard'
      },
      hidden: true
    },
    {
      path: '/account/wallet',
      name: 'accountWallet',
      component: () => import('@/views/maintain/accountWallet.vue'),
      meta: {
        title: '资产账户',
        icon: 'clipboard'
      },
      hidden: true
    }
  ]
}

export default maintainRouter
