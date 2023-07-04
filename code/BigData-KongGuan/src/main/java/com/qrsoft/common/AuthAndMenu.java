package com.qrsoft.common;

import com.qrsoft.entity.SysAuth;
import lombok.Data;

import java.util.List;

@Data
public class AuthAndMenu {

    /**
     * id
     */
    private Integer id;

    /**
     * 权限名称
     */
    private String authName;

    /**
     * 权限编码
     */
    private String authCode;

    /**
     * 权限类型（0：按钮；1，菜单）
     */
    private Integer type;

    /**
     * 菜单Url
     */
    private String menuUrl;

    /**
     * 菜单Icon
     */
    private String menuIcon;

    /**
     * 菜单order
     */
    private Integer menuOrder;

    /**
     *  子菜单
     */
    private List<AuthAndMenu> childs;

    /**
     * 父级id
     */
    private Integer parentId;

    public static AuthAndMenu authTOAuth(SysAuth auth) {
        AuthAndMenu output = new AuthAndMenu();
        output.setId(auth.getId());
        output.setAuthName(auth.getAuthName());
        output.setAuthCode(auth.getAuthCode());
        output.setType(0);
        output.setParentId(auth.getParentId());
        output.setMenuUrl(auth.getMenuUrl());
        output.setMenuIcon(auth.getMenuIcon());
        output.setMenuOrder(auth.getMenuOrder());
        return output;
    }

    public static AuthAndMenu authTOMenu(SysAuth auth) {
        AuthAndMenu output = new AuthAndMenu();
        output.setId(auth.getId());
        output.setAuthName(auth.getAuthName());
        output.setType(1);
        output.setParentId(auth.getParentId());
        output.setMenuUrl(auth.getMenuUrl());
        output.setMenuIcon(auth.getMenuIcon());
        output.setMenuOrder(auth.getMenuOrder());
        return output;
    }
}

