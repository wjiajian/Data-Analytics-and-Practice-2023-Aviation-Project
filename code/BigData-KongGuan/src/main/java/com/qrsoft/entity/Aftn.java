package com.qrsoft.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("kg_aftn")
//报文
public class Aftn implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "send_time")
    private String sendTime;

    @TableField(value = "area_source")
    private String areaSource;

    @TableField(value = "plan_source")
    private String planSource;

    @TableField(value = "acid")
    private String acid;

    @TableField(value = "adep")
    private String adep;

    @TableField(value = "ades")
    private String ades;

    @TableField(value = "stod")
    private String stod;

    @TableField(value = "stoa")
    private String stoa;

    @TableField(value = "aftn_status")
    private String aftnStatus;

    @TableField(value = "execute_date")
    private String executeDate;

    @TableField(value = "ssr_code")
    private String ssrCode;

    @TableField(value = "fly_type")
    private String flyType;

    @TableField(value = "aircraft_type")
    private String aircraftType;

    @TableField(value = "tail_flow")
    private String tailFlow;

    @TableField(value = "plan_height")
    private String planHeight;

    @TableField(value = "plan_speed")
    private String planSpeed;

    @TableField(exist = false)
    private String airCname;
    @TableField(exist = false)
    private Integer adepCount;
    @TableField(exist = false)
    private Integer adesCount;

}
