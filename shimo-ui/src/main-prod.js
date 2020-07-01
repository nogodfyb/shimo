import Vue from 'vue'
import App from './App.vue'
import router from './router'
import './assets/css/global.css'
import './assets/fonts/iconfont.css'

import axios from 'axios'
axios.defaults.baseURL = 'http://172.17.60.233:8081/shimo/'
// 允许携带cookie
axios.defaults.withCredentials = true
Vue.prototype.$http = axios

Vue.config.productionTip = false

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
