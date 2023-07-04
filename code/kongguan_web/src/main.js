import Vue from 'vue'
import App from './App'
import router from './router'
import store from './store/index'
import './assets/css/basic.css'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import './assets/icon/iconfont.css';
import * as echarts from 'echarts';
import axios from 'axios';
import moment from 'moment';
import { message } from './utils/message'
import BaiduMap from 'vue-baidu-map'

Vue.prototype.$echarts = echarts;
Vue.prototype.$axios = axios;
Vue.config.productionTip = false
Vue.use(ElementUI);
Vue.prototype.$message = message;
Vue.prototype.$moment = moment;
Vue.use(BaiduMap,{
  ak:'POhv5Sb6vhUpkVE5E3GZyGGe7XsmQf3V'
});

/* eslint-disable no-new */
// new Vue({
//   el: '#app',
//   router,
//   store,
//   components: {App},
//   template: '<App/>'
// })
new Vue({
  router,
  store,
  render:h =>h(App)
}).$mount('#app')

//定义一个响应拦截器
axios.interceptors.response.use(function (config) {
  let status = config.code;
  //401，未登录，跳转到登录页
  if (status == 401) {
    console.log(401);
    router.push("/login");
  }
  //403，无权限，跳转到登录页
  if (status == 403) {
    console.log(403);
    router.push("/login");
  }
  return config
})

// request拦截器
axios.interceptors.request.use(config => {
  // 如果想请求可以重复发起，给在请求参数中加allowedRepeat：true  (后续会删除，不会发送给服务端)
  if (!config.data || !config.data.allowedRepeat) { // 如果不允许重复请求，开启拦截
    // todo: 1. 设置拦截 防止重复请求
    // 拦截重复请求(即当前正在进行的相同请求)
    const requestData = getRequestIdentify(config)
    removePending(requestData, true)
    // 使用 cancel token 取消请求 参考：http://www.axios-js.com/zh-cn/docs/#%E6%8B%A6%E6%88%AA%E5%99%A8
    config.cancelToken = new CancelToken((c) => {
      pending[requestData] = c
    })
  } else { // 允许重复请求，不进行拦截
    delete config.data.allowedRepeat // 把自定义的请求参数给删掉，不发送给服务端
  }
  return config
}, error => {
  // Do something with request error
  console.log(error) // for debug
  Promise.reject(error)
})

// 拦截重复请求
let pending = {}
const CancelToken = axios.CancelToken
// 请求标识；完成请求后也需要执行删除记录，所以添加此参数避免执行无用操作
const removePending = (key, isRequest = false) => {
  if (pending[key] && isRequest) {
    pending[key]('取消重复请求')
  }
  delete pending[key]
}
/**
 * 由于我们请求用了代理 直接代理到测试服务器 因此请求响应拦截器的config.url是一致的，不需要标识值区分
 * 如果请求拦截器和响应拦截器的config.url不一致，就需要一个标识值用来区分判断
 */
const getRequestIdentify = (config) => {
  const url = config.url
  // 返回url及请求参数 post方法请求参数为config.data  get方法请求参数为config.params
  if (config.method === 'post') {
    return encodeURIComponent(config.url + JSON.stringify(config.data))
  }
  return encodeURIComponent(url + JSON.stringify(config.params))
}

