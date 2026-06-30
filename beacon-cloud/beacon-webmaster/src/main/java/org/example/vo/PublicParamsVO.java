package org.example.vo;

import lombok.Data;

/**
 * PublicParamsVO - 公共参数VO
 * 字段名严格匹配前端 public_params.js
 * 映射：descripton←description（保持前端拼写错误!)
 */
@Data
public class PublicParamsVO {
    private Long id;
    private String paramName;
    private String paramType;
    private String createDate;
    private String descripton;
    private Integer isMust;
    private Integer enableState;
}
