import Layout from '@/layout'

const carbonInventoryRouter = {
  path: '/carbonInventory/',
  component: Layout,
  // redirect: '/account/personal',
  name: 'carbonInventory',
  children: [
    {
      path: '/carbonInventory/',
      component: () => import('@/views/carbonInventory/index'),
      name: 'carbonInventory',
      meta: { title: '碳盘查', icon: '' }
    },
  ]
}

export default carbonInventoryRouter
