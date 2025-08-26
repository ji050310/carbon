const state = {
    dircs: []
  }
  
  const mutations = {
    SET_DIRCS: (state, dircs) => {
      state.dircs = dircs
    },
    GET_DIRCS: (state) => {
      state.dircs
    }
  }
  
  const actions = {
    setDircs({ commit }, dircs) {
      commit('SET_DIRCS', dircs)
    },
    // getDircs({ commit }) {
    //   commit('CLEAR_ERROR_LOG')
    // }
  }
  
  export default {
    namespaced: true,
    state,
    mutations,
    actions
  }
  