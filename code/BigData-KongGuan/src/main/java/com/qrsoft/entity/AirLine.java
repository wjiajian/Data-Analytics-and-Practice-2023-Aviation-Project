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
@TableName("airline_number")
public class AirLine implements Serializable {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    @TableField(value = "acid")
    private String acid;

    @TableField(value = "adepcode")
    private String adepcode;

    @TableField(value = "adescode")
    private String adescode;

    @TableField(value = "acids")
    private String acids;

    @TableField(value = "adepname")
    private String adepname;

    @TableField(value = "adesname")
    private String adesname;

    @TableField(value = "adeplong")
    private String adeplong;

    @TableField(value = "adeplat")
    private String adeplat;

    @TableField(value = "adeslong")
    private String adeslong;

    @TableField(value = "adeslat")
    private String adeslat;

    @TableField(value = "count")
    private String count;
}
