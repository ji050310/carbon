import Layout from '@/layout'

const carbonCheckRouter = {
  path: '/carbonCheck',
  component: Layout,
  // redirect: '/account/personal',
  name: 'carbonCheck',
  children: [
    {
      path: '/carbonCheck/',
      component: () => import('@/views/carbonInventory/index'),
      name: 'carbonCheck',
      meta: { title: '碳盘查', icon: '' }
    },
  ]
}

export default carbonCheckRouter
