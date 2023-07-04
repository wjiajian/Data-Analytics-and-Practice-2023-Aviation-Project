package com.qrsoft.common;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class TokenVO implements Serializable {

    private static final long serialVersionUID = -5501706435587205188L;

    /**
     * token
     */
    private String token;

    /**
     * 用户ID
     */
    private String id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 用户名
     */
    private String account;

    /**
     * 用户类型
     */
    private Integer type;

    /**
     * 按钮权限列表
     */
    private List<AuthAndMenu> authList;

    /**
     * 菜单权限列表
     */
    private List<AuthAndMenu> menuList;

    private String Authorization;

    private String userAuth;
}