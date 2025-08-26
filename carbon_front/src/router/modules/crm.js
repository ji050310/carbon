import Layout from '@/layout'

// const padRouter = {
const crmRouter = {
  path: '/crm/',
  component: Layout,
  // redirect: '/account/personal',
  name: 'pad',
  meta: {
    title: '客户关系管理',
    icon: 'clipboard'
  },
  children: [

    {
      path: '/crm/carbondev_comp',
      component: () => import('@/views/crm/carbondev'),
      name: 'carbondev',
      meta: { title: '碳开发客户', icon: '' }
    },

    {
      path: '/crm/carbondev',
      component: () => import('@/views/crm/carbondev'),
      name: 'carbondev',
      meta: { title: '碳开发客户', icon: '' }
    },
  ]
}

export default crmRouter
