package org.example.vo;

import lombok.Data;

/**
 * SmsTempVO - 短信模板VO
 * 字段名严格匹配前端 smstemp.js
 */
@Data
public class SmsTempVO {
    private Long id;
    private String template;
    private String paramter;
    private String creater;
    private Integer owntype;
    private Integer status;
}
