import './assets/main.css'

import { createApp } from 'vue'

import App from './App.vue'
import router from './router'
import { clearUser, getUser, isAuthenticated, setUser } from './store';
import VueCookies from 'vue-cookies';

const app = createApp(App)

app.use(router)
app.config.globalProperties.$setUser = setUser;
app.config.globalProperties.$clearUser = clearUser;
app.config.globalProperties.$getUser = getUser;
app.config.globalProperties.$isAuthenticated = isAuthenticated;
app.use(VueCookies);

app.mount('#app')
