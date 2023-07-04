import Vue from 'vue'
import Router from 'vue-router'

const originalPush = Router.prototype.push;
Router.prototype.push = function push(location) {
    return originalPush.call(this, location).catch(err => err)
}
Vue.use(Router)

/* Layout */
import Layout from '@/views/Layout/Layout'

const router =  new Router({
  base: process.env.BASE_URL,
  mode: 'history',
  routes: [
    {
      path: "/login",
      component: resolve => require(['@/views/Login/Login'], resolve),
      hidden: true,
      meta: {
        auth: true
      }
    },
    {
      path: '/',
      component: Layout,
      redirect: '/home',
      children: [
        {
          path: 'home',
          component: resolve => require(['@/views/Home/Index'], resolve),
          name: 'home',
          meta: { title: 'home' }
        }
      ]
    },
    {
      path: '/',
      component: Layout,
      redirect: '/map',
      children: [
        {
          path: 'map',
          component: resolve => require(['@/views/Home/Map'], resolve),
          name: 'map',
          meta: { title: 'map' }
        }
      ]
    },
  ]
})
router.beforeEach((to, from, next) => {
  if (to.path === '/login') {
    next();
  } else {
    let token = localStorage.getItem('Authorization');
    
    if (token === null || token === '') {
      next('/login');
    } else {
      next();
    }
  }
});
export default router