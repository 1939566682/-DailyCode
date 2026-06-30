package org.example.vo;

import lombok.Data;

/**
 * SmsSendVO - 短信发送VO
 * 字段名匹配前端 smssend.js
 */
@Data
public class SmsSendVO {
    private Long id;
    private String parentName;
    private Long parentId;
    private Integer type;
    private Integer orderNum;
}
