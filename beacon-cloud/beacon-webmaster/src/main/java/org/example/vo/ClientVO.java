package org.example.vo;

import lombok.Data;

/**
 * ClientVO - 客户基础信息VO
 * 字段名严格匹配前端 client.js
 */
@Data
public class ClientVO {
    private Long id;
    private String corpname;
    private String address;
    private String linkman;
    private String mobile;
    private String email;
    private String customermanager;
}
