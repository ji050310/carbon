import router from './router'
import store from './store'
import { Message } from 'element-ui'
import NProgress from 'nprogress' // progress bar
import 'nprogress/nprogress.css' // progress bar style
import { getToken } from '@/utils/auth' // get token from cookie
import getPageTitle from '@/utils/get-page-title'

NProgress.configure({ showSpinner: false }) // NProgress Configuration

const whiteList = ['/loginServiceTips','/ourEdition','/loginErrSwiper','/login','/ourServiceTips', '/auth-redirect', '/reg', '/forgetPassWord'] // no redirect whitelist

router.beforeEach(async(to, from, next) => {

  // console.log("path",to.path);
  // start progress bar
  NProgress.start()

  // set page title
  document.title = getPageTitle(to.meta.title)

  // determine whether the user has logged in
  const hasToken = getToken()
  if (hasToken) {
    if (to.path === '/login') {
      // if is logged in, redirect to the home page
      next({ path: '/' })
      NProgress.done()
    }else if((to.path).indexOf("_comp") != -1 ){
      let path = (to.path).replace(/_comp/g,"")
      let url = window.location.protocol + "//" + window.location.host + path;
      if(localStorage.getItem("is_new_window")){
        localStorage.removeItem("is_new_window")
        next(false)
        NProgress.done()
      }else{
        localStorage.setItem("is_new_window",1);
        window.open(url);
        next(false) 
       NProgress.done()
      }
      
    } else {
      const hasRoles = true || store.getters.roles && store.getters.roles.length > 0
      if (hasRoles) {
        next()
      } else {
        try {
          const roles = await store.dispatch('user/getInfo')
          const accessRoutes = await store.dispatch('permission/generateRoutes', roles)
          router.addRoutes(accessRoutes)
          next({ ...to, replace: true })
        } catch (error) {
          // remove token and go to login page to re-login
          await store.dispatch('user/resetToken')
          Message.error(error || 'Has Error')
          next(`/login?redirect=${to.path}`)
          NProgress.done()
        }
      }
    }
  } else {
    /* has no token*/
    if (whiteList.indexOf(to.path) !== -1) {
      // in the free login whitelist, go directly
      next()
    } else {
      // other pages that do not have permission to access are redirected to the login page.
      next(`/login?redirect=${to.path}`)
      NProgress.done()
    }
  }
})

router.afterEach(() => {
  // finish progress bar
  NProgress.done()
})
