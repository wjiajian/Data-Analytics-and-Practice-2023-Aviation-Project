import request from "../../utils/request";

//综合航迹数据查询相关的服务器端请求的根路径
const baseUrl="/api/multiRadar"
//年度统计查询相关的服务器端请求的根路径
const warUrl ="/api/warnFlightHistory"
//航班告警查询相关的服务器端请求的根路径
const warSimUrl = "/api/warnSimilarHistory"
//扇区操作查询相关的服务器端请求的根路径
const atcUrl = "/api/atc"

//查询综合航迹数据
export function findMultRadar(){
  return request({
    url:baseUrl+"/findMultRadar",
    method: "get",
  })
}
//管制指令纠错
export function findWarnTp(){
  return request({
    url:warUrl+"/findWarnTp",
    method: "get",
  })
}
//根据扇区名称获取该扇区航班数
export function findLocusCount(data){
  return request({
    url:atcUrl+"/findLocusCount?planSectorName="+data,
    method: "get",
  })
}
//根据扇区号查询相似航班
export function findWarnSimilarOfATC(data){
  return request({
    url:warSimUrl+"/findWarnSimilarOfATC?sectorName="+data,
    method: "get",
  })
}
//根据扇区号查询相似航班告警总数
export function findWarnSimilarOfATCCount(data){
  return request({
    url:warSimUrl+"/findWarnSimilarOfATCCount?sectorName="+data,
    method: "get",
  })
}