package org.example.vo;

import lombok.Data;

import java.util.Date;

/**
 * ScheduleJobVO - 定时任务VO
 * 字段名严格匹配前端 job.js
 */
@Data
public class ScheduleJobVO {
    private Long jobId;
    private String beanName;
    private String methodName;
    private String params;
    private String cronExpression;
    private String remark;
    private Integer status;
    private Date createTime;
}
