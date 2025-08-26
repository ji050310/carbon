import Layout from '@/layout'

let carbonNewsManager = {
  path: '/trade/',
  component: Layout,
  name: 'trade',
  //   meta: {
  //     title: '碳交易管理',
  //     icon: ''
  //   },
  children: [
    // {
    //     path: '/',
    //     component: () => import('@/views/trade/index'),
    //     name: 'trade',
    //     meta: { title: '碳交易管理', icon: 'clipboard' }
    // },
    // {
    //   path: '/trade/account/exchange/',
    //   component: () => import('@/views/carbonTrade/account/exchangeManager'),
    //   name: 'exchangeManager',
    //   meta: { title: '交易所管理', icon: 'clipboard' }
    // },
  ]
}

export default carbonNewsManager
