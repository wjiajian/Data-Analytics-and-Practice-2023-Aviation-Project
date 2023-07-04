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
@TableName("callsaturation_number")
public class Callsaturation implements Serializable {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    @TableField(value = "SEND_TIME")
    private String sendTime;

    @TableField(value = "AREA_SOURCE")
    private String areaSource;

    @TableField(value = "THBH_TIME")
    private String thbhTime;

    @TableField(value = "THBH_SECT")
    private String thbhSect;

    @TableField(value = "THBH_VALUE")
    private String thbhValue;
}
