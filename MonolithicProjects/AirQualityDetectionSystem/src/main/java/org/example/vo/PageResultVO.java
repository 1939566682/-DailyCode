package org.example.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-30 20:22
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResultVO<T> {
    private long total;
    private List<T> list;
}