package com.qrsoft.etl.util;

public class MapManager {
    /**
     * 是否在矩形区域内
     * @Title: isInArea
     * @Description: TODO()
     * @param lat 测试点经度
     * @param lng 测试点纬度
     * @param minLat 纬度范围限制1
     * @param maxLat 纬度范围限制2
     * @param minLng 经度限制范围1
     * @param maxLng 经度范围限制2
     * @return boolean
     */
    public  boolean isInRectangleArea(double lat,double lng,double minLat,
                                      double maxLat,double minLng,double maxLng){
        if(this.isInRange(lat, minLat, maxLat)){//如果在纬度的范围内
            if(minLng*maxLng>0){
                if(this.isInRange(lng, minLng, maxLng)){
                    return true;
                }else {
                    return false;
                }
            }else {
                if(Math.abs(minLng)+Math.abs(maxLng)<180){
                    if(this.isInRange(lng, minLng, maxLng)){
                        return true;
                    }else {
                        return false;
                    }
                }else{
                    double left = Math.max(minLng, maxLng);
                    double right = Math.min(minLng, maxLng);
                    if(this.isInRange(lng, left, 180)||this.isInRange(lng, right,-180)){
                        return true;
                    }else {
                        return false;
                    }
                }
            }
        }else{
            return false;
        }
    }
    /**
     * 判断是否在经纬度范围内
     */
    public  boolean isInRange(double point, double left,double right){
        if(point>=Math.min(left, right)&&point<=Math.max(left, right)){
            return true;
        }else {
            return false;
        }
    }
}