import Layout from '@/layout'

let carbonNewsManager = {
  path: '/carbonNewsManager/',
  component: Layout,
  name: 'Cert',
//   meta: {
//     title: '认证',
//     icon: ''
//   },
  children: [
  {
      path: '/',
      component: () => import('@/views/news/carbonNews'),
      name: 'greenCert',
      meta: { title: '碳资讯', icon: 'clipboard' }
  },
  {
    path: '/newsDetail',
    component: () => import('@/views/news/newsDetail.vue'),
    name: 'newsDetail',
    meta: { title: '资讯详情', icon: '' }
  },
 ]
}

export default carbonNewsManager
