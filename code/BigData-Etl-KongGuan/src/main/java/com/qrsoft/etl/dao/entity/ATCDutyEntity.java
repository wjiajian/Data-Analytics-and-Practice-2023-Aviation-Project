package com.qrsoft.etl.dao.entity;
import java.io.Serializable;

public class ATCDutyEntity implements Serializable {

    private String sendTime;
    private String areaSource;
    private String atcDutyPosition;
    private String atcDutyPerson;
    private String atcDutySector;
    private Integer count;

    public ATCDutyEntity() {
    }

    public ATCDutyEntity(String sendTime, String areaSource, String atcDutyPosition, String atcDutyPerson, String atcDutySector, Integer count) {
        this.sendTime = sendTime;
        this.areaSource = areaSource;
        this.atcDutyPosition = atcDutyPosition;
        this.atcDutyPerson = atcDutyPerson;
        this.atcDutySector = atcDutySector;
        this.count = count;
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

    public String getAtcDutyPosition() {
        return atcDutyPosition;
    }

    public void setAtcDutyPosition(String atcDutyPosition) {
        this.atcDutyPosition = atcDutyPosition;
    }

    public String getAtcDutyPerson() {
        return atcDutyPerson;
    }

    public void setAtcDutyPerson(String atcDutyPerson) {
        this.atcDutyPerson = atcDutyPerson;
    }

    public String getAtcDutySector() {
        return atcDutySector;
    }

    public void setAtcDutySector(String atcDutySector) {
        this.atcDutySector = atcDutySector;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}

