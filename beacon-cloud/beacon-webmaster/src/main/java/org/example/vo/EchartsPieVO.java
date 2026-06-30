package org.example.vo;

import lombok.Data;
import java.util.List;

/**
 * EchartsPieVO - 统计饼图VO
 */
@Data
public class EchartsPieVO {
    private List<PieItem> items;

    @Data
    public static class PieItem {
        private String name;
        private Long value;
    }
}
