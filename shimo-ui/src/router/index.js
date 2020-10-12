import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../components/Home.vue'
import Welcome from '../components/Welcome.vue'
import GraphiteDisc from '../components/shimo/GraphiteDisc.vue'
import WashRecord from '../components/shimo/WashRecord.vue'
import Statistics from '../components/shimo/Statistics'

Vue.use(VueRouter)

const routes = [
  { path: '/', redirect: '/home' },
  // { path: '/login', component: Login },
  {
    path: '/home',
    component: Home,
    redirect: '/welcome',
    children: [
      { path: '/welcome', component: Welcome },
      { path: '/shimo', component: GraphiteDisc },
      { path: '/wash', component: WashRecord },
      { path: '/statistics', component: Statistics }
    ]
  }
]

const router = new VueRouter({
  routes
})
export default router
