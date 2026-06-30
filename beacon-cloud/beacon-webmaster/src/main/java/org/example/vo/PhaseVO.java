package org.example.vo;

import lombok.Data;

/**
 * PhaseVO - 号段配置VO
 * 字段名严格匹配前端 phase.js
 */
@Data
public class PhaseVO {
    private Long id;
    private String phase;
    private Long provId;
    private Long cityId;
    private String provName;
    private String cityName;
}
