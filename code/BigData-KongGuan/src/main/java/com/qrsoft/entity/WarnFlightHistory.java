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
@TableName("warnflighthistory_number")
public class WarnFlightHistory implements Serializable {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    @TableField(value = "gj_type")
    private String gjType;

    @TableField(value = "gj_id")
    private String gjId;

    @TableField(value = "gj_msg_type")
    private String gjMsgType;

    @TableField(value = "gj_track_num1")
    private String gjTrackNum1;

    @TableField(value = "gj_track_num2")
    private String gjTrackNum2;

    @TableField(value = "gj_distinct")
    private String gjDistinct;

    @TableField(value = "gj_radian")
    private String gjRadian;

    @TableField(value = "gj_name")
    private String gjName;

    @TableField(value = "gj_distinct_bz")
    private String gjDistinctBz;

    @TableField(value = "gj_city")
    private String gjCity;

    @TableField(value = "gj_date")
    private String gjDate;

    @TableField(value = "count")
    private Integer count;

    @TableField(value = "gj_sector")
    private String gjSector;

    @TableField(exist = false)
    private String gjCount;

    //告警的两个航班号
    @TableField(exist = false)
    private  String gjACIds;
}