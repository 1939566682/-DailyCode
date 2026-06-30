package org.example.vo;

import lombok.Data;

/**
 * ApiGatewayFilterVO - API网关过滤器VO
 * 字段名严格匹配前端 apigatewayfilter.js
 * VO与Entity字段同名
 */
@Data
public class ApiGatewayFilterVO {
    private Long id;
    private String filters;
    private Integer filterState;
}
