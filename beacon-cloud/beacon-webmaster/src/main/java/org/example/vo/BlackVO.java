package org.example.vo;

import lombok.Data;

/**
 * BlackVO - 黑名单VO
 * 字段名严格匹配前端 blacklist.js
 * 映射：mobile←blackNumber, owntype←clientId(0映射为"全局"), creater←联查client_business的corpname
 */
@Data
public class BlackVO {
    private Long id;
    private String mobile;
    private String owntype;
    private String creater;
    private Integer clientId;
}
