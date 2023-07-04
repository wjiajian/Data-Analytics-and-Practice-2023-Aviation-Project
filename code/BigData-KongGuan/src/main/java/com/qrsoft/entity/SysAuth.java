package com.qrsoft.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 权限表
 */
@Data
@TableName("sys_auth")
public class SysAuth implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 权限名称
     */
    @TableField("auth_name")
    private String authName;

    /**
     * 权限编码
     */
    @TableField("auth_code")
    private String authCode;

    /**
     * 权限类型（0：按钮；1，菜单）
     */
    @TableField("type")
    private Integer type = 0;

    /**
     * 菜单Url
     */
    @TableField("menu_url")
    private String menuUrl;

    /**
     * 父级id
     */
    @TableField("parent_id")
    private Integer parentId;

    /**
     * 菜单图标
     */
    @TableField("menu_icon")
    private String menuIcon;

    /**
     * 菜单顺序
     */
    @TableField("menu_order")
    private Integer menuOrder;

    /**
     * 删除状态，0正常，1删除
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