import axios from 'axios'
import { MessageBox, Message } from 'element-ui'
import store from '@/store'
import { getToken } from '@/utils/auth'
import SettingMer from '@/utils/settingMer'
import { isPhone } from "@/libs/wechat";
// create an axios instance
if(!window.localStorage.getItem("apiBaseURL")){
  window.localStorage.setItem("apiBaseURL",SettingMer.apiBaseURL);
}
const service = axios.create({
  
  baseURL: SettingMer.apiBaseURL, // url = base url + request url
  // withCredentials: true, // send cookies when cross-domain requests
  timeout: 20000 // request timeout
})

// request interceptor
service.interceptors.request.use(
  config => {
    // do something before request is sent
    const token = !store.getters.token?sessionStorage.getItem('token'):store.getters.token;
    if (token) {
      // let each request carry token
      // ['X-Token'] is a custom headers key
      // please modify it according to the actual situationf
      // config.headers['Authori-zation'] = token
      config.headers['token'] = token
    }
    if(/get/i.test(config.method)){
      config.params = config.params || {}
      config.params.temp= Date.parse(new Date()) / 1000
    }
    return config
  },
  error => {
    // do something with request error
    return Promise.reject(error)
  }
)

// response interceptor
service.interceptors.response.use(
  /**
   * If you want to get http information such as headers or status
   * Please return  response => response
  */

  /**
   * Determine the request status by custom code
   * Here is just an example
   * You can also judge the status by HTTP Status Code
   */
  response => {
    const res = response.data
    // if the custom code is not 20000, it is judged as an error.
    if (res.code === 401) {
      // to re-login
      MessageBox.confirm('登录状态已过期，请重新登录。', '提示', {
        confirmButtonText: '重新登录',
        cancelButtonText: '取消',
        type: 'light-green-btn',
        customClass:'tipClass'
      }).then(() => {
        store.dispatch('user/resetToken').then(() => {
          location.reload()
        })
      })
    }
    
    if (res.code === 40006) {
      return Promise.reject(res)
    }
    if (res.code === 40005) {
      return Promise.reject(res)
    }
    if (res.code !== 200  && res.code !== 401) {
      if (isPhone()) { //移动端
        return Promise.reject(res.message)
      }
      Message({
        message: res.msg || 'Error',
        type: 'error',
        duration: 5 * 1000
      })

      return Promise.reject()
      // 50008: Illegal token; 50012: Other clients logged in; 50014: Token expired;
      // return Promise.reject(new Error(res.message || 'Error'))
    } else {
      return res.data ? res.data : res
    }
  },
  error => {
    Message({
      message: error.msg,
      type: 'error',
      duration: 5 * 1000
    })
    return Promise.reject(error)
  }
)

export default service