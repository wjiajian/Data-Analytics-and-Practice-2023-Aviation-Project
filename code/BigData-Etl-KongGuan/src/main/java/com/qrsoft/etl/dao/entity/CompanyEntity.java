package com.qrsoft.etl.dao.entity;

import java.io.Serializable;

public class CompanyEntity  implements Serializable {

    private Integer id;
    private String acid;
    private String companyCode3;
    private String companyName;
    private Integer delayCount;

    public String getAcid() {
        return acid;
    }

    public void setAcid(String acid) {
        this.acid = acid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompanyCode3() {
        return companyCode3;
    }

    public void setCompanyCode3(String companyCode3) {
        this.companyCode3 = companyCode3;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getDelayCount() {
        return delayCount;
    }

    public void setDelayCount(Integer delayCount) {
        this.delayCount = delayCount;
    }
}