package com.qrsoft.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("warnsimilarhistory_number")
public class WarnSimilarHistory implements Serializable {

    private Integer id;

    @TableField(value = "gj_type")
    private String gjType;

    @TableField(value = "gj_id")
    private String gjDd;

    @TableField(value = "gj_msg_type")
    private String gjMsgType;

    @TableField(value = "gj_num")
    private String gjNum;

    @TableField(value = "gj_track_num")
    private String gjTrackNum;

    @TableField(value = "gj_sector")
    private String gjSector;

    @TableField(value = "gj_acid")
    private String gjAcid;

    @TableField(value = "gj_status")
    private String gjStatus;

    @TableField(value = "gj_city")
    private String gjCity;

    @TableField(value = "gj_date")
    private String gjDate;

    @TableField(value = "count")
    private Integer count;

}