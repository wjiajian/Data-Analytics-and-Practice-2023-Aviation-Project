package com.qrsoft.etl.dao.entity;

import java.io.Serializable;

public class WarnSimilarHistoryEntity implements Serializable {

    //告警类型
    private String gjType;
    //告警的唯一标示编号
    private String gjId;
    //告警的类型，0:新增告警，1:删除告警，2：更新告警
    private String gjMsgType;
    //航迹号个数
    private String gjNum;
    //航迹号串
    private String gjTrackNum;
    //告警的扇区
    private String gjSector;
    //告警的航班号
    private String gjAcid;
    //告警的状态
    private String gjStatus;
    //告警的城市
    private String gjCity;
    //告警发生的时间
    private String gjDate;
    //此告警数量
    private Integer count;

    public WarnSimilarHistoryEntity() {
    }

    public WarnSimilarHistoryEntity(String gjType, String gjId, String gjMsgType, String gjNum, String gjTrackNum, String gjSector, String gjAcid, String gjStatus, String gjCity, String gjDate, Integer count) {
        this.gjType = gjType;
        this.gjId = gjId;
        this.gjMsgType = gjMsgType;
        this.gjNum = gjNum;
        this.gjTrackNum = gjTrackNum;
        this.gjSector = gjSector;
        this.gjAcid = gjAcid;
        this.gjStatus = gjStatus;
        this.gjCity = gjCity;
        this.gjDate = gjDate;
        this.count = count;
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

    public String getGjNum() {
        return gjNum;
    }

    public void setGjNum(String gjNum) {
        this.gjNum = gjNum;
    }

    public String getGjTrackNum() {
        return gjTrackNum;
    }

    public void setGjTrackNum(String gjTrackNum) {
        this.gjTrackNum = gjTrackNum;
    }

    public String getGjSector() {
        return gjSector;
    }

    public void setGjSector(String gjSector) {
        this.gjSector = gjSector;
    }

    public String getGjAcid() {
        return gjAcid;
    }

    public void setGjAcid(String gjAcid) {
        this.gjAcid = gjAcid;
    }

    public String getGjStatus() {
        return gjStatus;
    }

    public void setGjStatus(String gjStatus) {
        this.gjStatus = gjStatus;
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
}
