package com.qrsoft.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author Colonnello
 * @Gitee https://gitee.com/niuzhengchao
 * @Date 2021/3/15 9:41
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("company_number")
public class Company implements Serializable {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    @TableField(value = "acid")
    private String acid;

    @TableField(value = "company_code3")
    private String companyCode3;

    @TableField(value = "company_name")
    private String companyName;

    @TableField(value = "delay_count")
    private String delayCount;

    @TableField(exist = false)
    private String count;

    @TableField(exist = false)
    private Double companyCount;
}
