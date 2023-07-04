import request from '../../utils/request'

const baseUrl="/api"

/**
 * 用户登录
 */
export function login(data){
  return request({
    url:baseUrl+"/login",
    method:"post",
    data:data
  })
}