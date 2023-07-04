import request from '../../utils/request'
const baseUrl="/api"

//获取年度告警统计信息
export function getAnnualWarningAreaStatistics(data){
  return request({
    url:baseUrl+"/warnFlightHistory/annualWarningAreaStatistics",
    method:"get",
    data:data
  })
}

//获取指挥航空公司架次与延误率占比
export function getFindCompanyDelay(data){
  return request({
    url:baseUrl+"/company/findCompanyDelay",
    method:"get",
    data:data
  })
}

//获取各扇区航班数
export function getSectionVal(data){
  return request({
    url:baseUrl+"/atc/findSectorSortie",
    method:"get",
    data:data
  })
}
