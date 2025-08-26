import Layout from '@/layout'
import { AppMain } from '@/layout/components'
let carbonAssetManager = {
  path: '/assets',
  component: Layout,
  // redirect: '/assets/carbonQuota',
  // name: 'assets',
  // meta: {
  //   title: '碳资产管理',
  //   icon: ''
  // },
  redirect: 'noRedirect',
  children: [
    {
      path: '/dataRecord/add',
      component: () => import('@/views/carbonAssets/dataSource/addSource'),
      name: 'addSource',
      meta: { title: '添加碳数据', icon: '' }
    },
    {
      path: '/dataRecord/read',
      component: () => import('@/views/carbonAssets/dataSource/readSource'),
      name: 'readSource',
      meta: { title: '查看碳数据', icon: '' }
    },
    {
      path: '/dataRecord/edit',
      component: () => import('@/views/carbonAssets/dataSource/editSource'),
      name: 'editSource',
      meta: { title: '编辑碳数据', icon: '' }
    },

    {
      path: '/carbonAccounting',
      component: () => import('@/views/carbonAssets/apply/projectListView'),
      name: 'carbonAccounting',
      meta: { title: '碳减排核', icon: '' }
    },

    /***CCER项目*/
    {
      path: '/applyProject/',
      component: () => import('@/views/carbonAssets/apply/projectListView'),
      name: 'projectList',
      meta: { title: '申请项目主页', icon: '' }
    },
    {
      path: '/applyProject/add',
      component: () => import('@/views/carbonAssets/apply/addNewProject'),
      name: 'addNewProject',
      meta: { title: '申请项目', icon: '' }
    },
    {
      path: '/applyProject/read',
      component: () => import('@/views/carbonAssets/apply/readProject'),
      name: 'readProject',
      meta: { title: '查看项目', icon: '' }
    },
    {
      path: '/applyProject/edit',
      component: () => import('@/views/carbonAssets/apply/editProject'),
      name: 'editProject',
      meta: { title: '编辑项目', icon: '' }
    },
    {
      path: 'quota',
      component: () => import('@/views/carbonAssets/quota'),
      name: 'carbonQuota',
      meta: { title: '碳配额', icon: '' },
      // children: [{
      //   path: 'quotaDetail',
      //   component: () => import('@/views/carbonAssets/quotaDetail'),
      //   name: 'quotaDetail',
      //   meta: { title: '碳配额详情', icon: '' }
      // }]
    },
    {
      path: 'quota',
      component: AppMain,
      meta: { title: '碳配额', icon: '' },
      children: [{
        path: 'quotaDetail',
        component: () => import('@/views/carbonAssets/quotaDetail'),
        name: 'quotaDetail',
        meta: { title: '碳配额详情', icon: '',activeMenu: '/assets/quota' }
      }]
    },
    {
      path: 'quota/quotaDetail',
      component: () => import('@/views/carbonAssets/quotaDetail'),
      name: 'quotaDetail',
      meta: { title: '碳配额详情', icon: '' }
    },
    {
      path: 'credit',
      component: () => import('@/views/carbonAssets/credit'),
      name: 'carbonCredit',
      meta: { title: '碳信用', icon: '' },
    },
    {
      path: 'credit',
      component: AppMain,
      meta: { title: '碳信用', icon: '' },
      children: [{
        path: 'assetDetail',
        component: () => import('@/views/carbonAssets/assetDetail'),
        name: 'assetDetail',
        meta: { title: '碳信用详情', icon: '', activeMenu: '/assets/credit'}
      },
      ]
    },
    {
      path: 'credit/assetDetail',
      component: () => import('@/views/carbonAssets/assetDetail'),
      name: 'assetDetail',
      meta: { title: '碳资产详情', icon: '' }
    },
    {
      path: 'credit/CarbonAssessment',
      component: () => import('@/views/carbonAssets/CarbonAssessment'),
      name: 'CarbonAssessment',
      meta: { title: '碳评估', icon: '' }
    },
    {
      path: '/carbonMethodology/add/',
      component: () => import('@/views/carbonAssets/method/methodDetail'),
      name: 'methodDetail',
      meta: { title: '添加方法学详情页', icon: '' }
    },
    // {
    //   path: '/carbonMethodology/add',
    //   component: () => import('@/views/carbonAssets/method/addMethod'),
    //   name: 'addNewProject',
    //   meta: { title: '申请方法学', icon: '' }
    // },
    {
      path: '/carbonMethodology/readMethod',
      component: () => import('@/views/carbonAssets/method/readMethod'),
      name: 'readProject',
      meta: { title: '查看方法学', icon: '' }
    },
    {
      path: '/carbonMethodology/editMethod',
      component: () => import('@/views/carbonAssets/method/editMethod'),
      name: 'editProject',
      meta: { title: '编辑方法学', icon: '' }
    },
  ]
}

export default carbonAssetManager
