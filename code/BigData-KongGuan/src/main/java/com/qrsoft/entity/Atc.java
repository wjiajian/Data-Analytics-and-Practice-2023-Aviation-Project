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
@TableName("atc_number")
public class Atc implements Serializable {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    @TableField(value = "ACID")
    private String acId;

    @TableField(value = "ATC_TIME")
    private String atcTime;

    @TableField(value = "EXECUTE_DATE")
    private String executeDate;

    @TableField(value = "PLAN_SECTOR_NAME")
    private String planSectorName;

    @TableField(exist = false)
    private String count;
}
