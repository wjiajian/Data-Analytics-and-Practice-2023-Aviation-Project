package com.qrsoft.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("multiradar_number")
public class MultiRadar implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @TableField(value = "TRACK_NUMBER")
    private String trackNumber;
    @TableField(value = "AREA_SOURCE")
    private String areaSource;
    @TableField(value = "SEND_RADAR_TIME")
    private String sendRadarTime;
    @TableField(value = "RADAR_TYPE")
    private String radarType;
    @TableField(value = "ACID")
    private String acid;
    @TableField(value = "SSR_CODE")
    private String ssrCode;
    @TableField(value = "ZHIJIAO_X")
    private String zhijiaoX;
    @TableField(value = "ZHIJIAO_Y")
    private String zhijiaoY;
    @TableField(value = "RADAR_LONGTITUDE")
    private String radarLongtitude;
    @TableField(value = "RADAR_LATITUDE")
    private String radarLatitude;
    @TableField(value = "RADAR_HEIGHT")
    private String radarHeight;
    @TableField(value = "SPEED_X")
    private String speedX;
    @TableField(value = "SPEED_Y")
    private String speedY;
    @TableField(value = "RADAR_SPEED")
    private String radarSpeed;
    @TableField(value = "DIRECTION")
    private String direction;
    @TableField(value = "RADAR_CFL")
    private String radarCfl;
    @TableField(value = "FCU")
    private String fcu;
    @TableField(value = "TIME")
    private String time;
    @TableField(value = "FLYSTATUS")
    private String flystatus;
    @TableField(value = "CLIMBORDOWN_SPEED")
    private String climbordownSpeed;
}