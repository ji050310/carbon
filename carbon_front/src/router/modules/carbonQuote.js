import Layout from '@/layout'

let carbonQuote = {
  path: '/carbonQuote/',
  component: Layout,
  name: 'carbonQuote',
//   meta: {
//     title: '认证',
//     icon: ''
//   },
  children: [
  {
      path: '/',
      component: () => import('@/views/quote/quoteHome.vue'),
      name: 'carbonQuote',
      meta: { title: '碳交易行情', icon: '' }
  }
 ]
}

export default carbonQuote