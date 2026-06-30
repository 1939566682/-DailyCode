package org.example.vo;

import lombok.Data;

/**
 * SearchParamsVO - 搜索参数VO
 * 字段名严格匹配前端 searchparams.js
 * 映射：cloum←columnName（保持前端拼写错误!)
 */
@Data
public class SearchParamsVO {
    private Long id;
    private String name;
    private String cloum;
    private Integer type;
    private Integer tOrder;
    private Integer state;
}
