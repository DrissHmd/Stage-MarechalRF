import { createStore } from 'vuex';

const store = createStore({
  state: {
    user: null,
  },
  mutations: {
    setUser(state: { user: any; }, user: any) {
      state.user = user;
    },
    clearUser(state: { user: null; }) {
      state.user = null;
    },
  },
  actions: {
    login({ commit }: any, user: any) {
      commit('setUser', user);
    },
    logout({ commit }: any) {
      commit('clearUser');
    },
  },
  getters: {
    isAuthenticated(state: { user: any; }) {
      return !!state.user;
    },
    getUser(state: { user: any; }) {
      return state.user;
    },
  },
});

export default store;