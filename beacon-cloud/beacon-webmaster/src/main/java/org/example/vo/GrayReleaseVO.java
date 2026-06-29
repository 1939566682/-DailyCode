package org.example.vo;

import lombok.Data;

/**
 * GrayReleaseVO - 灰度发布VO
 * 字段名严格匹配前端 garyrelease.js（注意拼写garyrelease）
 */
@Data
public class GrayReleaseVO {
    private Long id;
    private String serviceId;
    private String path;
    private Integer percent;
    private Integer forward;
    private Integer state;
}
