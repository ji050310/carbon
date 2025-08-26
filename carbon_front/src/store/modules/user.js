import { login, logout, getInfo } from '@/api/user'
import { getToken, setToken, removeToken } from '@/utils/auth'
import router, { resetRouter } from '@/router'
import { isLoginApi } from '@/api/sms'
import Cookies from 'js-cookie'
import { oAuth, getQueryString } from "@/libs/wechat";

const state = {
  token: getToken(),
  name: '',
  avatar: '',
  introduction: '',
  roles: [],
  projectPage: 1,
  isRemember: false,
  isLogin: Cookies.get('isLogin')
}

const mutations = {
  SET_TOKEN: (state, token) => {
    state.token = token
  },
  SET_PROJECTPAGE: (state, page) => {
    state.projectPage = page
  },
  SET_ISLOGIN: (state, isLogin) => {
    state.isLogin = isLogin
    Cookies.set(isLogin)
  },
  SET_INTRODUCTION: (state, introduction) => {
    state.introduction = introduction
  },
  SET_NAME: (state, name) => {
    state.name = name
  },
  SET_AVATAR: (state, avatar) => {
    state.avatar = avatar
  },
  SET_ROLES: (state, roles) => {
    state.roles = roles
  },
  SET_REMEMBER: (state, isRemember) => {
    state.isRemember = isRemember
  }
}

const actions = {
  // user login
  login({ commit }, userInfo) {
    const { account, pwd, key, code, wxCode, rememberMe } = userInfo
    //清空cookie与token
    commit('SET_TOKEN', '')
    commit('SET_ROLES', [])
    removeToken()
    resetRouter()
    let apiBaseURL = "";
    if(window.localStorage.getItem("apiBaseURL")){
       apiBaseURL =  window.localStorage.getItem("apiBaseURL")
    }
    localStorage.clear();
    localStorage.setItem("apiBaseURL",apiBaseURL)
    Cookies.remove('storeStaffList')
    Cookies.remove('JavaInfo')
    sessionStorage.removeItem('token')
    return new Promise((resolve, reject) => {
      // 调用登录接口
      login(userInfo).then(data => {
        // var account = {
        //   account: data.securityData.accountName,
        //   mobile: data.securityData.mobile,
        //   roles: ['admin']
        // }

        var account = data.securityData
        
      
        /* 20220621 */
        // window.localStorage.setItem("avatar", data.securityData.avatar)
        // commit('SET_AVATAR', data.securityData.avatar)
        window.localStorage.setItem("accountName", data.securityData.accountName)
        commit('SET_NAME', data.securityData.accountName)
        

        commit('SET_TOKEN', data.token)
        if (rememberMe) {
          window.localStorage.setItem("carbonCookie", JSON.stringify(account))
          window.localStorage.setItem("carbonToken", data.token)
        }
        Cookies.set('JavaInfo', JSON.stringify(account))
        setToken(data.token)
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },
  // 更新头像
  upAvatar({ commit },avatar){
    return new Promise((resolve, reject) => {
      commit('SET_AVATAR', avatar)
    })

  },
  // 用户名 
  updateName({ commit },name){
    return new Promise((resolve, reject) => {
      commit('SET_NAME', name)
    })

  },

  // 短信是否登录
  isLogin({ commit }, userInfo) {
    // const { username, password } = userInfo
    return new Promise((resolve, reject) => {
      isLoginApi().then(async res => {
        commit('SET_ISLOGIN', res.status)
        resolve(res)
      }).catch(res => {
        commit('SET_ISLOGIN', false)
        reject(res)
      })
    })
  },

  // get user info
  getInfo({ commit, state }) {
    return new Promise((resolve, reject) => {
      getInfo(state.token).then(data => {
        if (!data) {
          reject('Verification failed, please Login again.')
        }
        const { roles, account } = data

        // roles must be a non-empty array
        if (!roles || roles.length <= 0) {
          reject('getInfo: roles must be a non-null array!')
        }

        commit('SET_ROLES', roles)
        // commit('SET_ROLES', ['admin'])
        commit('SET_NAME', account)
        // commit('SET_AVATAR', avatar)
        commit('SET_AVATAR', '#')
        commit('SET_INTRODUCTION', '#')
        resolve(data)
      }).catch(error => {
        reject(error)
      })
    })
  },
  setRemember({ commit }, dispatch) {
    commit('SET_REMEMBER', dispatch)
  },
  setProjectPage({ commit }, dispatch) {
    commit('SET_PROJECTPAGE', dispatch)
  },
  // user logout
  logout({ commit, state, dispatch }) {

    return new Promise((resolve, reject) => {
      // logout(state.token).then(() => {
      // alert('成功退出账号')
      commit('SET_TOKEN', '')
      commit('SET_ROLES', [])
      removeToken()
      resetRouter()
      
      if(window.localStorage.getItem("apiBaseURL")){
        apiBaseURL =  window.localStorage.getItem("apiBaseURL")
      }
      localStorage.clear();
      localStorage.setItem("apiBaseURL",apiBaseURL)
      Cookies.remove('storeStaffList')
      Cookies.remove('JavaInfo')
      sessionStorage.removeItem('token')
      // reset visited views and cached views
      // to fixed https://github.com/PanJiaChen/vue-element-admin/issues/2485
      dispatch('tagsView/delAllViews', null, { root: true })

      resolve()
      // }).catch(error => {
      //   reject(error)
      // })
    })
  },

  // remove token
  resetToken({ commit }) {
    return new Promise(resolve => {
      commit('SET_TOKEN', '')
      commit('SET_ROLES', [])
      removeToken()
      resolve()
    })
  },
  // 设置token
  setToken({ commit }, state) {
    return new Promise(resolve => {
      commit('SET_TOKEN', state.token)
      Cookies.set('JavaInfo', JSON.stringify(state))
      resolve()
    })
  },

  // dynamically modify permissions
  changeRoles({ commit, dispatch }, role) {
    return new Promise(async resolve => {
      const token = role + '-token'

      commit('SET_TOKEN', token)
      setToken(token)

      const { roles } = await dispatch('getInfo')

      resetRouter()

      // generate accessible routes map based on roles
      const accessRoutes = await dispatch('permission/generateRoutes', roles, { root: true })

      // dynamically add accessible routes
      router.addRoutes(accessRoutes)

      // reset visited views and cached views
      dispatch('tagsView/delAllViews', null, { root: true })

      resolve()
    })
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}
