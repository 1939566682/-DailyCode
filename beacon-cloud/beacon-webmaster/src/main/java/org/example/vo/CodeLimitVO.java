package org.example.vo;

import lombok.Data;

/**
 * CodeLimitVO - 验证码限流VO
 * 字段名严格匹配前端 limit.js
 * 列表用: id / limitTime / limitCount / despcription / limitState
 */
@Data
public class CodeLimitVO {
    private Long id;
    private Integer limitTime;
    private Integer limitCount;
    private String despcription;
    /**
     * 启用状态：0-停用 1-启用
     * 注意：code_limit 表中本无此列，此处保留 0/1 默认值以兼容前端表格
     */
    private Integer limitState;
}
