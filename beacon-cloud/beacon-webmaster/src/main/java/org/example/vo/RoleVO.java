package org.example.vo;

import lombok.Data;

/**
 * RoleVO - 角色信息VO
 * 字段名严格匹配前端 role.js
 * extend1 → status, extend2 → remark
 */
@Data
public class RoleVO {
    private Long id;
    private String name;
    private Integer status;
    private String remark;
}
