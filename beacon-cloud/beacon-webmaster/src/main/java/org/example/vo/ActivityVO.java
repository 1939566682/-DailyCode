package org.example.vo;

import lombok.Data;

/**
 * ActivityVO - 活动VO
 * 字段名严格匹配前端 activity.js
 */
@Data
public class ActivityVO {
    private Long id;
    private String title;
    private String author;
    private String beginTime;
    private String endTime;
    private String link;
    private String coverPic;
}
