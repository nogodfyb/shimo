import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../components/Login.vue'
import Home from '../components/Home.vue'
import Welcome from '../components/Welcome.vue'
import Users from '../components/user/users.vue'
import Rights from '../components/power/Rights.vue'
import Roles from '../components/power/Roles.vue'
import Machines from '../components/machine/Machines.vue'
import Products from '../components/machine/Products.vue'
import Productivitys from '../components/machine/Productivitys.vue'
import Plans from '../components/plan/Plans.vue'
import MachinePlans from '../components/plan/MachinePlans.vue'
import AssignPlans from '../components/plan/AssignPlans.vue'
import GraphiteDisc from '../components/shimo/GraphiteDisc.vue'

Vue.use(VueRouter)

const routes = [
  { path: '/', redirect: '/home' },
  { path: '/login', component: Login },
  {
    path: '/home',
    component: Home,
    redirect: '/welcome',
    children: [
      { path: '/welcome', component: Welcome },
      { path: '/users', component: Users },
      { path: '/rights', component: Rights },
      { path: '/roles', component: Roles },
      { path: '/machines', component: Machines },
      { path: '/products', component: Products },
      { path: '/productivitys', component: Productivitys },
      { path: '/plans', component: Plans },
      { path: '/machinePlans', component: MachinePlans },
      { path: '/assignPlans', component: AssignPlans },
      { path: '/shimo', component: GraphiteDisc }
    ]
  }
]

const router = new VueRouter({
  routes
})
export default router
