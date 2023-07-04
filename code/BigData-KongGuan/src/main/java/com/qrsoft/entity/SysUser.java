package com.qrsoft.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户基础
 */
@Data
@TableName("sys_user")
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户账号
     */
    @TableField("account")
    private String account;

    /**
     * 用户密码
     */
    @TableField("password")
    private String password;

    /**
     * 用户姓名
     */
    @TableField("name")
    private String name;

    /**
     * 联系方式
     */
    @TableField("contact")
    private String contact;

    /**
     * 用户类型（0：质检员(管理员)；1：司机；2：客户）
     */
    @TableField("type")
    private Integer type = 0;

    /**
     * 司机id/客户id
     */
    @TableField("user_type_id")
    private Integer userTypeId;

    /**
     * 是否启用（启用：0，未启用：1）
     */
    @TableField("is_enable")
    private Integer isEnable = 0;

    /**
     * 是否删除（正常：0，删除：1）
     */
    @TableField("is_del")
    private Integer isDel = 0;

    /**
     * 创建人
     */
    @TableField(value = "create_user", select = false)
    private Integer createUser;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", select = false)
    private Date createTime;
}
