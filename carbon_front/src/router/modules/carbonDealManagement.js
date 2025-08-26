import Layout from '@/layout'
import { AppMain } from '@/layout/components'
let carbonDealManagement = {
  path: '/trade',
  component: Layout,
  name: 'trade',
  redirect: 'noRedirect',
  // meta: {
  //   title: '碳交易管理',
  //   icon: ''
  // },
  children: [
    /**账户管理 */
    {
      path: 'account',
      component: () => import('@/views/carbonTrade/account/exchangeManager'),
      // name: '账户管理',
      // meta: { title: '账户管理', icon: '' },
      children: [
        {
          path: 'exchange',
          component: () => import('@/views/carbonTrade/account/exchangeManager'),
          name: '交易所管理',
          meta: { title: '交易所管理', icon: '' },
        },
      ]
    },
    {
      path: 'account/open',
      component: () => import('@/views/carbonTrade/account/tradeAccountManager'),
      name: '交易账户管理',
      meta: { title: '交易账户管理', icon: '' },
    },

    /***中和资产管理 */
    {
      path: '/',
      component: () => import('@/views/carbonAssets/cnc/index'),
      name: 'carbonAssetsCNCQuery',
      meta: { title: '中和资产查询', icon: '' },
    },
    {
      path: '/carbonAssets/add',
      component: () => import('@/views/carbonAssets/cnc/addAssets'),
      name: 'addAssets',
      meta: { title: '添加中和资产查询', icon: '' },
    },
    {
      path: '/carbonAssets/read',
      component: () => import('@/views/carbonAssets/cnc/readAssets'),
      name: 'readAssets',
      meta: { title: '查看中和资产查询', icon: '' },
    },
    {
      path: '/carbonAssets/edit',
      component: () => import('@/views/carbonAssets/cnc/editAssets'),
      name: 'editAssets',
      meta: { title: '编辑中和资产查询', icon: '' },
    },

    /***碳减排 */
    {
      path: '/dataRecord',
      component: () => import('@/views/carbonAssets/dataSource/index'),
      name: 'dataRecord',
      meta: { title: '碳数据报送', icon: '' },
    },
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

    /**询报价管理 */
    {
      path: '/trade/offer',
      component: () => import('@/views/carbonTrade/offer/index'),
      name: 'offer',
      meta: { title: '询报价管理', icon: '' }
    },
    // {
    //   path: '/trade',
    //   component: AppMain,
    //   meta: { title: '履约管理', icon: '' },
    //   children: [{
    //     path: 'orderDetail',
    //     component: () => import('@/views/carbonTrade/offer/orderDetail'),
    //     name: 'quotation',
    //     meta: { title: '意向成交详情', icon: '' }
    //   }]
    // },
    /**
     * 意向成交详情
     */
    /**履约管理 */
    // {
    //   path: 'performance',
    //   component: () => import('@/views/carbonTrade/performance/index'),
    //   name: 'performance',
    //   meta: { title: '履约管理', icon: '' }
    // },
    // /**履约管理 */
    // {
    //   path: 'performance',
    //   meta: { title: '履约管理', icon: '' },
    //   component: AppMain,
    //   children: [
    //     {
    //       path: 'orderDetail',
    //       component: () => import('@/views/carbonTrade/offer/orderDetail'),
    //       name: 'quotation',
    //       meta: { title: '意向成交详情', icon: '' }
    //     },
    //   ]
    // },
    /**
     * 供需行情
     */
    {
      path: '/trade/quotation',
      component: () => import('@/views/carbonTrade/quotation/quota'),
      name: 'quotation',
      meta: { title: '供需行情', icon: '' }
    },
    // {
    //   path: '/carbon/projectSchedule_comp',
    //   component: () => import('@/views/carbonTrade/process/devProcessManager'),
    //   name: 'process',
    //   meta: { title: '开发进度管理', icon: '' }
    // }
  ]
}

export default carbonDealManagement
