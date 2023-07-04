package com.qrsoft.etl.dao.entity;
import java.io.Serializable;

public class CallSaturationEntity implements Serializable {
    private Integer id;
    private String sendTime;
    private String areaSource;
    private String thbhTime;
    private String thbhSect;
    private String thbhValue;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getAreaSource() {
        return areaSource;
    }

    public void setAreaSource(String areaSource) {
        this.areaSource = areaSource;
    }

    public String getThbhTime() {
        return thbhTime;
    }

    public void setThbhTime(String thbhTime) {
        this.thbhTime = thbhTime;
    }

    public String getThbhSect() {
        return thbhSect;
    }

    public void setThbhSect(String thbhSect) {
        this.thbhSect = thbhSect;
    }

    public String getThbhValue() {
        return thbhValue;
    }

    public void setThbhValue(String thbhValue) {
        this.thbhValue = thbhValue;
    }

}
