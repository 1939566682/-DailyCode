package org.example.vo;

import lombok.Data;

/**
 * EchartsLineVO - 统计折线图VO
 */
@Data
public class EchartsLineVO {
    private String[] dates;
    private Long[] successCounts;
    private Long[] failCounts;
}
