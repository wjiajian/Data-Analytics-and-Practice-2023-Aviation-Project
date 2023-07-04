package com.qrsoft.etl.dao.entity;

import java.io.Serializable;

public class MultiRadarEntity implements Serializable {

    //哪个地区的综合航迹
    private String areaSource;
    //发送时间
    private String sendRadarTime;
    //雷达种类
    private String radarType;
    //航班号
    private String acid;
    //航迹号
    private String trackNumber;
    //二次代码
    private String ssrCode;
    //直角坐标X分量
    private String zhiJiaoX;
    //直角坐标Y分量
    private String zhiJiaoY;
    //小数形式的经度
    private String radarLongTitude;
    //小数形式的纬度
    private String radarLatitude;
    //高度
    private String radarHeight;
    //X分量速度
    private String speedX;
    //Y分量速度
    private String speedY;
    //速度
    private String radarSpeed;
    //航向
    private String direction;
    //指令高度
    private String radarCFL;
    //飞行员报告高度
    private String fcu;
    //时间戳
    private String time;
    //飞行状态
    private String flyStatus;
    //爬升/下降速度
    private String climbordownSpeed;

    //扇区
    private String section;

    public MultiRadarEntity() {
    }

    public MultiRadarEntity(String areaSource, String sendRadarTime, String radarType, String acid, String trackNumber, String ssrCode, String zhiJiaoX, String zhiJiaoY, String radarLongTitude, String radarLatitude, String radarHeight, String speedX, String speedY, String radarSpeed, String direction, String radarCFL, String fcu, String time, String flyStatus, String climbordownSpeed,String section) {
        this.areaSource = areaSource;
        this.sendRadarTime = sendRadarTime;
        this.radarType = radarType;
        this.acid = acid;
        this.trackNumber = trackNumber;
        this.ssrCode = ssrCode;
        this.zhiJiaoX = zhiJiaoX;
        this.zhiJiaoY = zhiJiaoY;
        this.radarLongTitude = radarLongTitude;
        this.radarLatitude = radarLatitude;
        this.radarHeight = radarHeight;
        this.speedX = speedX;
        this.speedY = speedY;
        this.radarSpeed = radarSpeed;
        this.direction = direction;
        this.radarCFL = radarCFL;
        this.fcu = fcu;
        this.time = time;
        this.flyStatus = flyStatus;
        this.climbordownSpeed = climbordownSpeed;
        this.section = section;
    }

    public String getAreaSource() {
        return areaSource;
    }

    public void setAreaSource(String areaSource) {
        this.areaSource = areaSource;
    }

    public String getSendRadarTime() {
        return sendRadarTime;
    }

    public void setSendRadarTime(String sendRadarTime) {
        this.sendRadarTime = sendRadarTime;
    }

    public String getRadarType() {
        return radarType;
    }

    public void setRadarType(String radarType) {
        this.radarType = radarType;
    }

    public String getAcid() {
        return acid;
    }

    public void setAcid(String acid) {
        this.acid = acid;
    }

    public String getTrackNumber() {
        return trackNumber;
    }

    public void setTrackNumber(String trackNumber) {
        this.trackNumber = trackNumber;
    }

    public String getSsrCode() {
        return ssrCode;
    }

    public void setSsrCode(String ssrCode) {
        this.ssrCode = ssrCode;
    }

    public String getZhiJiaoX() {
        return zhiJiaoX;
    }

    public void setZhiJiaoX(String zhiJiaoX) {
        this.zhiJiaoX = zhiJiaoX;
    }

    public String getZhiJiaoY() {
        return zhiJiaoY;
    }

    public void setZhiJiaoY(String zhiJiaoY) {
        this.zhiJiaoY = zhiJiaoY;
    }

    public String getRadarLongTitude() {
        return radarLongTitude;
    }

    public void setRadarLongTitude(String radarLongTitude) {
        this.radarLongTitude = radarLongTitude;
    }

    public String getRadarLatitude() {
        return radarLatitude;
    }

    public void setRadarLatitude(String radarLatitude) {
        this.radarLatitude = radarLatitude;
    }

    public String getRadarHeight() {
        return radarHeight;
    }

    public void setRadarHeight(String radarHeight) {
        this.radarHeight = radarHeight;
    }

    public String getSpeedX() {
        return speedX;
    }

    public void setSpeedX(String speedX) {
        this.speedX = speedX;
    }

    public String getSpeedY() {
        return speedY;
    }

    public void setSpeedY(String speedY) {
        this.speedY = speedY;
    }

    public String getRadarSpeed() {
        return radarSpeed;
    }

    public void setRadarSpeed(String radarSpeed) {
        this.radarSpeed = radarSpeed;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getRadarCFL() {
        return radarCFL;
    }

    public void setRadarCFL(String radarCFL) {
        this.radarCFL = radarCFL;
    }

    public String getFcu() {
        return fcu;
    }

    public void setFcu(String fcu) {
        this.fcu = fcu;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getFlyStatus() {
        return flyStatus;
    }

    public void setFlyStatus(String flyStatus) {
        this.flyStatus = flyStatus;
    }

    public String getClimbordownSpeed() {
        return climbordownSpeed;
    }

    public void setClimbordownSpeed(String climbordownSpeed) {
        this.climbordownSpeed = climbordownSpeed;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }
}
