package org.example.vo;

import lombok.Data;

import java.util.Date;

/**
 * ScheduleLogVO - 定时任务日志VO
 * 字段名严格匹配前端 log.js
 */
@Data
public class ScheduleLogVO {
    private Long logId;
    private Long jobId;
    private String beanName;
    private String methodName;
    private String params;
    private Integer status;
    private String error;
    private Integer times;
    private Date createTime;
}
