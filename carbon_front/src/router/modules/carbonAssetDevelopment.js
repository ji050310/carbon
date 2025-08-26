import Layout from '@/layout'
import { AppMain } from '@/layout/components'
let carbonAssetDevelopment = {
  path: '/carbon',
  component: Layout,
  // redirect: '/assets/carbonQuota',
  name: 'carbon',
  // meta: {
  //   title: '碳资产开发',
  //   icon: ''
  // },
  children: [
    /**项目开发立项管理 */
    {
      path: 'projectCreate',
      component: () => import('@/views/carbonAssets/dev/projectDevHome'),
      name: 'projectDevHome',
      meta: { title: '开发立项', icon: '' },
    },
    /**开发进度管理 */
    {
      path: '/carbon/projectSchedule_comp',
      component: () => import('@/views/carbonAssets/dev/projectSchedule'),
      name: 'projectSchedule_comp',
      meta: { title: '开发进度管理', icon: '' },
    },
    /* 飞书文档后缀_comp 新窗口打开需要在路由守卫拦截去除 _comp后缀重新跳转 */
    {
      path: '/carbon/projectSchedule',
      component: () => import('@/views/carbonAssets/dev/projectSchedule'),
      name: 'projectSchedule',
      meta: { title: '开发进度管理', icon: '' },
    },

    {
      path: 'projectDetail',
      component: () => import('@/views/carbonAssets/dev/projectDetail.vue'),
      name: 'projectDetail',
      meta: { title: '项目详情', icon: '' },
    },
    {
      path: 'projectCreate',
      meta: { title: '开发立项', icon: '' },
      component: AppMain,
      children: [
        /* 项目详情页面 */
        {
          path: 'projectDetail',
          component: () => import('@/views/carbonAssets/dev/projectDetail.vue'),
          name: 'projectDetail',
          meta: { title: '项目详情', icon: '', activeMenu: '/carbon/projectCreate' },
        },
        /* 添加项目 */
        {
          path: 'projectAdd',
          component: () => import('@/views/carbonAssets/dev/projectAdd.vue'),
          name: 'projectAdd',
          meta: { title: '创建项目', icon: '', activeMenu: '/carbon/projectCreate' },
        },
        {
          path: 'ownerAdd',
          component: () => import('@/views/carbonAssets/dev/ownerAdd.vue'),
          name: 'ownerAdd',
          meta: { title: '上传项目业主资料', icon: '', activeMenu: '/carbon/projectCreate' },
        },
        {
          path: 'projectEdit',
          component: () => import('@/views/carbonAssets/dev/projectEdit.vue'),
          name: 'projectEdit',
          meta: { title: '修改项目', icon: '', activeMenu: '/carbon/projectCreate' },
        },


      ]
    }
    ,
    //上传业主资料
    {
      path: 'ownerAdd',
      component: () => import('@/views/carbonAssets/dev/ownerAdd.vue'),
      name: 'ownerAdd',
      meta: { title: '上传业主资料', icon: '' },
    },
    /**项目开发实施 */
    {
      path: 'projectDo',
      component: () => import('@/views/carbonAssets/dev/projectDo'),
      name: 'projectDo',
      meta: { title: '项目开发实施', icon: '' },
    },
    /**项目文档管理 */
    {
      path: 'projectFile',
      component: () => import('@/views/carbonAssets/dev/projectFile'),
      name: 'projectFile',
      meta: { title: '项目文档管理', icon: '' },
    },
    //项目文档列表
    {
      path: 'projectFileList',
      component: () => import('@/views/carbonAssets/dev/projectFileList.vue'),
      name: 'projectFileList',
      meta: { title: '项目文档列表', icon: '' },
    },
    // /***中和资产管理 */
    // {
    //   path: '/',
    //   component: () => import('@/views/carbonAssets/cnc/index'),
    //   name: 'carbonAssetsCNCQuery',
    //   meta: { title: '中和资产查询', icon: '' },
    // },
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

    /**方法学 */
    {
      path: 'methodology',
      component: () => import('@/views/carbonAssets/method/index'),
      name: 'carbonMethodology',
      meta: { title: '方法学管理', icon: '' }
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

export default carbonAssetDevelopment
