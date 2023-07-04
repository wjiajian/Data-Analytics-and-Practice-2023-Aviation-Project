package com.qrsoft.etl.dao.entity;
import java.io.Serializable;

public class WarnFlightHistoryEntity implements Serializable {


    //告警类型
    private String gjType;
    //告警的唯一标示编号
    private String gjId;
    //告警的类型，0:新增告警，1:删除告警，2：更新告警
    private String gjMsgType;
    //航迹号1
    private String gjTrackNum1;
    //航迹号2
    private String gjTrackNum2;
    //告警航班之间的距离
    private String gjDistinct;
    //告警航班之间的夹角
    private String gjRadian;
    //告警的名称
    private String gjName;
    //告警的距离参数
    private String gjDistinctBz;
    //告警的城市
    private String gjCity;
    //告警发生的时间
    private String gjDate;
    //此告警数量
    private Integer count;
    //告警的扇区
    private String gjSector;

    public WarnFlightHistoryEntity() {
    }

    public WarnFlightHistoryEntity(String gjType, String gjId, String gjMsgType, String gjTrackNum1, String gjTrackNum2, String gjDistinct, String gjRadian, String gjName, String gjDistinctBz, String gjCity, String gjDate, Integer count, String gjSector) {
        this.gjType = gjType;
        this.gjId = gjId;
        this.gjMsgType = gjMsgType;
        this.gjTrackNum1 = gjTrackNum1;
        this.gjTrackNum2 = gjTrackNum2;
        this.gjDistinct = gjDistinct;
        this.gjRadian = gjRadian;
        this.gjName = gjName;
        this.gjDistinctBz = gjDistinctBz;
        this.gjCity = gjCity;
        this.gjDate = gjDate;
        this.count = count;
        this.gjSector = gjSector;
    }

    public String getGjType() {
        return gjType;
    }

    public void setGjType(String gjType) {
        this.gjType = gjType;
    }

    public String getGjId() {
        return gjId;
    }

    public void setGjId(String gjId) {
        this.gjId = gjId;
    }

    public String getGjMsgType() {
        return gjMsgType;
    }

    public void setGjMsgType(String gjMsgType) {
        this.gjMsgType = gjMsgType;
    }

    public String getGjTrackNum1() {
        return gjTrackNum1;
    }

    public void setGjTrackNum1(String gjTrackNum1) {
        this.gjTrackNum1 = gjTrackNum1;
    }

    public String getGjTrackNum2() {
        return gjTrackNum2;
    }

    public void setGjTrackNum2(String gjTrackNum2) {
        this.gjTrackNum2 = gjTrackNum2;
    }

    public String getGjDistinct() {
        return gjDistinct;
    }

    public void setGjDistinct(String gjDistinct) {
        this.gjDistinct = gjDistinct;
    }

    public String getGjRadian() {
        return gjRadian;
    }

    public void setGjRadian(String gjRadian) {
        this.gjRadian = gjRadian;
    }

    public String getGjName() {
        return gjName;
    }

    public void setGjName(String gjName) {
        this.gjName = gjName;
    }

    public String getGjDistinctBz() {
        return gjDistinctBz;
    }

    public void setGjDistinctBz(String gjDistinctBz) {
        this.gjDistinctBz = gjDistinctBz;
    }

    public String getGjCity() {
        return gjCity;
    }

    public void setGjCity(String gjCity) {
        this.gjCity = gjCity;
    }

    public String getGjDate() {
        return gjDate;
    }

    public void setGjDate(String gjDate) {
        this.gjDate = gjDate;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getGjSector() {
        return gjSector;
    }

    public void setGjSector(String gjSector) {
        this.gjSector = gjSector;
    }
}
