package com.qrsoft.etl.dao.entity;

import java.io.Serializable;

public class ATCEntity  implements Serializable {

    private Integer id;
    //
    private String acId;
    //
    private String atcTime;
    //
    private String executeDate;
    //
    private String planSectorName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAcId() {
        return acId;
    }

    public void setAcId(String acId) {
        this.acId = acId;
    }

    public String getAtcTime() {
        return atcTime;
    }

    public void setAtcTime(String atcTime) {
        this.atcTime = atcTime;
    }

    public String getExecuteDate() {
        return executeDate;
    }

    public void setExecuteDate(String executeDate) {
        this.executeDate = executeDate;
    }

    public String getPlanSectorName() {
        return planSectorName;
    }

    public void setPlanSectorName(String planSectorName) {
        this.planSectorName = planSectorName;
    }
}

