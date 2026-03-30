package org.example.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-30 18:18
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultVO<T> {
    private Integer code;
    private String msg;
    private T data;
}
