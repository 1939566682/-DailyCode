package org.example.vo;

import lombok.Data;

/**
 * ApiMappingVO - API映射VO
 * 字段名匹配前端 api.js
 * 注意前端有 gatewayApiName、serviceId、insideApiUrl 等字段
 * 映射：gatewayApiName←sourcePath, serviceId←extend1, insideApiUrl←targetPath
 */
@Data
public class ApiMappingVO {
    private Long id;
    private String gatewayApiName;
    private String serviceId;
    private String insideApiUrl;
    private String sourcePath;
    private String targetPath;
    private String method;
    private Integer state;
    private String description;
    private String createDate;
}
