package com.qrsoft.etl.dao.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author Colonnello
 * @Date 2021/3/29 10:49
 * @ClassName
 */
@Data
public class AFTNEntity implements Serializable {


    private Integer id;

    private String sendTime;

    private String areaSource;

    private String planSource;

    private String acid;

    private String adep;

    private String ades;

    private String stod;

    private String stoa;

    private String aftnStatus;

    private String executeDate;

    private String ssrCode;

    private String flyType;

    private String aircraftType;

    private String tailFlow;

    private String planHeight;

    private String planSpeed;

}

