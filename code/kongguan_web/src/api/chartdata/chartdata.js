import request from '../../utils/request'

const baseUrl="/api"

/**
 * 扇区架次数动态统计
 */
 export function findATCTime() {
  return request({
    url: baseUrl + "/atc/findATCTime",
    method: "GET"
  })
}
/**
 * 获取各个扇区通话饱和度
 */
export function findCallSaturation() {
  return request({
    url: baseUrl + "/callSaturation/findCallSaturation",
    method: "GET"
  })
}
/**
* 年度告警分类统计
*/
export function annualWarningStatisticsByCategory() {
  return request({
    url: baseUrl + "/warnFlightHistory/annualWarningStatisticsByCategory",
    method: "GET"
  })
}
/**
* 机场当前负荷统计
*/
export function getAirPortCount() {
  return request({
    url: baseUrl + "/company/getAirPortCount",
    method: "GET"
  })
}

/**
 * 获取从青岛起飞航班数前十的航线
 * @returns {AxiosPromise}
 */
 export function findByLimit() {
    return request({
      url: baseUrl + "/airLine/findByLimit",
      method: "GET"
    })
  }