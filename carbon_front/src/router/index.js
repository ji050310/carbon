import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'

/* Router Modules */
// import componentsRouter from './modules/components'
import storeRouter from './modules/store'
import orderRouter from './modules/order'
import userRouter from './modules/user'
import crmRouter from './modules/crm'
import carbonCheckRouter from './modules/carbonCheck'
import distributionRouter from './modules/distribution'
import marketingRouter from './modules/marketing'
import financialRouter from './modules/financial'
import contentRouter from './modules/content'
import operationRouter from './modules/operation'
import appSettingRouter from './modules/appSetting'
import maintainRouter from './modules/maintain'
import mobileRouter from './modules/mobile'
// import tenantRouter from './modules/tenant'
// import roleManagerRouter from './modules/roleManager'

import carbonNewsManager from './modules/carbonNewsManager'
import finaManager from './modules/finaManager'
import carbonAssetManager from './modules/carbonAssetManager'
import carbonTradeManager from './modules/carbonTradeManager'
import tradeRouter from './modules/trade'
import carbonQuote from './modules/carbonQuote'
import settingmr from './modules/settingManager'
import carbonAssetDevelopment from './modules/carbonAssetDevelopment'
import carbonDealManagement from "./modules/carbonDealManagement"

/**
 * Note: sub-menu only appear when route children.length >= 1
 * Detail see: https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 *
 * hidden: true                   if set true, item will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu
 *                                if not set alwaysShow, when item has more than one children route,
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noRedirect           if set noRedirect will no redirect in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    roles: ['admin','editor']    control the page roles (you can set multiple roles)
    title: 'title'               the name show in sidebar and breadcrumb (recommend set)
    icon: 'svg-name'             the icon show in the sidebar
    noCache: true                if set true, the page will no be cached(default is false)
    affix: true                  if set true, the tag will affix in the tags-view
    breadcrumb: false            if set false, the item will hidden in breadcrumb(default is true)
    activeMenu: '/example/list'  if set path, the sidebar will highlight the path you set
  }
 */

/**
 * constantRoutes
 * a base page that does not have permission requirements
 * all roles can be accessed
 */
export const constantRoutes = [
  // 商品
  storeRouter,
  // 订单
  orderRouter,
  // 会员
  userRouter,
  // 分销
  distributionRouter,
  // 营销
  marketingRouter,
  // 财务
  financialRouter,
  // 内容
  contentRouter,
  // 设置
  operationRouter,
  // 应用
  appSettingRouter,
  // 维护
  maintainRouter,
  //移动端管理
  mobileRouter,
  // 租户
  // tenantRouter,
  // 绿色认证

  // 碳资讯管理
  carbonNewsManager,
  // 碳金融管理
  finaManager,
  // 中和资产管理
  carbonAssetManager,
  // 碳交易管理
  carbonTradeManager,
  // 交易
  tradeRouter,
  // 行情
  carbonQuote,
  // 系统设置
  settingmr,
  // 碳开发
  carbonAssetDevelopment,
  //
  carbonDealManagement,
  //碳盘查
  carbonCheckRouter,
  // 客户关系管理
  crmRouter,
  

  // 数据
  {
    path: '/datas',
    component: Layout,
    redirect: '/datas',
    name: 'Datas',
    meta: {
      title: '数据',
      icon: 'clipboard'
    },
    children: [
      {
        path: 'transaction',
        component: () => import('@/views/datas/transaction/index'),
        name: 'Transaction',
        meta: { title: '交易数据', icon: '' },
        children: [
          {
            path: 'transactionorder',
            component: () => import('@/views/datas/transaction/order/index'),
            name: 'transactionOrder',
            meta: { title: '订单统计', icon: '' }
          },
          {
            path: 'transactiongoods',
            component: () => import('@/views/datas/transaction/goods/index'),
            name: 'transactionGoods',
            meta: { title: '商品统计', icon: '' }
          }
        ]
      }
    ]
  },
  {
    path: '/404',
    component: () => import('@/views/error-page/404'),
    hidden: true
  },
  {
    path: '/redirect',
    component: Layout,
    hidden: true,
    children: [
      {
        path: '/redirect/:path(.*)',
        component: () => import('@/views/redirect/index')
      }
    ]
  },
  {
    path: '/auth-send',
    component: () => import('@/views/mobile/auth-send'),
    hidden: true
  },
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },
  {
    path: '/reg',
    component: () => import('@/views/login/reg'),
    hidden: true
  },
  {
    path: '/forgetPassWord',
    component: () => import('@/views/login/forgetPassWord'),
    hidden: true
  },
  {
    path: '/auth-redirect',
    component: () => import('@/views/login/auth-redirect'),
    hidden: true
  },
  {
    path: '/401',
    component: () => import('@/views/error-page/401'),
    hidden: true
  },
  {
    path: '/loginErrSwiper',
    component: () => import('@/views/error-page/loginErrSwiper'),
    hidden: true
  },
  {
    path: '/loginServiceTips',
    component: () => import('@/views/error-page/loginServiceTips'),
    hidden: true
  },
  {
    path: '/ourServiceTips',
    component: () => import('@/views/error-page/ourServiceTips'),
    hidden: true
  },
  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    // meta: { title: '首页', icon: 'dashboard', affix: true },
    children: [
      {
        path: 'dashboard',
        component: () => import('@/views/dashboard/index'),
        // name: '首页',
        // meta: { title: '首页', icon: 'dashboard', affix: true }
      }
    ]
  },
  {
    path: '/dashboard',
    component: Layout,
    // meta: { title: '首页', icon: 'dashboard', affix: true },
    // children: [
    //   {
    //     path: 'dashboard',
    //     component: () => import('@/views/dashboard/index'),
    //     name: '首页',
    //     meta: { title: '首页', icon: 'dashboard', affix: true }
    //   }
    // ]
  },
  {
    path: '/setting/uploadPicture',
    component: () => import('@/components/uploadPicture/index.vue'),
    name: 'uploadPicture'
  },
  {
    path: '/train/index',
    component: () => import('@/views/train/index'),
    name: 'train'
  },
  {
    path: '/coperation/index',
    component: () => import('@/views/coperation/index'),
    name: 'coperation'
  },
  { path: '*', redirect: '/404', hidden: true }
]

/**
 * asyncRoutes
 * the routes that need to be dynamically loaded based on user roles
 */
export const asyncRoutes = [

]

const createRouter = () => new Router({
  // mode: 'history', // require service support
  mode: 'history',
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
