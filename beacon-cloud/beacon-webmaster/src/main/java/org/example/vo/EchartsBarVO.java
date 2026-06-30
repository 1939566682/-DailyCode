package org.example.vo;

import lombok.Data;

/**
 * EchartsBarVO - 统计柱状图VO
 */
@Data
public class EchartsBarVO {
    private String[] categories;
    private Long[] values;
}
