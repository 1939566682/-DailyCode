package org.example.vo;

import lombok.Data;

/**
 * NotifyVO - 通知配置VO
 * 字段名严格匹配前端 notify.js
 */
@Data
public class NotifyVO {
    private Long id;
    private String tag;
    private String desp;
    private Integer notifyState;
    private Integer cacheState;
}
