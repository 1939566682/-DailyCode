package org.example.entity;

import java.io.Serializable;

/**
 * SmsRoleMenu - 角色菜单关联实体
 * 对应表: sms_role_menu
 * TODO: 需确认该表是否已创建
 * 建表SQL:
 * CREATE TABLE sms_role_menu (
 *   role_id BIGINT NOT NULL,
 *   menu_id BIGINT NOT NULL,
 *   PRIMARY KEY (role_id, menu_id)
 * ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色菜单关联表';
 */
public class SmsRoleMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long roleId;

    private Long menuId;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }
}
