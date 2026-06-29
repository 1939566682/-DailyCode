package org.example.vo;

import lombok.Data;

/**
 * StrategyFilterVO - 策略过滤器VO
 * 字段名严格匹配前端 stragetyfilter.js（注意拼写stragety）
 * VO与Entity字段同名
 */
@Data
public class StrategyFilterVO {
    private Long id;
    private String filters;
    private Integer filterState;
}
