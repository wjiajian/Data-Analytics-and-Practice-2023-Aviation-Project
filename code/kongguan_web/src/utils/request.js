import Vue from 'vue'
import axios from 'axios'

Vue.prototype.$ajax = Vue.ajax = axios

axios.defaults.baseURL = process.env.NODE_ENV !== 'production' ? "" : (process.env.VUE_APP_BASE_API)

axios.interceptors.request.use(config => {
  if (localStorage.getItem('Authorization')) {
    config.headers.common['Authorization'] = localStorage.getItem('Authorization');
  } else {

  }
  return config;
}, err => {
  return Promise.reject(err);
})
axios.interceptors.response.use(function (response) {
  // console.log(response);
  // console.log(response.headers.Authorization);
  // 对响应数据做点什么
  return response.data;
}, function (error) {
  if (JSON.stringify(error).indexOf('401') !== -1) { // token失效
    localStorage.removeItem('Authorization')
    Vue.prototype.$message.error("token过期")
    Vue.prototype.$router.push({
      path: '/login',
    })
  } else if (JSON.stringify(error).indexOf('403') !== -1) {
    localStorage.removeItem('Authorization')
    Vue.prototype.$message.error("token无效")
    Vue.prototype.$router.push({
      path: '/login',
    })
  }
  // 对响应错误做点什么
  return Promise.reject(error);
});

export default axios;